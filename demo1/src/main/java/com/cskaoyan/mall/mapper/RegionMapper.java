package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<Region> selectAllProvince();

    //查省
    List<Region> selectAllProvince2(@Param("pid")Integer pid);

    String selectAddressByCode(Integer provinceId);
}
