package com.cskaoyan.mall.bean;

import java.util.List;

public class PermissionInfo {
    private List<SystemPermissions> systemPermissions;

    private List<String> assignedPermissions;

    public List<SystemPermissions> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(List<SystemPermissions> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }

    public List<String> getAssignedPermissions() {
        return assignedPermissions;
    }

    public void setAssignedPermissions(List<String> assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }
}
