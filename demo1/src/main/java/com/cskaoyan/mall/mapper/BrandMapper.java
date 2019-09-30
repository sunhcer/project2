package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.vo.CatAndBrandVo;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<CatAndBrandVo> findAllBrand();

    List<Brand> findAllBrandDetail();

}
