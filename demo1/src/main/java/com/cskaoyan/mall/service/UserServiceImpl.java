package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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



//    username:
//    mobile:
    @Override
    public UsersListInfo selectAllUsers(UserPage userPage) {
        if(userPage.getUsername()!=null&&"".equals(userPage.getUsername().trim())){
            userPage.setUsername(null);
        }
        if(userPage.getMobile()!=null&&"".equals(userPage.getMobile().trim())){
            userPage.setMobile(null);
        }
        int page=userPage.getPage();
        int limit=userPage.getLimit();
        PageHelper.startPage(page,limit);
        List<User> users = userMapper.queryAllUsers(userPage);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        UsersListInfo usersListInfo = new UsersListInfo();
        usersListInfo.setItems(users);
        usersListInfo.setTotal(total);
        return usersListInfo;
    }

//    name: 1
//    userId: 1
    @Override
    public UsersAddressInfo selectUsersAddress(UserPage userPage) {
        if(userPage.getName()!=null&&"".equals(userPage.getName().trim())){
            userPage.setName(null);
        }
        if(userPage.getUserId()!=null&&"".equals(userPage.getUserId().trim())){
            userPage.setUserId(null);
        }
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

//    userId: 1
//    valueId: 1
    @Override
    public UsersCollectInfo selectUsersCollect(UserPage userPage) {
        if(userPage.getValueId()!=null&&"".equals(userPage.getValueId().trim())){
            userPage.setValueId(null);
        }
        if(userPage.getUserId()!=null&&"".equals(userPage.getUserId().trim())){
            userPage.setUserId(null);
        }
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


//    userId: 1
//    goodsId: 1
    @Override
    public UsersFootInfo selectUsersFoot(UserPage userPage) {
        if(userPage.getGoodsId()!=null&&"".equals(userPage.getGoodsId().trim())){
            userPage.setGoodsId(null);
        }
        if(userPage.getUserId()!=null&&"".equals(userPage.getUserId().trim())){
            userPage.setUserId(null);
        }
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

//    userId: 1
//    keyword: 1
    @Override
    public UserHistoryInfo selectUsersHistory(UserPage userPage) {
        if(userPage.getKeyword()!=null&&"".equals(userPage.getKeyword().trim())){
            userPage.setKeyword(null);
        }
        if(userPage.getUserId()!=null&&"".equals(userPage.getUserId().trim())){
            userPage.setUserId(null);
        }
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

//    username
//    id
    @Override
    public UserFeedbackInfo selectUsersFeedback(UserPage userPage) {
        if(userPage.getUsername()!=null&&"".equals(userPage.getUsername().trim())){
            userPage.setUsername(null);
        }
        if(userPage.getId()!=null&&"".equals(userPage.getId().trim())){
            userPage.setId(null);
        }
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
