package com.cskaoyan.mall.vo;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 15:01
 *
 * @author EGGE
 */
public class CatAndBrandVo {
    Integer value;
    String label;
    List<CatAndBrandVo> children;

    public List<CatAndBrandVo> getChildren() {
        return children;
    }

    public void setChildren(List<CatAndBrandVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CatAndBrandVo{" +
                "value=" + value +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
