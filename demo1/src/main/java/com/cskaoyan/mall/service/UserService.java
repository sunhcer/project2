package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;

public interface UserService {
    StatUser[] getStatUsers();
    StatOrder[] getStatOrders();
    StatGood[] getStatGoods();
}
