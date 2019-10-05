package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.util.JacksonUtil;
import com.cskaoyan.mall.util.UserInfo;
import com.cskaoyan.mall.util.UserToken;
import com.cskaoyan.mall.util.UserTokenManager;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/wx/auth/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
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
//        result.put("token", userToken.getToken());
        result.put("token", token);
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return BaseRespVo.success(result);
    }
}
