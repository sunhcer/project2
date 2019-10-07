package com.cskaoyan.mall.vo;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:45
 */
public class WxOrderVo {
    List<WxOrderDetailVo> data;
    long count;
    int totalPages;

    public List<WxOrderDetailVo> getData() {
        return data;
    }

    public void setData(List<WxOrderDetailVo> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
