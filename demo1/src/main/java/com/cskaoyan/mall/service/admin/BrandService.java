package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.BrandPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 14:50
 */
public interface BrandService {
    /**
     * 获取品牌
     * @param page
     * @return
     */
    BrandList getBrandList(BrandPage page);

    void updateBrand(Brand brand);

    void deleteBrandById(Integer id);

    void insertBrand(Brand brand);
}
