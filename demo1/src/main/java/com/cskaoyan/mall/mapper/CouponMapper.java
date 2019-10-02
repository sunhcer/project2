package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponArray;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    int insertCouponByAll(@Param("coupon") CouponArray coupon);

    CouponArray selectById(@Param("id") int id);

   void couponUpdate(@Param("coupon") CouponArray coupon);
}
