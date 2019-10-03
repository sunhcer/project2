package com.cskaoyan.mall.bean;

import java.util.List;

public class TopicArrayPage {
    List<TopicArray> items;
    int total;

    public TopicArrayPage() {
    }

    public TopicArrayPage(List<TopicArray> items, int total) {
        this.items = items;
        this.total = total;
    }

    @Override
    public String toString() {
        return "TopicArrayPage{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }

    public List<TopicArray> getItems() {
        return items;
    }

    public void setItems(List<TopicArray> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
