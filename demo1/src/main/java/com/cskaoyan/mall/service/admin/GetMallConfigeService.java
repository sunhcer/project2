package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.MarkConfigBean;

import java.text.ParseException;

public interface GetMallConfigeService {
    //显示商场配置
    MarkConfigBean showMallConfige();
    //修改商场配置
    void changeMallConfige(MarkConfigBean markConfigBean);
}
