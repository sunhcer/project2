package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.bean.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public void insertAdmin(AdminInfo adminInfo) {
         adminMapper.insertAdmin(adminInfo);
    }
}
