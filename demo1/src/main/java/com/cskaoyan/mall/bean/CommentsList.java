package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * 类简介：评价页面显示参数
 * 创建时间: 2019-09-30 20:32
 *
 * @author EGGE
 */
public class CommentsList {
    long total;
    List<Comment> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Comment> getItems() {
        return items;
    }

    public void setItems(List<Comment> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CommentsList{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
