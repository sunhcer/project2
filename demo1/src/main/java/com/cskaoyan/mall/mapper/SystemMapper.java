package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.System;
import org.apache.ibatis.annotations.Param;


public interface SystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(System record);

    int insertSelective(System record);

    System selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);
    //查询
    String selectKey(String keyName);
    //修改
    void changeKey(@Param("key1") String key1, @Param("keyName") String keyName);
}
