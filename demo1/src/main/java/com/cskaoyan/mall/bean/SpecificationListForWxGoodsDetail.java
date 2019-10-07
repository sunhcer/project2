package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-07 17:09
 *
 * @author EGGE
 */
public class SpecificationListForWxGoodsDetail {
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpecificationListForWxGoodsDetail(String name, List<GoodsSpecification> valueList) {
        this.name = name;
        this.valueList = valueList;
    }

    public SpecificationListForWxGoodsDetail() {
    }

    public List<GoodsSpecification> getValueList() {
        return valueList;
    }

    public void setValueList(List<GoodsSpecification> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "SpecificationListForWxGoodsDetail{" +
                "name='" + name + '\'' +
                ", valueList=" + valueList +
                '}';
    }

    private List<GoodsSpecification> valueList;
}
