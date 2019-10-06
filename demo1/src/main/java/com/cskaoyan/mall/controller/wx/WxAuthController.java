package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.admin.UserService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.mall.util.UserInfo;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:07
 */
@RestController
public class WxAuthController {
    @Value("${myfile.img-prefix}")
    String myprefix;

    @Autowired
    UserService userService;
    @RequestMapping("/wx/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo) {
        /*String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        Session session = SecurityUtils.getSubject().getSession();
        String token=session.getId().toString();
        System.out.println(token);
        session.setAttribute("username",username);
        //*******************************
        //根据username和password查询user信息
        //*******************************
        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        //userInfo.setAvatarUrl(user.getAvatar());
        //********************************
        //根据获得userid
        int userId = 1;
        //********************************
        // token
        UserToken userToken = UserTokenManager.generateToken(userId);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return BaseRespVo.success(result);*/

        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        UsernamePasswordToken customToken = new CustomToken(username, password, "wx");
        Subject subject = SecurityUtils.getSubject();

        if(subject != null) {
            try {
                subject.login(customToken);
            } catch (AuthenticationException e) {
                return BaseRespVo.fail(700, "用户帐号或密码不正确");
            }
            //登录成功
            Session session = subject.getSession();
            String token = session.getId().toString();
            session.setAttribute("username", username);
            User user = userService.selectUserInfoByUsername(username);

            UserInfo userInfo = new UserInfo();
            userInfo.setAvatarUrl(myprefix + user.getAvatar());
            userInfo.setNickName(username);
            Map<Object, Object> result = new HashMap<>();
            result.put("token", token);
            //距当前日期两天后
            result.put("tokenExpire", new Date(System.currentTimeMillis() + 60*60*12*2*1000));
            result.put("userInfo", userInfo);

            return BaseRespVo.success(result);
        } else {
            return BaseRespVo.fail(605, "账号不存在");
        }


    }
}
