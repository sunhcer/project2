package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.CouponUser;
import org.apache.ibatis.annotations.Param;

public interface CouponUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);

    CouponUser queryCouponUserByUserCouponId(@Param("userId") int userId,@Param("couponId") int couponId);
}
