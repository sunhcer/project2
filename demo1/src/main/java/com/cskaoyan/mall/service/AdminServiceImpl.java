package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminListInfo;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminListInfo selectAllAdmin(int page,int limit,String username, String sort,String order) {
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Admin> admins;
        if(username == null) {
            admins = adminMapper.selectAllAdmin();
        } else {
            username = "%" + username + "%";
            admins = adminMapper.selectAdminByName(username);
        }

        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        AdminListInfo adminListInfo = new AdminListInfo();

        adminListInfo.setItems(admins);
        adminListInfo.setTotal(total);

        return adminListInfo;
    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.insertSelective(admin);
    }

    @Override
    public Admin selectAdminById(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }


//
//    @Override
//    public void insertAdmin(AdminInfo adminInfo) {
//         adminMapper.insertAdmin(adminInfo);
//    }
}
