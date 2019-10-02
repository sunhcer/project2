package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<OrderGoods> selectByOrderId(int id);

    List<OrderGoods> selectOrderByGoodsId(Integer goodsId);
}
