package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.BrandPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 14:50
 */
public interface BrandService {
    BrandList getBrandList(BrandPage page);
}
