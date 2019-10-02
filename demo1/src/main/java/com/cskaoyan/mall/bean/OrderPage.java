package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 17:29
 */
public class OrderPage {
    private int page;
    private int limit;
    private String sort;
    private String order;
    List<Integer> orderStatusArray;
    private Integer userId;
    private String orderSn;
    String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getOrderStatusArray() {
        return orderStatusArray;
    }

    public void setOrderStatusArray(List<Integer> orderStatusArray) {
        this.orderStatusArray = orderStatusArray;
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
