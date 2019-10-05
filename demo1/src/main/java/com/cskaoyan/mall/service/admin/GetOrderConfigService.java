package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.OrderConfigBean;

public interface GetOrderConfigService {
    //显示订单配置
    OrderConfigBean showOrderConfig();
    //修改订单配置
    void changeOrderConfig(OrderConfigBean orderConfigBean);
}
