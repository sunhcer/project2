package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 17:41
 */
public class WxOrderDetailData {
    Order orderInfo;
    List<OrderGoods> orderGoods;
    Map expressInfo = new HashMap();

    public Map getExpressInfo() {
        return expressInfo;
    }

    public void setExpressInfo(Map expressInfo) {
        this.expressInfo = expressInfo;
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
