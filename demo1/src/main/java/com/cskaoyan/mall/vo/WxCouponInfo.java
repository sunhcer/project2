package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

public class WxCouponInfo {
    long count;
    List<Coupon> data;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Coupon> getData() {
        return data;
    }

    public void setData(List<Coupon> data) {
        this.data = data;
    }
}
