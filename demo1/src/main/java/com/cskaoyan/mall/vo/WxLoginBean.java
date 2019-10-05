package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.WxUserInfo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:11
 */
public class WxLoginBean {
    WxUserInfo userInfo;
    String tokenExpire;
    String token;

    public WxUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
