package com.cskaoyan.mall.vo;

import java.util.List;

public class UsersCollectInfo {
    long total;
    List<UserCollect> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<UserCollect> getItems() {
        return items;
    }

    public void setItems(List<UserCollect> items) {
        this.items = items;
    }
}
