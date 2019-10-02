package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public class UsersFootInfo {
    long total;
    List<Footprint> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Footprint> getItems() {
        return items;
    }

    public void setItems(List<Footprint> items) {
        this.items = items;
    }
}
