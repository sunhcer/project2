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
}
