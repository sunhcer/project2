package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.UsersListInfo;
import com.cskaoyan.mall.vo.*;
import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;

public interface UserService {

    UsersListInfo selectAllUsers(UserPage userPage);

    UsersAddressInfo selectUsersAddress(UserPage userPage);

    UsersCollectInfo selectUsersCollect(UserPage userPage);

    UsersFootInfo selectUsersFoot(UserPage userPage);

    UserHistoryInfo selectUsersHistory(UserPage userPage);

    UserFeedbackInfo selectUsersFeedback(UserPage userPage);

    StatUser[] getStatUsers();
    StatOrder[] getStatOrders();
    StatGood[] getStatGoods();
}
