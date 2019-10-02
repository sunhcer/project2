package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 19:42
 */
@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public BrandList<GrouponRules> getGrouponRules(OrderPage orderPage) {
        PageHelper.startPage(orderPage.getPage(), orderPage.getLimit());
        if (orderPage.getOrder() != null && orderPage.getSort() != null){
            PageHelper.orderBy(orderPage.getSort() + " " + orderPage.getOrder());
        }
        List<GrouponRules> grouponList = grouponRulesMapper.selectGrouponRulesByCondition(orderPage);
        PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponList);
        BrandList<GrouponRules> rulesBrandList = new BrandList<>();
        rulesBrandList.setItems(grouponList);
        long total = grouponRulesPageInfo.getTotal();
        rulesBrandList.setTotal(total);
        return rulesBrandList;
    }

    @Override
    public void updateGroupRules(GrouponRules grouponRules) {
        int inserNum = grouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
    }

    @Override
    public boolean insertGrouponRules(GrouponRules grouponRules) {
        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());    //根据商品id查找商品
        if (goods == null){
            //商品不存在 代表插入失败
            return false;
        }
        grouponRules.setId(null);
        grouponRules.setAddTime(new Date());
        grouponRules.setUpdateTime(new Date());
        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        int inserNum = grouponRulesMapper.insertSelective(grouponRules);
        return true;    //插入成功
    }

    @Override
    public void deleteGroupRules(GrouponRules grouponRules) {
        grouponRules.setDeleted(true);
        grouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
    }
}
