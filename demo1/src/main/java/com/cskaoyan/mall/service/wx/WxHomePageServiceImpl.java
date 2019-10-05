package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.CouponArray;
import com.cskaoyan.mall.mapper.CouponMapper;
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
}
