package com.cskaoyan.mall.bo;

import com.cskaoyan.mall.bean.Goods;

import java.util.List;

/**
 * 类简介：商品页面显示参数
 * 创建时间: 2019-09-30 17:20
 *
 * @author EGGE
 */
public class GoodsList {
    long total;
    List<Goods> items;

    @Override
    public String toString() {
        return "GoodsList{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Goods> getItems() {
        return items;
    }

    public void setItems(List<Goods> items) {
        this.items = items;
    }
}
