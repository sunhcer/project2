package com.cskaoyan.mall.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String authorizationExceptionHandle(AuthorizationException e){
        return "perm refuse";
    }
}