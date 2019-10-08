package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.TopicArray;

import java.util.Arrays;

public class TopicDetailWxPage {
    TopicArray topic;
    String[] goods;

    public TopicDetailWxPage(TopicArray topic) {
        this.topic = topic;
    }

    public TopicDetailWxPage(TopicArray topic, String[] goods) {
        this.topic = topic;
        this.goods = goods;
    }

    public TopicArray getTopic() {
        return topic;
    }

    public void setTopic(TopicArray topic) {
        this.topic = topic;
    }

    public String[] getGoods() {
        return goods;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "TopicDetailWxPage{" +
                "topic=" + topic +
                ", goods=" + Arrays.toString(goods) +
                '}';
    }
}
