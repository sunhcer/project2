package com.cskaoyan.mall.bean;

import java.util.List;

public class CouponUserPage {
    List<CouponUser> items;
    int total;

    public CouponUserPage() {
    }

    public CouponUserPage(List<CouponUser> items, int total) {
        this.items = items;
        this.total = total;
    }

    @Override
    public String toString() {
        return "CouponUserPage{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }

    public List<CouponUser> getItems() {
        return items;
    }

    public void setItems(List<CouponUser> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
