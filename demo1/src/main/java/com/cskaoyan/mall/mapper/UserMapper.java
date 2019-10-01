package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.vo.UserPage;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

//    @Select("select id,username,password,gender,birthday,last_login_time as lastLoginTime,last_login_ip as lastLoginIp," +
//            "user_level as userLevel,nickname,mobile,avatar,weixin_openid as weixinOpenid," +
//            "status,add_time as addTime,update_time as updateTime,deleted from cskaoyan_mall_user")
    List<User> queryAllUsers(UserPage userPage);

    int queryUserNum();
}
