package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.OrderPage;

import java.util.List;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> selectGrouponRulesByCondition(OrderPage orderPage);
}
