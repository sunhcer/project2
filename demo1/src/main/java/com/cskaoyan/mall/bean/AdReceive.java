package com.cskaoyan.mall.bean;

public class AdReceive {
    int page;
    int limit;
    String name;
    String sort;
    String order;
    String content;


    public AdReceive(int page, int limit, String name, String sort, String order, String content) {
        this.page = page;
        this.limit = limit;
        this.name = name;
        this.sort = sort;
        this.order = order;
        this.content = content;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AdReceive{" +
                "page=" + page +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
