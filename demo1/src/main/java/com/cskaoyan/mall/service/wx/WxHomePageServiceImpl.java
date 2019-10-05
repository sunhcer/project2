package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponArray;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxCouponPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxHomePageServiceImpl implements WxHomePageService {

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CouponUserMapper couponUserMapper;

    @Override
    public BaseRespVo couponList(int page, int size) {
        PageHelper.startPage(page,size);
        List<CouponArray> list=couponMapper.queryWxCouponPage();
        PageInfo<CouponArray> couponArrayPageInfo = new PageInfo<>(list);
        int count=(int)couponArrayPageInfo.getTotal();
        WxCouponPage wxCouponPage = new WxCouponPage(list, count);
        BaseRespVo success = BaseRespVo.success(wxCouponPage);
        return success;
    }

    @Override
    public BaseRespVo couponReceive(int couponId, String username) {
        //根据username找出userId
        int userId=userMapper.queryUserIdByUsername(username);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        //根据userId和couponId找到CouponUser
        CouponUser couponUser= couponUserMapper.queryCouponUserByUserCouponId(userId,couponId);
        if (couponUser!=null){
            objectBaseRespVo.setErrno(0);
            objectBaseRespVo.setErrmsg("成功");
            return objectBaseRespVo;
        }else{
            objectBaseRespVo.setErrno(740);
            objectBaseRespVo.setErrmsg("优惠券已经领取过");
            return objectBaseRespVo;
        }
    }
}
