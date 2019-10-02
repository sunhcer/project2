package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminListInfo;



public interface AdminService {
//    void insertAdmin(AdminInfo adminInfo);
    AdminListInfo selectAllAdmin(int page, int limit,String username, String sort, String order);

    void deleteAdminById(Integer id);

    void addAdmin(Admin admin);

    Admin selectAdminById(Integer id);

    void updateAdmin(Admin admin);
}
