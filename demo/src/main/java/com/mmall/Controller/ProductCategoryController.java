package com.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.entity.User;
import com.mmall.service.CartService;
import com.mmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    @GetMapping("/main")
    public ModelAndView main(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list",this.productCategoryService.buildProductCategoryMenue());
        modelAndView.addObject("levelOne",this.productCategoryService.findAllProductByCategoryLevelOne());
        User user = (User) session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{


            modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        }
        return modelAndView;

    }


}

