package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.wx.CouponService;
import com.cskaoyan.mall.service.wx.WxCollectService;
import com.cskaoyan.mall.service.wx.WxFootPrintService;
import com.cskaoyan.mall.service.wx.WxGrouponService;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WxCoreServiceController {

    @Autowired
    CouponService couponService;

    @Autowired
    WxCollectService wxCollectService;

    @Autowired
    WxFootPrintService wxFootPrintService;

    @Autowired
    WxGrouponService wxGrouponService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("wx/coupon/mylist")
    public BaseRespVo wxCouponMyList(WxCouponPage wxcouponPage, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
//        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
//        Integer userId = UserTokenManager.getUserId(tokenKey);
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        //先写userId
        WxCouponInfo couponInfo = couponService.queryMyCouponList(wxcouponPage, userId);
        BaseRespVo<WxCouponInfo> wxCouponInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = wxCouponInfoBaseRespVo.success(couponInfo);
        return success;
    }

    @RequestMapping("wx/coupon/exchange")
    public BaseRespVo wxCouponExchange(@RequestBody WxCouponPage wxCouponPage, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
     //   String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
     //   Integer userId = UserTokenManager.getUserId(tokenKey);
        //先写userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        String code = wxCouponPage.getCode();
        String message = couponService.exchangeCoupon(code, 100);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        if(message.equals("成功")){
            objectBaseRespVo.setErrmsg("成功");
            objectBaseRespVo.setErrno(0);
        }else if(message.equals("优惠券不正确")){
            objectBaseRespVo.setErrmsg("优惠券不正确");
            objectBaseRespVo.setErrno(742);
        }else if(message.equals("优惠券已兑换")){
            objectBaseRespVo.setErrmsg("优惠券已兑换");
            objectBaseRespVo.setErrno(740);
        }
        return objectBaseRespVo;
    }

    @RequestMapping("wx/collect/list")
    public BaseRespVo wxCollectList(WxCollectPage wxCollectPage, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
    //    String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
    //    Integer userId = UserTokenManager.getUserId(tokenKey);
        //先写userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxCollectInfo wxCollectInfo = wxCollectService.queryMyCollect(wxCollectPage, userId);
        BaseRespVo<WxCollectInfo> wxCollectInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = wxCollectInfoBaseRespVo.success(wxCollectInfo);
        return success;
    }

    @RequestMapping("wx/footprint/list")
    public BaseRespVo wxFootPrintList(WxCollectPage wxCollectPage, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
    //    String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
    //    Integer userId = UserTokenManager.getUserId(tokenKey);
        //先写userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxFootInfo wxFootInfo = wxFootPrintService.queryMyFoot(wxCollectPage, userId);
        BaseRespVo<WxFootInfo> wxFootInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = wxFootInfoBaseRespVo.success(wxFootInfo);
        return success;
    }

    @RequestMapping("wx/groupon/my")
    public BaseRespVo wxGrouponMy(@RequestParam int showType, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
    //    String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
    //    Integer userId = UserTokenManager.getUserId(tokenKey);
        //先写userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxGrouponMyInfo wxGrouponMyInfo = wxGrouponService.queryWxMyGroupon(showType, userId);
        BaseRespVo<WxGrouponMyInfo> wxGrouponMyInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = wxGrouponMyInfoBaseRespVo.success(wxGrouponMyInfo);
        return success;
    }

    @RequestMapping("wx/groupon/detail")
    public BaseRespVo wxGrouponDetail(@RequestParam int grouponId, HttpServletRequest request){
        //前端写了一个tokem放在请求头中
        //这里还需要得到用户的id  传到mapper得到信息
    //    String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
    //    Integer userId = UserTokenManager.getUserId(tokenKey);
        //先写userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxGrouponDetail wxGrouponDetail = wxGrouponService.queryWxGrouponDetail(grouponId, userId);
        BaseRespVo<WxGrouponDetail> wxGrouponDetailBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = wxGrouponDetailBaseRespVo.success(wxGrouponDetail);
        return success;
    }


}
