package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Options> selectAllOptions() {
        List<Options> options = roleMapper.selectAllOptions();
        return options;
    }

    @Override
    public OptionListInfo selectAllOptionsDesc(int page, int limit, String name, String sort, String order) {
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Role> roles;
        if(name == null) {
            roles = roleMapper.selectAllOptionsDesc();
        } else {
            name = "%" + name + "%";
            roles = roleMapper.selectOptionByName(name);
        }

        OptionListInfo optionListInfo = new OptionListInfo();
        optionListInfo.setItems(roles);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long total = rolePageInfo.getTotal();
        optionListInfo.setTotal(total);
        return optionListInfo;
    }
}
