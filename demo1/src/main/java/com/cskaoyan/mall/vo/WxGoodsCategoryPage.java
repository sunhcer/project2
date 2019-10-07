package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

public class WxGoodsCategoryPage<T> {
    Category parentCategory;
    Category currentCategory;
    List<T> brotherCategory;

    @Override
    public String toString() {
        return "WxGoodsCategoryPage{" +
                "parentCategory=" + parentCategory +
                ", currentCategory=" + currentCategory +
                ", brotherCategory=" + brotherCategory +
                '}';
    }

    public WxGoodsCategoryPage(Category parentCategory, Category currentCategory, List<T> brotherCategory) {
        this.parentCategory = parentCategory;
        this.currentCategory = currentCategory;
        this.brotherCategory = brotherCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<T> getBrotherCategory() {
        return brotherCategory;
    }

    public void setBrotherCategory(List<T> brotherCategory) {
        this.brotherCategory = brotherCategory;
    }
}
