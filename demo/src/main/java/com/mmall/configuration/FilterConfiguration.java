package com.mmall.configuration;

import com.mmall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean registerBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*","/user/orderList","/user/addressList");
        return filterRegistrationBean;


    }
}
