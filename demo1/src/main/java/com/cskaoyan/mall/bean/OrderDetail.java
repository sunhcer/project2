package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description: 订单详情表 包括很多商品和订单的信息
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 19:40
 */
public class OrderDetail {
    List<OrderGoods> orderGoods;
    User user;
    Order order;

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
