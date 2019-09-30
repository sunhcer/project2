package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.vo.AdminInfo;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);
    //增加管理员
    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    boolean insertAdmin(AdminInfo adminInfo);
}
