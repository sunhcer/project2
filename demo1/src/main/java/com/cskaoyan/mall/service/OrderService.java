package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderDetail;
import com.cskaoyan.mall.bean.OrderPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 17:09
 */
public interface OrderService {
    BrandList getOrderByState(OrderPage page);

    OrderDetail getOrderDetail(int id);

    void updateOrder(Order order);
}
