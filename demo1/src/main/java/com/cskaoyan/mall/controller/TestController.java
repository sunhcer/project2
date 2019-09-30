package com.cskaoyan.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 11:11
 */
@RestController
public class TestController {
    @RequestMapping("test")
    public String test1(){
        return "test success";
    }
}
