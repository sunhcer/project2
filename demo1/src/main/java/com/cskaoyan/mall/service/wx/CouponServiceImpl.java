package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.vo.WxCouponInfo;
import com.cskaoyan.mall.vo.WxCouponPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponMapper couponMapper;

    @Override
    public WxCouponInfo queryMyCouponList(WxCouponPage wxCouponPage, int userId) {
        int page = wxCouponPage.getPage();
        int size = wxCouponPage.getSize();
        int status = wxCouponPage.getStatus();
        PageHelper.startPage(page,size);
        //先定userid 是100
        List<Coupon> list = couponMapper.queryMyCouponList(status, 100);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(list);
        long total = couponPageInfo.getTotal();
        WxCouponInfo couponInfo = new WxCouponInfo();
        couponInfo.setCount(total);
        couponInfo.setData(list);
        return couponInfo;

    }

    @Override
    public String exchangeCoupon(String code, int userId) {
        //首先要兑换
        //1  查找是否有对应字段  有 查看优惠券的limit 如果是1  查看有几张券 如果有 返回 已兑换
        //                                          x>1  查看有几张y   x>y     成功
        //                                                            x<y     已兑换
        //
        //2  查找是否有对应字段  无  返回优惠券不正确
        Coupon coupon = couponMapper.queryExchangeCode(code);
        Integer id = coupon.getId();
        if(id==null){
            return "优惠券不正确";
        }else {         //找到了相应的字段
            //查看该优惠券能兑换的次数
            Short limit = coupon.getLimit();
            //查看该用户拥有多少该优惠券
            int i = couponMapper.queryThisCodeCouponNum(id, userId);
            if (limit==i){
                return "优惠券已兑换";
            }else if(limit>i){
                //兑换成功还要插入
                int i1 = couponMapper.insertUserCoupon(userId,id,0, new Date());
                return "成功";
            }
        }
        return null;
    }
}
