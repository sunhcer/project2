package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description:    复用  在订单和品牌处复用 问题也复用了
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 15:51
 */
public class BrandList <T> {
    long total;
    List<T> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BrandList{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
