package com.cskaoyan.mall.bean;

/**
 * 类简介：商品分页数据接收
 * 创建时间: 2019-09-30 17:13
 *
 * @author EGGE
 */
public class GoodsPage {
    private int page;//当前页数
    private int limit;//每页限制
    private String sort;//排序的子列
    private String order;//排序规则
    private String name;//商品名称
    private String goodsSn;//商品编号

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
        return "GoodsPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", name='" + name + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
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

}
