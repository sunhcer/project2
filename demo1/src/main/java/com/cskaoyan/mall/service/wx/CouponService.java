package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.vo.WxCouponInfo;
import com.cskaoyan.mall.vo.WxCouponPage;

import java.util.List;


public interface CouponService {

    WxCouponInfo queryMyCouponList(WxCouponPage wxCouponPage, int userId);

    String exchangeCoupon(String code,int userId);

    List<Coupon> selectCoupons();
}
