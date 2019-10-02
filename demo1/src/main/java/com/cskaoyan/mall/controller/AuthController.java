package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.service.DashBoardService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.DashBoard;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    DashBoardService dashBoardService;


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

    /**
     * 登录后首页的 没有新建 直接用的这个
     * @return
     */
    @RequestMapping("/admin/dashboard")
    public BaseRespVo dashboard(){

        DashBoard dashBoard = new DashBoard();

        dashBoard.setGoodsTotal(dashBoardService.queryGoodsTotal());
        dashBoard.setUserTotal(dashBoardService.queryUserTotal());
        dashBoard.setProductsTotal(dashBoardService.queryProductTotal());
        dashBoard.setOrderTotal(dashBoardService.queryOrderTotal());
        BaseRespVo respVo = BaseRespVo.success(dashBoard);
        return respVo;
    }
}
