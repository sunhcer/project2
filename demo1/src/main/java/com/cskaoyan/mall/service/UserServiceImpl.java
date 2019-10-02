package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public UsersListInfo selectAllUsers(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
//        String username ="%"+ userPage.getUsername()+"%";
//        userPage.setUsername(username);
        List<User> users = userMapper.queryAllUsers(userPage);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        UsersListInfo usersListInfo = new UsersListInfo();
        usersListInfo.setItems(users);
        usersListInfo.setTotal(total);
        return usersListInfo;
    }

    @Override
    public UsersAddressInfo selectUsersAddress(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<UserAddress> list = addressMapper.queryUsersAddress(userPage);
        PageInfo<UserAddress> userAddressPageInfo = new PageInfo<>(list);
        long total = userAddressPageInfo.getTotal();
        UsersAddressInfo usersAddressInfo = new UsersAddressInfo();
        usersAddressInfo.setTotal(total);
        usersAddressInfo.setItems(list);
        return usersAddressInfo;
    }

    @Override
    public UsersCollectInfo selectUsersCollect(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<UserCollect> list = collectMapper.queryAllCollect(userPage);
        PageInfo<UserCollect> userCollectPageInfo = new PageInfo<>(list);
        long total = userCollectPageInfo.getTotal();
        UsersCollectInfo usersCollectInfo = new UsersCollectInfo();
        usersCollectInfo.setTotal(total);
        usersCollectInfo.setItems(list);
        return usersCollectInfo;
    }

    @Override
    public UsersFootInfo selectUsersFoot(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<Footprint> list = footprintMapper.queryAllFoot(userPage);
        PageInfo<Footprint> footprintPageInfo = new PageInfo<>(list);
        long total = footprintPageInfo.getTotal();
        UsersFootInfo usersFootInfo = new UsersFootInfo();
        usersFootInfo.setItems(list);
        usersFootInfo.setTotal(total);
        return usersFootInfo;
    }

    @Override
    public UserHistoryInfo selectUsersHistory(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<SearchHistory> list = searchHistoryMapper.queryAllHistory(userPage);
        PageInfo<SearchHistory> searchHistoryPageInfo = new PageInfo<>(list);
        long total = searchHistoryPageInfo.getTotal();
        UserHistoryInfo userHistoryInfo = new UserHistoryInfo();
        userHistoryInfo.setTotal(total);
        userHistoryInfo.setItems(list);
        return userHistoryInfo;
    }

    @Override
    public UserFeedbackInfo selectUsersFeedback(UserPage userPage) {
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<Feedback> list = feedbackMapper.queryAllFeedback(userPage);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(list);
        long total = feedbackPageInfo.getTotal();
        UserFeedbackInfo userFeedbackInfo = new UserFeedbackInfo();
        userFeedbackInfo.setItems(list);
        userFeedbackInfo.setTotal(total);
        return  userFeedbackInfo;
    }

}
