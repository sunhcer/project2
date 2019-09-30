package com.cskaoyan.controller;

import com.cskaoyan.vo.BaseRespVo;
import com.cskaoyan.vo.LoginVo;
import com.cskaoyan.vo.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 10:52
 *
 * @author EGGE
 */
@RestController
public class AuthController {
    @RequestMapping("admin/auth/login")
    public BaseRespVo login(LoginVo user){
        BaseRespVo respVo=BaseRespVo.success("795d0858-6431-462a-b1ae-50b58801733f");
        return respVo;

    }
    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token){
        UserInfo userInfo=new UserInfo();
        userInfo.setAvater("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("admin123");
        List perms=new ArrayList();
        perms.add("*");
        userInfo.setPerms(perms);
        List roles=new ArrayList();
        roles.add("超级管理员");
        userInfo.setRoles(roles);
        BaseRespVo respVo=BaseRespVo.success(userInfo);
        return respVo;

    }
}
