package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.service.wx.WxHomePageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxCouponPage;
import org.springframework.beans.factory.annotation.Autowired;
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
        return baseRespVo;
    }


}
