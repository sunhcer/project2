package com.cskaoyan.mall.vo;

import java.util.List;

public class WxBrandPage<T> {
    List<T> brandList;
    int totalPages;

    public WxBrandPage() {
    }

    public WxBrandPage(List<T> brandList, int totalPages) {
        this.brandList = brandList;
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "WxBrandPage{" +
                "brandList=" + brandList +
                ", totalPages=" + totalPages +
                '}';
    }

    public List<T> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<T> brandList) {
        this.brandList = brandList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
