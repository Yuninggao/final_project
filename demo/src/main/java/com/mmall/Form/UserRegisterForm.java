package com.mmall.Form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class UserRegisterForm {

        @NotEmpty(message = "loginName cannot be null")
        private String loginName;
        @NotEmpty(message = "userName cannot be null")
        private String userName;
        @NotEmpty(message = "password cannot be null")
        private String password;
        @NotNull(message = "gender cannot be null")
        private Integer gender;
        @NotEmpty(message = "email cannot be null")
        private String email;
        @NotEmpty(message = "mobile cannot be null")
        private String mobile;
}
