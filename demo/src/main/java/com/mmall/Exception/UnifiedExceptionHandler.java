package com.mmall.Exception;

import com.mmall.Result.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class UnifiedExceptionHandler {
    @ExceptionHandler(value = mmallException.class)
    public ModelAndView handlerException(mmallException e){
        ModelAndView modelAndView = new ModelAndView();

        ResponseEnum responseEnum = e.getResponseEnum();
        switch (responseEnum.getCode()){
            case 301:
                modelAndView.setViewName("register");
                modelAndView.addObject("emailError",responseEnum.getMsg());
                break;
            case 302:
                modelAndView.setViewName("register");
                modelAndView.addObject("mobileError",responseEnum.getMsg());
                break;
            case 303:
                modelAndView.setViewName("register");
                modelAndView.addObject("userNameExists",responseEnum.getMsg());
                break;
            case 304:
                modelAndView.setViewName("register");
                modelAndView.addObject("registerFaild",responseEnum.getMsg());
                break;
            case 305:

                modelAndView.setViewName("login");
                modelAndView.addObject("loginFaild",responseEnum.getMsg());
                break;
            case 306:
                modelAndView.setViewName("login");
                modelAndView.addObject("password error",responseEnum.getMsg());
                break;
            case 308:
                modelAndView.setViewName("login");
                break;







        }
        return modelAndView;
    }


}
