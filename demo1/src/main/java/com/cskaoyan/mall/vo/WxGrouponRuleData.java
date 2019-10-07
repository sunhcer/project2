package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Goods;

import java.math.BigDecimal;
import java.util.List;

public class WxGrouponRuleData {
    BigDecimal grouponPrice;
    Goods goods;
    int grouponMember;

    @Override
    public String toString() {
        return "WxGrouponRuleData{" +
                "grouponPrice=" + grouponPrice +
                ", goods=" + goods +
                ", grouponMember=" + grouponMember +
                '}';
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getGrouponMember() {
        return grouponMember;
    }

    public void setGrouponMember(int grouponMember) {
        this.grouponMember = grouponMember;
    }
}
