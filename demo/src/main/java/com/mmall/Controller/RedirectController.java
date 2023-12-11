package com.mmall.Controller;

import com.mmall.entity.User;
import com.mmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RedirectController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{url}")
    public ModelAndView redirect(@PathVariable("url") String url, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        modelAndView.addObject("cartList",new ArrayList<>());
        User user = (User) session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{

            modelAndView.addObject("cartList",this.cartService.findVoListByUserId(user.getId()));
        }
        return modelAndView;


    }
    @GetMapping("/")
    public String main() {
        return "redirect:/productCategory/main";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon(){

    }



}
