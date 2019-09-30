package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 15:51
 */
public class BrandList {
    long total;
    List<Brand> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Brand> getItems() {
        return items;
    }

    public void setItems(List<Brand> items) {
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
