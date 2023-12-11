package com.mmall.Exception;
import com.mmall.Result.ResponseEnum;
public class mmallException extends RuntimeException {
    private ResponseEnum responseEnum;

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public mmallException(ResponseEnum responseEnum){
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;

    }
}
