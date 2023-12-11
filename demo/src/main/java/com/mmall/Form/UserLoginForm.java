package com.mmall.Form;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
@Data

public class UserLoginForm {
    @NotEmpty(message = "loginName cannot be null")
    private String loginName;

    @NotEmpty(message = "password cannot be null")
    private String password;
}
