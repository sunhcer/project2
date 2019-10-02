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
        String comment = systemMapper.selectKey(10);
        String unconfirm = systemMapper.selectKey(3);
        String unpaid = systemMapper.selectKey(1);
        OrderConfigBean orderConfigBean = new OrderConfigBean(comment, unconfirm, unpaid);
        return orderConfigBean;
    }

    @Override
    public void changeOrderConfig(OrderConfigBean orderConfigBean) {
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_comment(),10);
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_unconfirm(),3);
        systemMapper.changeKey(orderConfigBean.getCskaoyan_mall_order_unpaid(),1);
    }
}
