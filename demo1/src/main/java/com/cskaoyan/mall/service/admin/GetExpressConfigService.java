package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.ExpressConfigBean;

public interface GetExpressConfigService {
    //显示运费配置
    ExpressConfigBean showExpressConfige();
    //修改运费配置
    void changeExpressConfig(ExpressConfigBean expressConfigBean);
}
