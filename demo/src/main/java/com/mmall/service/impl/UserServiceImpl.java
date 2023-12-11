package com.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mmall.Exception.mmallException;
import com.mmall.Form.UserLoginForm;
import com.mmall.Form.UserRegisterForm;
import com.mmall.Result.ResponseEnum;
import com.mmall.entity.User;
import com.mmall.mapper.UserMapper;
import com.mmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.utils.MD5Util;
import com.mmall.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author Yuning
 * @since 2023-11-07
 */
@Service

@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User register(UserRegisterForm userRegisterForm){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name",userRegisterForm.getLoginName());
        User one = this.userMapper.selectOne(queryWrapper);
        if(one !=null){
            log.info("userName exist");
            throw new mmallException(ResponseEnum.USER_NAME_EXIST);
        }

        //check email
        if (!RegexValidateUtil.checkEmail((userRegisterForm.getEmail()))) {
            log.info("email format error");
            throw new mmallException(ResponseEnum.EMAIL_ERROR);

        }
        //check mobile
        if (!RegexValidateUtil.checkMobile((userRegisterForm.getMobile()))) {
            log.info("mobile format error");
            throw new mmallException(ResponseEnum.MOBILE_ERROR);


        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm,user);
        user.setPassword(MD5Util.getSaltMD5(user.getPassword()));
        int insert = this.userMapper.insert(user);
        if(insert != 1){
            log.info("Register faild");
            throw new mmallException(ResponseEnum.Register_FAILD);
        }
        return user;


    }
    @Override
    public User login(UserLoginForm userloginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userloginForm.getLoginName());
        User one = this.userMapper.selectOne(queryWrapper);
        if (one == null) {
            log.info("userName not exist");
            throw new mmallException(ResponseEnum.USER_NAME_NOT_EXIST);
        }

        boolean saltverifyMD5 = MD5Util.getSaltverifyMD5(userloginForm.getPassword(), one.getPassword());
        if(!saltverifyMD5){
            log.info("password doesn't match");
            throw new mmallException(ResponseEnum.PASSWORD_ERROR);
        }
        return one;

    }
}
