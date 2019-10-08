package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.vo.UserPage;
import com.cskaoyan.mall.vo.WxFoot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootprintMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    Footprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    List<Footprint> queryAllFoot(UserPage userPage);

    List<WxFoot> queryMyFoot(@Param("userId") int userId);
}
