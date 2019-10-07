package com.cskaoyan.mall.vo;

import java.util.Arrays;
import java.util.Date;

public class WxUserComment {
    WxUserInfo userInfo;
    Date addTime;
    String[] picList;
    String content;

    public WxUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WxUserComment{" +
                "userInfo=" + userInfo +
                ", addTime=" + addTime +
                ", picList=" + Arrays.toString(picList) +
                ", content='" + content + '\'' +
                '}';
    }
}
