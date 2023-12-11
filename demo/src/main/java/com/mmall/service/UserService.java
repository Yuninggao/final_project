package com.mmall.service;

import com.mmall.Form.UserLoginForm;
import com.mmall.Form.UserRegisterForm;
import com.mmall.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
public interface UserService extends IService<User> {
    public User register(UserRegisterForm userRegisterForm);
    public User login(UserLoginForm userloginForm);


}
