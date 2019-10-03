package com.cskaoyan.mall.bean;

public class CouponQueryUser {
    int page;
    int limit;
    int couponId;
    int status;
    String sort;
    String order;



    public CouponQueryUser() {
        this.status=100;
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

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
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

    @Override
    public String toString() {
        return "CouponQueryUser{" +
                "page=" + page +
                ", limit=" + limit +
                ", couponId=" + couponId +
                ", status=" + status +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
