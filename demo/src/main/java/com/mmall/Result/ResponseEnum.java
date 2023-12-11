package com.mmall.Result;

public enum ResponseEnum {

    USER_INFO_NULL(300,"Username cannot be null"),
    EMAIL_ERROR(301,"email format error"),
    MOBILE_ERROR(302,"mobile not vaild"),

    USER_NAME_EXIST(303,"UserName EXIST"),
    Register_FAILD(304,"RegisterFaild"),

    USER_NAME_NOT_EXIST(305,"UserName Not Exist"),

    PASSWORD_ERROR(306,"Password error"),
    PARAMETER_NULL(307,"parameter error"),
    NOT_LOGIN(308,"User not login"),
    CART_ADD_ERROR(309,"Cart add error"),
    PRODUCT_NOT_EXIST(310,"product not exists"),
    STOCK_ERROR(311,"Stock error"),

    UPDATE_CART_ERROR(312,"Update error"),
    UPDATE_STOCK_ERROR(313,"Update stock error"),

    DELETE_STOCK_ERROR(314,"Delete stock error"),
    CHECK_OUT_ERROR(315,"Check out error"),
    DELETE_ERROR(316,"Delete error"),
    USER_ADDRESS_ADD_ERROR(317,"Useraddress add error"),
    USER_ADDRESS_SET_DEFAULT_ERROR(318,"Set Default address error");


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
