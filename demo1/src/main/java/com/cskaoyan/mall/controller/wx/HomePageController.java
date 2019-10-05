package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.wx.WxHomePageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxCouponPage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class HomePageController {

    @Autowired
    WxHomePageService wxHomePageService;
    @RequestMapping("/coupon/list")
    public BaseRespVo couponList(@RequestParam int page, @RequestParam int size){
        BaseRespVo baseRespVo=wxHomePageService.couponList(page,size);
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getId());
        return baseRespVo;
    }
    @RequestMapping("/coupon/receive")
    public BaseRespVo couponReceive(@RequestBody CouponUser couponUser){
        int couponId=couponUser.getCouponId();
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getId());
        String username=(String) session.getAttribute("username");
        BaseRespVo baseRespVo=wxHomePageService.couponReceive(couponId,username);
        return baseRespVo;
    }

}
