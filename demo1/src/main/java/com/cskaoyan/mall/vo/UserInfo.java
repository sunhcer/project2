package com.cskaoyan.mall.vo;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 11:08
 *
 * @author EGGE
 */
public class UserInfo {
    String avater;
    String name;
    List perms;
    List roles;

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getPerms() {
        return perms;
    }

    public void setPerms(List perms) {
        this.perms = perms;
    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }
}
