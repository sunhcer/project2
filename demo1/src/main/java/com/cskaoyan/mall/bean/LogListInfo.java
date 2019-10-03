package com.cskaoyan.mall.bean;

import java.util.List;

public class LogListInfo {
    private long total;

    private List<Log> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Log> getItems() {
        return items;
    }

    public void setItems(List<Log> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "LogListInfo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
