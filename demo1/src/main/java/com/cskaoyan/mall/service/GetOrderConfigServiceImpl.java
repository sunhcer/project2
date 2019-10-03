package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OrderConfigBean;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrderConfigServiceImpl implements GetOrderConfigService{
    @Autowired
    SystemMapper systemMapper;

    @Override
    public OrderConfigBean showOrderConfig() {
        String comment = systemMapper.selectKey("cskaoyan_mall_order_comment");
        String unconfirm = systemMapper.selectKey("cskaoyan_mall_order_unconfirm");
        String unpaid = systemMapper.selectKey("cskaoyan_mall_order_unpaid");
        OrderConfigBean orderConfigBean = new OrderConfigBean(comment, unconfirm, unpaid);
        return orderConfigBean;
    }

    @Override
    public void changeOrderConfig(OrderConfigBean orderConfigBean) {
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_comment(),"cskaoyan_mall_order_comment");
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_unconfirm(),"cskaoyan_mall_order_unconfirm");
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_unpaid(),"cskaoyan_mall_order_unpaid");
    }
}
