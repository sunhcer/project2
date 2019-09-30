package com.cskaoyan.mall.vo;

/**
 * @Description:主面板的四个变量
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 12:23
 */
public class DashBoard {
    int goodsTotal;
    int userTotal;
    int productsTotal;
    int orderTotal;

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public int getProductsTotal() {
        return productsTotal;
    }

    public void setProductsTotal(int productsTotal) {
        this.productsTotal = productsTotal;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }
}
