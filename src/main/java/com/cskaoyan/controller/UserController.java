package com.cskaoyan.controller;

import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping
    public String hello(int id){
        String username = userMapper.queryUserNameById(id);
        return username;
    }
}
