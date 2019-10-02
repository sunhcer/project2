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
    List<GoodsAttribute> goodsAttributes;
    Goods goods;
    List<GoodsProduct> goodsProducts;
    List<GoodsSpecification> goodsSpecifications;

    public List<GoodsAttribute> getGoodsAttributes() {
        return goodsAttributes;
    }

    public void setGoodsAttributes(List<GoodsAttribute> goodsAttributes) {
        this.goodsAttributes = goodsAttributes;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsProduct> getGoodsProducts() {
        return goodsProducts;
    }

    public void setGoodsProducts(List<GoodsProduct> goodsProducts) {
        this.goodsProducts = goodsProducts;
    }

    public List<GoodsSpecification> getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(List<GoodsSpecification> goodsSpecifications) {
        this.goodsSpecifications = goodsSpecifications;
    }

    @Override
    public String toString() {
        return "GoodsInfoForCreate{" +
                "goodsAttributes=" + goodsAttributes +
                ", goods=" + goods +
                ", goodsProducts=" + goodsProducts +
                ", goodsSpecifications=" + goodsSpecifications +
                '}';
    }
}
