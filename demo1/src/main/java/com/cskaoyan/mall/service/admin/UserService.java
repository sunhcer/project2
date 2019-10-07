package com.cskaoyan.mall.service.admin;


import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.*;

public interface UserService {

    UsersListInfo selectAllUsers(UserPage userPage);

    UsersAddressInfo selectUsersAddress(UserPage userPage);

    UsersCollectInfo selectUsersCollect(UserPage userPage);

    UsersFootInfo selectUsersFoot(UserPage userPage);

    UserHistoryInfo selectUsersHistory(UserPage userPage);

    UserFeedbackInfo selectUsersFeedback(UserPage userPage);

    User selectUserInfoByUsername(String username);

    boolean registertUser(User user);
}
