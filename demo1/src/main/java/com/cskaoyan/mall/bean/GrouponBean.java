package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * @Description: 集合的数据 有三个 Groupon Goods Rules
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 21:10
 */
public class GrouponBean {
    Groupon groupon;
    Goods goods;
    GrouponRules rules;
    List<Groupon> subGroupons;

    public List<Groupon> getSubGroupons() {
        return subGroupons;
    }

    public void setSubGroupons(List<Groupon> subGroupons) {
        this.subGroupons = subGroupons;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }
}
