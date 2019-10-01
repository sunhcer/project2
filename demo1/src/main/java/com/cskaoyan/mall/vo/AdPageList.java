package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Ad;

import java.util.List;

public class AdPageList {
    int toatl;
    List<Ad> items;

    public AdPageList() {
    }

    public AdPageList(int toatl, List<Ad> items) {
        this.toatl = toatl;
        this.items = items;
    }

    @Override
    public String toString() {
        return "AdPageList{" +
                "toatl=" + toatl +
                ", items=" + items +
                '}';
    }

    public int getToatl() {
        return toatl;
    }

    public void setToatl(int toatl) {
        this.toatl = toatl;
    }

    public List<Ad> getItems() {
        return items;
    }

    public void setItems(List<Ad> items) {
        this.items = items;
    }
}
