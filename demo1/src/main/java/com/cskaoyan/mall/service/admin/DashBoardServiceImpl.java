package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardServiceImpl implements DashBoardService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int queryGoodsTotal() {
        Integer amountOfGoods = goodsMapper.findAmountOfGoods();
        return amountOfGoods;
    }

    @Override
    public int queryUserTotal() {
        int i = userMapper.queryUserNum();
        return i;
    }

    @Override
    public int queryProductTotal() {
        Integer amountOfProducts = goodsProductMapper.findAmountOfProducts();
        return amountOfProducts;
    }

    @Override
    public int queryOrderTotal() {
        Integer amountOfOrders = orderMapper.findAmountOfOrders();
        return amountOfOrders;
    }
}
