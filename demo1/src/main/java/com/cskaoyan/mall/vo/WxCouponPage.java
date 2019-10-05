package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.CouponArray;

import java.util.List;

public class WxCouponPage {
    List<CouponArray> data;
    int count;

    public WxCouponPage() {
    }

    public WxCouponPage(List<CouponArray> data, int count) {
        this.data = data;
        this.count = count;
    }

    @Override
    public String toString() {
        return "WxCouponPage{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }

    public List<CouponArray> getData() {
        return data;
    }

    public void setData(List<CouponArray> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
