package com.cskaoyan.mall.vo;
import java.util.List;

public class WxCouponPage<T> {
    List<T> data;
    int status;
    int page;
    int size;
    String code;
    int count;

    public WxCouponPage() {
    }

    public WxCouponPage(List<T> data, int count) {
        this.data = data;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
