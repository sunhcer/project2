package com.cskaoyan.mall.bean;


import java.util.List;

public class AdminListInfo {
    private long total;

    private List<Admin> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Admin> getItems() {
        return items;
    }

    public void setItems(List<Admin> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AdminListInfo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
