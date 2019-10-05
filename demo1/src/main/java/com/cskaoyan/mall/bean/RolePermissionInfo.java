package com.cskaoyan.mall.bean;

import java.util.List;

public class RolePermissionInfo {

    private int roleId;

    private List<String> permissions;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
