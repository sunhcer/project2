package com.cskaoyan.mall.vo;

import java.util.List;

public class WxCollectInfo {
    int totalPages;
    List<WxCollect> collectList;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<WxCollect> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<WxCollect> collectList) {
        this.collectList = collectList;
    }
}
