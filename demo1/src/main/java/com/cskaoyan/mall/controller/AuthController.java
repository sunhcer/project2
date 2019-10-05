package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.DashBoardService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.DashBoard;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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

    @Autowired
    AdminService adminService;
    @Value("${myfile.img-prefix}")
    String myprefix;

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo){

        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.fail(605,"用户帐号或密码不正确");
        }
        Serializable id = subject.getSession().getId();
        return BaseRespVo.success(id);

    }

    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Admin admin = adminService.selectAdminByName(principal);
        UserInfo userInfo = new UserInfo();
        String avatar = admin.getAvatar();
        String username = admin.getUsername();
        if(avatar != null){
            userInfo.setAvatar(myprefix + avatar);
        }
        userInfo.setName(username);

        List<String> perms = adminService.selectPermissionsByName(principal);
        List<String> roles = adminService.selectRolesByName(principal);

        userInfo.setPerms(perms);
        userInfo.setRoles(roles);

        return BaseRespVo.success(userInfo);
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

    @RequestMapping("/fail")
    public String fail(){
        return "fail";
    }

    @RequestMapping("/admin/auth/logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success(null);
    }
}
