package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.UsersListInfo;
import com.cskaoyan.mall.vo.*;

public interface UserService {

    UsersListInfo selectAllUsers(UserPage userPage);

    UsersAddressInfo selectUsersAddress(UserPage userPage);

    UsersCollectInfo selectUsersCollect(UserPage userPage);

    UsersFootInfo selectUsersFoot(UserPage userPage);

    UserHistoryInfo selectUsersHistory(UserPage userPage);

    UserFeedbackInfo selectUsersFeedback(UserPage userPage);

}
