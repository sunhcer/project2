package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Topic;

import java.util.List;

public class WxTopicPage {
    List<Topic> data;
    int count;

    public WxTopicPage(List<Topic> data, int count) {
        this.data = data;
        this.count = count;
    }

    @Override
    public String toString() {
        return "WxTopicPage{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
