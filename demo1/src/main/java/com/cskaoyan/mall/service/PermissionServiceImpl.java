package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.RolePermissionInfo;
import com.cskaoyan.mall.bean.SystemPermissions;
import com.cskaoyan.mall.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<SystemPermissions> selectAllSystemPermissions() {
        List<SystemPermissions> systemPermissions = permissionMapper.selectAllSystemPermissions();
        return systemPermissions;
    }

    @Override
    public List<String> selectAllPermissions() {
        List<String> permissions = permissionMapper.selectAllPermissions();
        return permissions;
    }

    @Override
    public List<String> selectAllPermissionsById(Integer roleId) {
        List<String> permissions = permissionMapper.selectPermissionById(roleId);
//        List<String> apiList = new ArrayList<>();
//        for (String permission : permissions) {
//            String api = permissionMapper.selectApiByPermission(permission);
//            apiList.add(api);
//        }
//        return apiList;
        return permissions;
    }

    @Override
    public void setPermissions(RolePermissionInfo rolePermissionInfo) {
        int roleId = rolePermissionInfo.getRoleId();
        List<String> permissions = rolePermissionInfo.getPermissions();
        permissionMapper.deletePermissionsByRoleId(new Date(),roleId);
        for (String permission : permissions) {
            permissionMapper.addPermissions(roleId,new Date(),permission);
        }
    }
}
