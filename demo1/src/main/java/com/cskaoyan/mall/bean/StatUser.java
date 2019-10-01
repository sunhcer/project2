package com.cskaoyan.mall.bean;

import java.util.Date;

public class StatUser {

    private String day;

    private int users;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "StatUser{" +
                "day=" + day +
                ", users=" + users +
                '}';
    }
}
