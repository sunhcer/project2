package com.cskaoyan.mall.vo;

import java.util.List;

public class WxGoodsListPage<T> {
    List<T> filterCategoryList;
    int count;
    List<T> goodsList;

    public WxGoodsListPage(List<T> filterCategoryList, int count, List<T> goodsList) {
        this.filterCategoryList = filterCategoryList;
        this.count = count;
        this.goodsList = goodsList;
    }

    public WxGoodsListPage(int count, List<T> goodsList) {
        this.count = count;
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "WxGoodsListPage{" +
                "filterCategoryList=" + filterCategoryList +
                ", count=" + count +
                ", goodsList=" + goodsList +
                '}';
    }

    public List<T> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<T> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<T> goodsList) {
        this.goodsList = goodsList;
    }
}
