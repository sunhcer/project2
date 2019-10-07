package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.StatUser;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.vo.UserPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAllUsers(UserPage userPage);

    int queryUserNum();

    StatUser[] getStatUsers();


    int queryUserIdByUsername(@Param("username") String username);

    String selectPasswordByUsername(String username);

    User selectUserByUsername(String username);

    User selectUserByMobile(String mobile);
}
