package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.AdService;
import com.cskaoyan.mall.vo.AdPageList;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class AdController {
    @Autowired
    AdService adService;

    //推广管理--首页--只有四个字段
    //推广管理--模糊查询--至少五个字段,默认会提交一个content
    @RequestMapping("/ad/list")
    public BaseRespVo adList(AdReceive adReceive){
        //对name和content判null,如果同时为null则为四个字段的首页请求
        int page=adReceive.getPage();
        int limit=adReceive.getLimit();
        if (adReceive.getName()==null&&adReceive.getContent()==null){
            page=adReceive.getPage();
            limit=adReceive.getLimit();
            int total=adService.findTotalOfAllPage();
        List<Ad> adList=adService.refAdPageList(page,limit);
        if (adList!=null) {
            AdPageList adPageList = new AdPageList(total, adList);
            BaseRespVo success = BaseRespVo.success(adPageList);
            return success;
        }else{
            return null;
        }
        }else{
            //否则为五个字段以上的模糊查询请求
            List<Ad> list=adService.queryAdPageList(adReceive);
            if (list!=null) {
                int total=adService.findTotalOfLikePage(adReceive);
                AdPageList adPageList = new AdPageList(total, list);
                BaseRespVo success = BaseRespVo.success(adPageList);
                return success;
            }else{
                return null;
            }
        }
    }

    //推广管理--添加广告--图片上传
    @RequestMapping("/storage/create")
    public BaseRespVo createAdImage(MultipartFile file) throws IOException {
        //前台有判空校验,所以直接用
        BaseRespVo<Storage> baseRespVo=adService.createAdImage(file);
        return baseRespVo;
    }

    //推广管理--添加广告
    @RequestMapping("/ad/create")
    public BaseRespVo<Ad> addAd(@RequestBody Ad ad){
        //前端添加广告某一栏为空就无法发起请求,所以这里不对ad做空值校验
        BaseRespVo<Ad> adBaseRespVo1=adService.addAd(ad);
        return adBaseRespVo1;
    }
    //推广管理--更新广告
    @RequestMapping("/ad/update")
    public BaseRespVo<Ad> updateAd(@RequestBody Ad ad){
        BaseRespVo<Ad> baseRespVo=adService.updateAd(ad);
        return baseRespVo;
    }
    //推广管理--删除广告
    @RequestMapping("/ad/delete")
    public  BaseRespVo<Ad> deleteAd(@RequestBody Ad ad){
        BaseRespVo<Ad> baseRespVo=adService.deleteAd(ad.getId());
        return baseRespVo;
    }

    //推广管理--优惠劵显示和模糊查询
    @RequestMapping("/coupon/list")
    public BaseRespVo refCouponList(CouponReceive receive){
        BaseRespVo baseRespVo=null;
        int page = receive.getPage();
        int limit = receive.getLimit();
        //初始化的时候给定一个值用以区分类型0和状态0
        if (receive.getName()==null&&receive.getStatus()==100&&receive.getType()==100){
            //显示所有
           baseRespVo=adService.findAllCoupon(page,limit);
           return baseRespVo;
        }else{
            //模糊查询
            baseRespVo=adService.findLikeCouponByReceive(receive);
            return baseRespVo;
        }
    }

    //推广管理---创建优惠劵
    //接收不到数据--原因一:日期,原因二:数组
    @RequestMapping("/coupon/create")
    public BaseRespVo createCoupon(@RequestBody CouponArray coupon){
        BaseRespVo baseRespVo=adService.createCoupon(coupon);
        return baseRespVo;
    }

    //推广管理--优惠劵详情
    @RequestMapping("/coupon/read")
    public BaseRespVo couponRead(int id){
        BaseRespVo baseRespVo=adService.couponRead(id);
        return baseRespVo;
    }

    //推广管理--优惠劵更新;
    @RequestMapping("/coupon/update")
    public BaseRespVo couponUpdate(@RequestBody CouponArray coupon){
        BaseRespVo baseRespVo=adService.couponUpdate(coupon);
        return baseRespVo;
    }

    //推广管理--优惠劵删除
    @RequestMapping("/coupon/delete")
    public BaseRespVo  couponDelete(CouponArray coupon){
        int id=coupon.getId();
        BaseRespVo baseRespVo=adService.couponDelete(id);
        return baseRespVo;
    }
}
