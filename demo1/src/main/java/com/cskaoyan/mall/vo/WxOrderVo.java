package com.cskaoyan.mall.vo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:45
 */
public class WxOrderVo {
    WxOrderDetailVo data;
    int count;
    int totalPages;

    public WxOrderDetailVo getData() {
        return data;
    }

    public void setData(WxOrderDetailVo data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
