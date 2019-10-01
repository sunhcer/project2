package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Coupon;
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

    List<Ad> queryPageListAdByPageAndLimit(@Param("limit") int limit, @Param("offsetNum") int offsetNum, @Param("name") String name, @Param("content") String content);

    void updateAdById(@Param("ad") Ad ad);

    List<Ad> queryAllPageListAdByPageAndLimit(@Param("name") String name,@Param("content") String content);

    int addAdWithoutLink(@Param("ad") Ad ad);

    int selectCouponAmount();

    List<Coupon> queryCurrentPageCouponByPageAndLimit(@Param("limit") int limit, @Param("offsetNum") int offsetNum);

    int selectLikeCouponAmount(@Param("name") String name,@Param("type1") int type1,@Param("status1") int status1);

    List<Coupon> selectLikeCouponPage(@Param("name") String name,@Param("type1") int type1,@Param("status1") int status1,@Param("limit") int limit,@Param("offsetNum") int offsetNum);
}
