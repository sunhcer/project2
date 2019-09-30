package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public StatUser[] getStatUsers() {
        StatUser[] statUsers = userMapper.getStatUsers();
        return statUsers;
    }

    @Override
    public StatOrder[] getStatOrders() {
        return new StatOrder[0];
    }

    @Override
    public StatGood[] getStatGoods() {
        return new StatGood[0];
    }
}
