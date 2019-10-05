package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:39
 */
public class WxOrderDetailVo {
    int id;
    String orderStatusText;
    boolean isGroupin;
    String orderSn;
    double actualPrice;
    List<OrderGoods> goodsList;
    HandleOption handleOption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public boolean isGroupin() {
        return isGroupin;
    }

    public void setGroupin(boolean groupin) {
        isGroupin = groupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }

}
