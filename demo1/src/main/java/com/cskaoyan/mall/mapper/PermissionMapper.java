package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.SystemPermissions;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> selectPermissionById(Integer roleId);

    List<String> selectAllPermissions();

    List<SystemPermissions> selectAllSystemPermissions();

    void deletePermissionsByRoleId(@Param("time") Date time,@Param("roleId") int roleId);

    void addPermissions(@Param("roleId") int roleId,@Param("time") Date time,@Param("permission") String permission);

    String selectApiByPermission(String permission);
}
