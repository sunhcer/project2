package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 14:07
 *
 * @author EGGE
 */
@Service
public class ProductServiceImpl implements  ProductService {
    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<CatAndBrandVo> findAllBrand() {
        return brandMapper.findAllBrand();
    }

    @Override
    public List<CatAndBrandVo> findAllCategories() {
        return categoryMapper.findAllCategories();
    }
}
