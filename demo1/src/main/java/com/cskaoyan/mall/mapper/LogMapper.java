package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAllLogs();

    List<Log> selectLogsByName(String name);
}
