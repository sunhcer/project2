package com.cskaoyan.mall.bean;

public class TopicReceive {
    int page;
    int limit;
    String title;
    String subtitle;
    String sort;
    String order;

    @Override
    public String toString() {
        return "TopicReceive{" +
                "page=" + page +
                ", limit=" + limit +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
}
