package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.System;

import java.util.Date;


public interface SystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(System record);

    int insertSelective(System record);

    System selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);
    //查询
    String selectKey(int id);
    //修改
    String changeKey(String key1, int id);
}
