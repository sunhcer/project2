package com.cskaoyan.mall.bean;

import java.util.List;

public class UsersListInfo {
    long total;
    List<User> items;

    @Override
    public String toString() {
        return "UsersListInfo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}
