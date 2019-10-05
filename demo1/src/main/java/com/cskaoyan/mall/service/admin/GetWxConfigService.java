package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.WxConfigBean;

public interface GetWxConfigService {
    //显示小程序配置
    WxConfigBean showWxConfig();
    //修改小程序配置
    void changWxConfig(WxConfigBean wxConfigBean);
}
