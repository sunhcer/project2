package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 20:55
 */
@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BrandList<GrouponBean> getGrouponList(OrderPage orderPage) {
        PageHelper.startPage(orderPage.getPage(), orderPage.getLimit());
        if (orderPage.getOrder() != null && orderPage.getSort() != null) {
            PageHelper.orderBy(orderPage.getSort() + " " + orderPage.getOrder());
        }
        if ("".equals(orderPage.getGoodsId())) {
            orderPage.setGoodsId(null);
        }

        //根据商品id寻找订单id
        if (orderPage.getGoodsId() != null) {
            List<OrderGoods> orderList = orderGoodsMapper.selectOrderByGoodsId(orderPage.getGoodsId());
            List list = new ArrayList();
            if (orderList != null) {
                for (OrderGoods orderGoods : orderList) {
                    list.add(orderGoods.getOrderId());
                }
            }
            if (list.size() > 0) {
                orderPage.setOrderList(list);
            } else {
                orderPage.setOrderList(null);
            }
            logger.warn(Arrays.toString(list.toArray()));
        }

        List<Groupon> grouponList = grouponMapper.selectGrouponByCondition(orderPage);
        //要将其封装到GrouponBean封装到一起
        List<GrouponBean> grouponBeans = new ArrayList<>();
        for (Groupon groupon : grouponList) {
            GrouponBean grouponBean = new GrouponBean();
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
            Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
            if (goods != null) {
                grouponBean.setGoods(goods);
            }
            if (grouponRules != null) {
                grouponBean.setRules(grouponRules);
            }
            grouponBean.setGroupon(groupon);
            grouponBean.setSubGroupons(new ArrayList<>());
            grouponBeans.add(grouponBean);
        }
        PageInfo<Groupon> grouponPageInfo = new PageInfo<>(grouponList);
        BrandList<GrouponBean> grouponBrandList = new BrandList<>();
        grouponBrandList.setItems(grouponBeans);
        long total = grouponPageInfo.getTotal();
        grouponBrandList.setTotal(total);
        return grouponBrandList;
    }

    @Override
    public void updateGroupon(Groupon groupon) {

    }

    @Override
    public boolean insertGroupon(Groupon groupon) {
        return false;
    }

    @Override
    public void deleteGroup(Groupon groupon) {

    }

    @Override
    public List<GrouponRules> findGrouponRuleList(Integer start, Integer end) {
        return grouponRulesMapper.selectByAddTimeWithLimit(end-start,start);
    }
}
