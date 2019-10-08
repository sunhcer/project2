package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatUser {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date day;

    private int users;


    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
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
