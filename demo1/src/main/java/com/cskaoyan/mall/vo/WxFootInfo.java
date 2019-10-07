package com.cskaoyan.mall.vo;

import java.util.List;

public class WxFootInfo {
    long totalPages;
    List<WxFoot> footprintList;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<WxFoot> getFootprintList() {
        return footprintList;
    }

    public void setFootprintList(List<WxFoot> footprintList) {
        this.footprintList = footprintList;
    }
}
