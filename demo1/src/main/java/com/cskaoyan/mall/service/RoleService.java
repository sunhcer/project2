package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OptionListInfo;
import com.cskaoyan.mall.bean.Options;
import com.cskaoyan.mall.bean.Role;

import java.util.List;


public interface RoleService {
    List<Options> selectAllOptions();

    OptionListInfo selectAllOptionsDesc(int page, int limit, String username, String sort, String order);

    void addRole(Role role);

    Role selectRoleByName(String name);

    void updateRole(Role role);

    void deleteRole(Role role);
}
