package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

public interface GoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    List<GoodsSpecification> findGoodsSpecificationsByGoodsId(int goodsId);
}
