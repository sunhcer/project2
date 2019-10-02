package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.OrderPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 19:42
 */
public interface GrouponRulesService {
    BrandList<GrouponRules> getGrouponRules(OrderPage orderPage);

    void updateGroupRules(GrouponRules grouponRules);

    boolean insertGrouponRules(GrouponRules grouponRules);

    void deleteGroupRules(GrouponRules grouponRules);
}
