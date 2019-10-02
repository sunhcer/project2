package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.SearchHistory;

import java.util.List;

public class UserHistoryInfo {
    long total;
    List<SearchHistory> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<SearchHistory> getItems() {
        return items;
    }

    public void setItems(List<SearchHistory> items) {
        this.items = items;
    }
}
