package com.cskaoyan.mall.controller.admin;


import com.cskaoyan.mall.bean.PasswordProfile;
import com.cskaoyan.mall.service.admin.AdminService;
import com.cskaoyan.mall.service.admin.DashBoardService;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.admin.PermissionService;
import com.cskaoyan.mall.shiro.CustomToken;
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
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;


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

    @Autowired
    PermissionService permissionService;

    @Autowired
    LogService logService;

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo, HttpServletRequest request){

        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        UsernamePasswordToken token = new CustomToken(username, password, "user");
        Subject subject = SecurityUtils.getSubject();
        String remoteHost = request.getRemoteHost();
        if(subject != null) {
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                logService.addLog(remoteHost,username,0,"登录",false,new Date());
                return BaseRespVo.fail(605, "用户帐号或密码不正确");
            }
            Serializable id = subject.getSession().getId();
            logService.addLog(remoteHost,username,1,"登录",true,new Date());
            return BaseRespVo.success(id);
        } else
            logService.addLog(remoteHost,username,0,"登录",false,new Date());
            return BaseRespVo.fail(605,"账号不存在");

    }

    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();

        UserInfo userInfo = adminService.getRoleMessage(principal);


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
    public BaseRespVo fail(){
        return BaseRespVo.fail(507,"登录失效，请重新登录");
    }

    @RequestMapping("/admin/auth/logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/profile/password")
    public BaseRespVo profilePassword(@RequestBody PasswordProfile passwordProfile) {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        boolean b = adminService.profilePassword(passwordProfile,username);
        if(b) {
            subject.logout();
            return BaseRespVo.success(null);
        }
        return BaseRespVo.fail(605,"账号密码不对");
    }
}
