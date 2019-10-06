package com.cskaoyan.mall.controller.wx;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.config.AliyunConfig;
import com.cskaoyan.mall.config.SmsConfig;
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

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    AliyunConfig aliyunConfig;

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

        if (subject != null) {
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
            result.put("tokenExpire", new Date(System.currentTimeMillis() + 60 * 60 * 12 * 2 * 1000));
            result.put("userInfo", userInfo);

            return BaseRespVo.success(result);
        } else {
            return BaseRespVo.fail(605, "账号不存在");
        }
    }

    ///wx/auth/logout
    @RequestMapping("/wx/auth/logout")
    public BaseRespVo wxlogout() {
        //直接清空
        SecurityUtils.getSubject().logout();
        return BaseRespVo.success(null);
    }

    private String sendMessage(String mobile) {

        int code = (int) (Math.random() * 1000000);
        String accessKeyId = aliyunConfig.getAccessKeyId();
        String accessSecret = aliyunConfig.getAccessSecret();
        SmsConfig smsConfig = aliyunConfig.getSmsConfig();
        String regionId = smsConfig.getRegionId();
        String signName = smsConfig.getSignName();
        String templateCode = smsConfig.getTemplateCode();
        DefaultProfile profile = DefaultProfile.getProfile(regionId,
                accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code + "";
    }

    ///wx/auth/regCaptcha
    @RequestMapping("/wx/auth/regCaptcha")
    public BaseRespVo regCaptcha(@RequestBody Map map) {
        String mobile = (String) map.get("mobile");
        Session session = SecurityUtils.getSubject().getSession();
        Serializable sessionId = session.getId();
        System.out.println("第一次进" + sessionId);
//        String code = sendMessage(mobile);
        String code = "666999";
        session.setAttribute("code", code);
        return BaseRespVo.success(sessionId);
    }

    ///wx/auth/register
    @RequestMapping("/wx/auth/register")
    public BaseRespVo register(@RequestBody User user) {
        //代表是否注册成功
        boolean flag = false;

        Session session = SecurityUtils.getSubject().getSession();
        Serializable sessionId = session.getId();
        System.out.println("第二次进" + sessionId);
        String code = (String) session.getAttribute("code");
        if (!Objects.equals(code, user.getCode())) {
            return BaseRespVo.fail(605, "验证码错误");
        } else {
            flag = userService.registertUser(user);
        }

        if (!flag){
            return BaseRespVo.fail(608, "账号已存在");
        }
        user.setNickname(user.getUsername());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("userInfo", user);
        map.put("token", sessionId);
        return BaseRespVo.success(map);
    }


}
