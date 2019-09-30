package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminDesc;
import com.cskaoyan.mall.bean.AdminListInfo;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        ArrayList<AdminDesc> adminDescs = new ArrayList<>();
        for (Admin admin : admins) {
            AdminDesc adminDesc = new AdminDesc();
            adminDesc.setId(admin.getId());
            adminDesc.setAvatar(admin.getAvatar());
            adminDesc.setUsername(admin.getUsername());
            String roleIds = admin.getRoleIds();
            ArrayList<Integer> roleId = new ArrayList<>();
            if(roleIds.equals("[1]")) {
                roleId.add(1);
                adminDesc.setRoleIds(roleId);
            } else if(roleIds.equals("[2]")) {
                roleId.add(2);
                adminDesc.setRoleIds(roleId);
            } else if(roleIds.equals("[3]")) {
                roleId.add(3);
                adminDesc.setRoleIds(roleId);
            }
            adminDescs.add(adminDesc);
        }

        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        AdminListInfo adminListInfo = new AdminListInfo();
        adminListInfo.setItems(adminDescs);
        adminListInfo.setTotal(total);
        return adminListInfo;
    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }


//
//    @Override
//    public void insertAdmin(AdminInfo adminInfo) {
//         adminMapper.insertAdmin(adminInfo);
//    }
}
