package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponArray;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.vo.WxUserComment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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

    void couponDeleteById(@Param("id") int id);

    List<CouponUser> queryCouponUserByCouponId(@Param("couponId") int couponId);

    List<CouponUser> queryCouponUserPage(@Param("pagesize") int pagesize,@Param("offsetNum") int offsetNum,@Param("couponId") int couponId);

    List<CouponUser> queryCouponUserByCouponIdAndStatus(@Param("couponId") int couponId,@Param("status") int status);

    List<CouponUser> queryCouponUserPageByStatus(@Param("pagesize") int pagesize, @Param("offsetNum") int offsetNum,@Param("couponId") int couponId,@Param("status") int status);

    List<Coupon> findAllCoupon();


    List<Coupon> queryMyCouponList( @Param("status")int status,@Param("id")int id);

    Coupon queryExchangeCode(@Param("code") String code);

    int queryThisCodeCouponNum(@Param("id")int id,@Param("userId")int userId);

    int insertUserCoupon(@Param("userId")int userId,@Param("couponId")int couponId,
                         @Param("status")int status,@Param("startTime")Date startTime);

    List<CouponArray> queryWxCouponPage();


    int queryCouponLimitByCouponId(@Param("couponId") int couponId);

    Coupon queryCouponByCouponId(@Param("id") int id);

    void updateWxCouponTotal(@Param("restTotal") int restTotal,@Param("id") int id);

    }

