package com.cskaoyan.mall.service.admin;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminListInfo;
import com.cskaoyan.mall.vo.UserInfo;

import java.util.List;


public interface AdminService {
//    void insertAdmin(AdminInfo adminInfo);
    AdminListInfo selectAllAdmin(int page, int limit,String username, String sort, String order);

    void deleteAdminById(Integer id);

    void addAdmin(Admin admin);

    Admin selectAdminById(Integer id);

    void updateAdmin(Admin admin);

    List<String> selectPermissionsByName(String principal);

    List<String> selectRolesByName(String principal);

    Admin selectAdminByName(String principal);

    UserInfo getRoleMessage(String principal);
}
