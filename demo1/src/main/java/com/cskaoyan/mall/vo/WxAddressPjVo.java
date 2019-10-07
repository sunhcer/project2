package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Address;

public class WxAddressPjVo {
    String detailedAddress;
    int id;
    boolean isDefault;
    String mobile;
    String name;

    public WxAddressPjVo(Address address) {
        this.detailedAddress = address.getProvince()+address.getCity()+address.getArea()+" "+address.getAddress();
        this.id = address.getId();
        this.isDefault = address.getIsDefault();
        this.mobile =address.getMobile();
        this.name =address.getName();
    }



    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
