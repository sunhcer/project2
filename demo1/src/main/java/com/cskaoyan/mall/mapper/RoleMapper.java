package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Options;
import com.cskaoyan.mall.bean.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Options> selectAllOptions();

    List<Role> selectAllOptionsDesc();

    List<Role> selectOptionByName(String name);

    Role selectRoleByName(String name);
}
