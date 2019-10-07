package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.*;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 20:55
 */
public interface GrouponService {
    BrandList<GrouponBean> getGrouponList(OrderPage orderPage);

    void updateGroupon(Groupon groupon);

    boolean insertGroupon(Groupon groupon);

    void deleteGroup(Groupon groupon);

    List<GrouponRules> findGrouponRuleList(Integer start, Integer end);

    List<GrouponRules> findGrouponRuleListByGoodsId(int goodsId);
}
