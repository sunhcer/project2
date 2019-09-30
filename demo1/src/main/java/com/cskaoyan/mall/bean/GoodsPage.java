package com.cskaoyan.mall.bean;

/**
 * 类简介：商品分页数据接收
 * 创建时间: 2019-09-30 17:13
 *
 * @author EGGE
 */
public class GoodsPage {
    private int page;
    private int limit;
    private String addTime;
    private String desc;
    private String name;
    private String goodsSn;

    @Override
    public String toString() {
        return "GoodsPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", addTime='" + addTime + '\'' +
                ", desc='" + desc + '\'' +
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
