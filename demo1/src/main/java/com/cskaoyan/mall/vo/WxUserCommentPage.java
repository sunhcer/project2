package com.cskaoyan.mall.vo;

import java.util.List;

public class WxUserCommentPage<T> {
    List<T> data;
    int count;
    int currentPage;

    public WxUserCommentPage(List<T> data, int count, int currentPage) {
        this.data = data;
        this.count = count;
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "WxUserCommentPage{" +
                "data=" + data +
                ", count=" + count +
                ", currentPage=" + currentPage +
                '}';
    }
}
