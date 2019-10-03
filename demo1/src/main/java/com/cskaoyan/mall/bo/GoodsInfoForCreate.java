package com.cskaoyan.mall.bo;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-02 15:19
 *
 * @author EGGE
 */
public class GoodsInfoForCreate {
    List<GoodsAttribute> attributes;
    Goods goods;
    List<GoodsProduct> products;
    List<GoodsSpecification> specifications;

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "GoodsInfoForCreate{" +
                "attributes=" + attributes +
                ", goods=" + goods +
                ", products=" + products +
                ", specifications=" + specifications +
                '}';
    }
}
