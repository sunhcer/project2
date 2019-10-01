package com.cskaoyan.mall.bean;

public class CouponReceive {
    int page;
    int limit;
    String name;
    int type;
    int status;
    String sort;
    String order;

    @Override
    public String toString() {
        return "CouponReceive{" +
                "page=" + page +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
