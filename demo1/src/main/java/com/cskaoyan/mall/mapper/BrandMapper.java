package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandPage;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<CatAndBrandVo> findAllBrandToVo();

    List<Brand> findAllBrandDetail();

    void deleteById(Integer id);

    List<Brand> selectByCondition(BrandPage page);

    int queryAllWxBrandAmount();

    List<Brand> queryWxBrandPage(@Param("pageSize") int size,@Param("offsetNum") int offsetNum);

    Brand  queryWxBrandById(@Param("id") int id);
}
