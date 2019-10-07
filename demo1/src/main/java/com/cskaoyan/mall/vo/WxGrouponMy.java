package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponGoodsList;
import com.cskaoyan.mall.bean.GrouponRules;

import java.util.List;

public class WxGrouponMy {
    String orderStatusText;
    String creator;
    Groupon groupon;
    int orderId;
    String orderSn;
    double actualPrice;
    int joinerCount;  //加入的人数
    List<GrouponGoodsList> goodsList;
    GrouponRules rules;
    int id;
    boolean isCreator;
    //handleOption;
}
