package com.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.Exception.mmallException;
import com.mmall.Result.ResponseEnum;
import com.mmall.entity.User;
import com.mmall.service.CartService;
import com.mmall.service.ProductCategoryService;
import com.mmall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;


    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") Integer type,  @PathVariable("id") Integer productCategoryId,HttpSession session ){
        if( type == null || productCategoryId == null){
            log.info("category is null");
            throw new mmallException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",this.productService.findByTypeAndProductCategoryId(type,productCategoryId));

        User user = (User) session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        }


        modelAndView.addObject("list",this.productCategoryService.buildProductCategoryMenue());
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(String keyWord, HttpSession session){

        if(keyWord == null){
            log.info("parameter is null");
            throw new mmallException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",keyWord);
        modelAndView.addObject("productList",this.productService.list(queryWrapper));
        User user = (User) session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{

            modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        }


        modelAndView.addObject("list",this.productCategoryService.buildProductCategoryMenue());
        return modelAndView;
    }
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id,HttpSession session){
        if(id == null){
            log.info("parameter is null");
            throw new mmallException(ResponseEnum.PARAMETER_NULL);


        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{


            modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        }


        modelAndView.addObject("list",this.productCategoryService.buildProductCategoryMenue());

        modelAndView.addObject("product",this.productService.getById(id));
        return modelAndView;
        }

    }









