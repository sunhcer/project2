package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Storage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    List<Ad> queryAllAd();

    List<Ad> queryCurrentPageAdByPageAndLimit(@Param("limit") int limit,@Param("offsetNum") int offsetNum);

    int insertAdImage(@Param("storage") Storage storage);

    int addAd(@Param("ad")Ad ad);
}
