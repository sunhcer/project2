package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);
    //增加管理员
//    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectAllAdmin();

    List<Admin> selectAdminByNameLike(String username);


//    boolean insertAdmin(AdminInfo adminInfo);

    String queryPasswordByUsername(String principal);

  //  List<String> queryPermissionsByUsername(String primaryPrincipal);

    Admin selectAdminByName(String principal);

}
