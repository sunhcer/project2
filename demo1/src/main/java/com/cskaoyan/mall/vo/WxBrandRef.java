package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Brand;

public class WxBrandRef {
    Brand brand;

    @Override
    public String toString() {
        return "WxBrandRef{" +
                "brand=" + brand +
                '}';
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public WxBrandRef() {
    }

    public WxBrandRef(Brand brand) {
        this.brand = brand;
    }
}
