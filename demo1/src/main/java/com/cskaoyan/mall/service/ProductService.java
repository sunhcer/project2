package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.vo.CatAndBrandVo;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 14:07
 *
 * @author EGGE
 */
public interface ProductService {
    List<CatAndBrandVo> findAllBrandToVo();

    List<CatAndBrandVo> findAllCategoriesToVo();
}
