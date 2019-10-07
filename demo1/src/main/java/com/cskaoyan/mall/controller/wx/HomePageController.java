package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.wx.WxHomePageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxCommentArray;
import com.cskaoyan.mall.vo.WxCommentList;
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

    @RequestMapping("/groupon/list")
    public BaseRespVo grouponList(@RequestParam int page, @RequestParam int size){
        BaseRespVo baseRespVo=wxHomePageService.grouponList(page,size);
        return baseRespVo;
    }

    @RequestMapping("/brand/list")
    public BaseRespVo brandList(@RequestParam int page, @RequestParam int size){
        BaseRespVo baseRespVo=wxHomePageService.brandWxList(page,size);
        return baseRespVo;
    }

    @RequestMapping("/brand/detail")
    public BaseRespVo brandDetail(@RequestParam int id){
        BaseRespVo baseRespVo=wxHomePageService.wxBrandDetail(id);
        return baseRespVo;
    }

    ///wx/goods/list品牌详情页这个一直返回502,系统错误,不知道老师写来干嘛
    @RequestMapping("/topic/list")
    public BaseRespVo topicList(@RequestParam int page, @RequestParam int size){
        BaseRespVo baseRespVo=wxHomePageService.topicList(page,size);
        return baseRespVo;
    }

    @RequestMapping("/topic/detail")
    public BaseRespVo topicDetail(@RequestParam int id){
        BaseRespVo baseRespVo=wxHomePageService.topicWxDetail(id);
        return baseRespVo;
    }

    //不知道老师这里采用的是什么related策略,我选取了专题id差值20的区间内更新时间最近的四个
    //本来打算按照阅读量选取,但是这个字段是varchar
    @RequestMapping("/topic/related")
    public BaseRespVo topicRelated(@RequestParam int id){
        BaseRespVo baseRespVo=wxHomePageService.topicWxRelated(id);
        return baseRespVo;
    }

    @RequestMapping("/comment/list")
    public BaseRespVo commentList(WxCommentList wxCommentList){
        //评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论。
        int type=wxCommentList.getType();
        BaseRespVo baseRespVo=null;
//        String username=(String) SecurityUtils.getSubject().getSession().getAttribute("username");
        if(type==1){
           baseRespVo=wxHomePageService.wxTopicCommentList(wxCommentList);
           return baseRespVo;
        }else if (type==0){
            baseRespVo=wxHomePageService.wxGoodsCommentList(wxCommentList);
            return baseRespVo;
        }else{
            return null;
        }
    }

    @RequestMapping("/comment/post")
    public BaseRespVo wxCommentPost(@RequestBody WxCommentArray wxCommentArray){
        //专题评论得到userName要判空,判空的逻辑是这样的
        //前台的判空是在用户点击进入评论,如果没有登录,会弹到登录页面,
        //但是用户可能在进入评论后很久没有发送,此时session结束了,username为null,请求依然可以发送到后端
        //这样也让shiro来做??
        String username=(String) SecurityUtils.getSubject().getSession().getAttribute("username");
        BaseRespVo baseRespVo=wxHomePageService.wxCommentPost(wxCommentArray,username);
        return baseRespVo;
    }

    @RequestMapping("/comment/count")
    public BaseRespVo commentCount(WxCommentList wxCommentList){
        BaseRespVo baseRespVo=wxHomePageService.wxcommentCount(wxCommentList);
        return baseRespVo;
    }

    @RequestMapping("/goods/category")
    public BaseRespVo goodsCategory(@RequestParam int id){
        BaseRespVo baseRespVo=wxHomePageService.goodsWxCategory(id);
        return baseRespVo;
    }

    //这里本来打算过滤未上架的商品,但是有一种情况是,商品未上架,但是上了广告,所以没有过滤
    //同时过滤掉兄弟分类
    //显示当前二级分类下的商品
//    @RequestMapping("/goods/list")
//    public BaseRespVo goodsList(int categoryId,int page,int size){
//        BaseRespVo baseRespVo=wxHomePageService.goodsWxList(categoryId,page,size);
//        return baseRespVo;
//    }

}
