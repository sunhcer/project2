package com.cskaoyan.mall.bean;

import java.util.List;

public class SystemPermissions {
    private String id;
    private String label;
    private String api;
    private List<SystemPermissions> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<SystemPermissions> getChildren() {
        return children;
    }

    public void setChildren(List<SystemPermissions> children) {
        this.children = children;
    }
}
