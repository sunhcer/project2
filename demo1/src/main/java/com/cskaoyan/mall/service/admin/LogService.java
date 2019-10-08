package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.LogListInfo;

import java.util.Date;

public interface LogService {
    LogListInfo selectAllLog(int page, int limit, String name, String sort, String order);


    void addLog(String ip, String username, int type, String action, boolean status, Date actionTime);
}
