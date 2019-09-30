package com.cskaoyan.mall.bean;


import java.util.List;

public class AdminListInfo {
    private long total;

    private List<AdminDesc> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<AdminDesc> getItems() {
        return items;
    }

    public void setItems(List<AdminDesc> items) {
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
