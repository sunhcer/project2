package com.cskaoyan.mall.vo;


import java.util.List;

public class WxCouponPage<T> {
    List<T> data;
    int count;

    public WxCouponPage() {
    }

    public WxCouponPage(List<T> data, int count) {
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
