package com.cskaoyan.mall.bean;

import java.util.List;

public class StorageListInfo {
    private long total;

    private List<Storage> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Storage> getItems() {
        return items;
    }

    public void setItems(List<Storage> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "StorageListInfo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
