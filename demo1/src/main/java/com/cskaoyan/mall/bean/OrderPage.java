package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description: 关键词复用了这个  团购规则也复用了
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
    String keyword;
    String url;
    Integer goodsId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
