package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.RolePermissionInfo;
import com.cskaoyan.mall.bean.SystemPermissions;

import java.util.List;

public interface PermissionService {
    List<SystemPermissions> selectAllSystemPermissions();

    List<String> selectAllPermissions();

    List<String> selectAllPermissionsById(Integer roleId);

    void setPermissions(RolePermissionInfo rolePermissionInfo);
}
