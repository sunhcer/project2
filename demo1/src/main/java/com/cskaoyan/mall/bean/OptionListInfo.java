package com.cskaoyan.mall.bean;

import java.util.List;

public class OptionListInfo {

    private long total;

    private List<Role> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Role> getItems() {
        return items;
    }

    public void setItems(List<Role> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OptionListInfo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
