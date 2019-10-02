package com.cskaoyan.mall.vo;

import java.util.List;

public class UsersAddressInfo {
    long total;
    List<UserAddress> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<UserAddress> getItems() {
        return items;
    }

    public void setItems(List<UserAddress> items) {
        this.items = items;
    }
}
