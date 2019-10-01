package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OptionListInfo;
import com.cskaoyan.mall.bean.Options;

import java.util.List;


public interface RoleService {
    List<Options> selectAllOptions();

    OptionListInfo selectAllOptionsDesc(int page, int limit, String username, String sort, String order);
}
