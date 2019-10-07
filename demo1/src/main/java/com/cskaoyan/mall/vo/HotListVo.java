package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class HotListVo {

    private List<Goods> goodsList;

    private long count;

    private List<Category> filterCategoryList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Category> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<Category> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }
}
