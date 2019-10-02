package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Feedback;

import java.util.List;

public class UserFeedbackInfo {
    long total;
    List<Feedback> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Feedback> getItems() {
        return items;
    }

    public void setItems(List<Feedback> items) {
        this.items = items;
    }
}
