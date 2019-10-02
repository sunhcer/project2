package com.cskaoyan.mall.bean;

import java.util.List;

public class CouponRef {
    List<Coupon> items;
    int total;

    public CouponRef() {
    }

    public CouponRef(List<Coupon> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<Coupon> getItems() {
        return items;
    }

    public void setItems(List<Coupon> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CouponRef{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
