package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.Order;

import java.util.List;

public class WxGrouponDetail {

    WxGrouponCreator creator;
    Groupon groupon;
    List<WxGrouponCreator> joiners;
    Order orderInfo;
    List<WxGrouponGood> orderGoods;
    GrouponRules rules;
    int linkGrouponId;

    public WxGrouponCreator getCreator() {
        return creator;
    }

    public void setCreator(WxGrouponCreator creator) {
        this.creator = creator;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public List<WxGrouponCreator> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<WxGrouponCreator> joiners) {
        this.joiners = joiners;
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<WxGrouponGood> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<WxGrouponGood> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }

    public int getLinkGrouponId() {
        return linkGrouponId;
    }

    public void setLinkGrouponId(int linkGrouponId) {
        this.linkGrouponId = linkGrouponId;
    }
}
