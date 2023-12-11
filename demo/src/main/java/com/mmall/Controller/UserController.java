package com.mmall.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.Exception.mmallException;
import com.mmall.Form.UserLoginForm;
import com.mmall.Form.UserRegisterForm;
import com.mmall.Result.ResponseEnum;
import com.mmall.entity.User;
import com.mmall.entity.UserAddress;
import com.mmall.service.CartService;
import com.mmall.service.OrdersService;
import com.mmall.service.UserAddressService;
import com.mmall.service.UserService;
import com.mmall.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    /**
     * user register
     */
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/register")
    public String register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("user information cannot be null");
            throw new mmallException(ResponseEnum.USER_INFO_NULL);
        }

        User register = this.userService.register(userRegisterForm);
        if(register == null){
            log.info("user already exist");
            throw new mmallException(ResponseEnum.Register_FAILD);
        }

        return "redirect:/login";
    }

    /**
     * user login
     */
    @PostMapping("/login")
    public  String login(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.info("user information cannot be null");
            throw new mmallException(ResponseEnum.USER_INFO_NULL);
        }

        User login = this.userService.login(userLoginForm);

        session.setAttribute("user",login);
        return "redirect:/productCategory/main";
    }
    @GetMapping("/orderList")
    public ModelAndView ordersList(HttpSession session){

        User user = (User)session.getAttribute("user");
        if(user == null){
            log.info("user not login");
            throw new mmallException(ResponseEnum.NOT_LOGIN);
        }
       ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("orderList",this.ordersService.findAllByUserId(user.getId()));
        modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
       return modelAndView;
    }
    @GetMapping("/addressList")
    public ModelAndView addressList (HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            log.info("user not login");
            throw new mmallException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());

        modelAndView.addObject("addressList",this.userAddressService.list(queryWrapper));
        modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        return modelAndView;


    }


}


