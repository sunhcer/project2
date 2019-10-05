package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.AdService;
import com.cskaoyan.mall.service.GrouponService;
import com.cskaoyan.mall.service.ProductService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-04 20:08
 *
 * @author EGGE
 */
@RestController
public class WxIndexController {
    @Autowired
    ProductService productService;

    @Autowired
    GrouponService grouponService;

    @Autowired
    AdService adService;


    @RequestMapping("wx/home/index")
    public BaseRespVo indexInfo(){
        Map<String,Object> data =new HashMap<String, Object>();
        //根据添加时间显示最新的五个广告
        List<Ad> banner=adService.queryAdPageList(new AdReceive(1,5,null,"add_time","desc",null));
        List<Brand> brandList=productService.findAllBrand();
        List<Category> channel=productService.findAllCategories();
        List<Coupon> couponList=adService.findAllCoupon();
        List<Map<String, Object>> floorGoodsList=new ArrayList<Map<String, Object>>();
        //显示前三个类别商品信息
        for (int i = 0; i < 3&&i<channel.size(); i++) {
            if(channel.get(i)!=null) {
                Map<String, Object> object = new HashMap<>();
                object.put("goodsList",productService.findGoodsByCategoryIdForIndex(channel.get(i).getId()));
                object.put("id",channel.get(i).getId());
                object.put("name",channel.get(i).getName());
                floorGoodsList.add(object);
            }
        }
        List<Map<String, Object>> grouponList=new ArrayList<>();
        List<GrouponRules> grouponRuleList = grouponService.findGrouponRuleList(0, 5);
        for (GrouponRules grouponRules : grouponRuleList) {
            if(grouponRules.getGoodsId()!=null){
                Goods goods=productService.findGoodsById(grouponRules.getGoodsId());
                Map<String, Object> member=new HashMap<>();
                member.put("goods",goods);
                member.put("groupon_member",grouponRules.getDiscountMember());
                member.put("groupon_price",grouponRules.getDiscount());
                grouponList.add(member);
            }
        }
        List<Goods> hotGoodsList=productService.findGoodsIsHotLastAdd(10);
        List<Goods> newGoodsList=productService.findGoodsLastAdd(10);
        List<TopicArray> topicList =adService.findTopicLastAdd(3);
        data.put("banner",banner);
        data.put("brandList",brandList);
        data.put("channel",channel);
        data.put("couponList",couponList);
        data.put("floorGoodsList",floorGoodsList);
        data.put("grouponList",grouponList);
        data.put("hotGoodsList",hotGoodsList);
        data.put("newGoodsList",newGoodsList);
        data.put("topicList",topicList);
        return BaseRespVo.success(data);
    }
}
