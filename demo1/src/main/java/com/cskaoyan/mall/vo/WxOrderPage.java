package com.cskaoyan.mall.vo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:24
 */
public class WxOrderPage {
    int showType;
    int page;
    int size;

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
