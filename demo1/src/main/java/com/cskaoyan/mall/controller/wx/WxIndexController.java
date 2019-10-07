package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.admin.*;
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
    IssueService issueService;
    @Autowired
    ProductService productService;

    @Autowired
    GrouponService grouponService;

    @Autowired
    AdService adService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping("wx/home/index")
    public BaseRespVo indexInfo() {
        Map<String, Object> data = new HashMap<String, Object>();
        //根据添加时间显示最新的五个广告
        List<Ad> banner = adService.queryAdPageList(new AdReceive(1, 5, null, "add_time", "desc", null));
        List<Brand> brandList = productService.findAllBrand();
        List<Category> channel = categoryService.findAllCateGoriesByLevel("L1");
        List<Coupon> couponList = adService.findAllCoupon();
        List<Map<String, Object>> floorGoodsList = new ArrayList<Map<String, Object>>();
        //显示前三个类别商品信息
        for (int i = 0; i < 3 && i < channel.size(); i++) {
            if (channel.get(i) != null) {
                Map<String, Object> object = new HashMap<>();
                object.put("goodsList", productService.findGoodsByCategoryIdForIndex(channel.get(i).getId()));
                object.put("id", channel.get(i).getId());
                object.put("name", channel.get(i).getName());
                floorGoodsList.add(object);
            }
        }
        List<Map<String, Object>> grouponList = new ArrayList<>();
        List<GrouponRules> grouponRuleList = grouponService.findGrouponRuleList(0, 5);
        for (GrouponRules grouponRules : grouponRuleList) {
            if (grouponRules.getGoodsId() != null) {
                Goods goods = productService.findGoodsById(grouponRules.getGoodsId());
                Map<String, Object> member = new HashMap<>();
                member.put("goods", goods);
                member.put("groupon_member", grouponRules.getDiscountMember());
                member.put("groupon_price", goods.getRetailPrice().subtract(grouponRules.getDiscount()));
                grouponList.add(member);
            }
        }
        List<Goods> hotGoodsList = productService.findGoodsIsHotLastAdd(10);
        List<Goods> newGoodsList = productService.findGoodsLastAdd(10);
        List<TopicArray> topicList = adService.findTopicLastAdd(3);
        data.put("banner", banner);
        data.put("brandList", brandList);
        data.put("channel", channel);
        data.put("couponList", couponList);
        data.put("floorGoodsList", floorGoodsList);
        data.put("grouponList", grouponList);
        data.put("hotGoodsList", hotGoodsList);
        data.put("newGoodsList", newGoodsList);
        data.put("topicList", topicList);
        return BaseRespVo.success(data);
    }

    @RequestMapping("wx/catalog/current")
    public BaseRespVo currentCatalog(String id){
        int currentCategoryId;
        try {
            currentCategoryId = Integer.parseInt(id);
        } catch (Exception e) {
            return BaseRespVo.error(null, 403, "参数错误！");
        }
        Map<String, Object> data = new HashMap<>();
        Category currentCategory = categoryService.findCategoryById(currentCategoryId);
        List<Category> currentSubCategory = categoryService.findCategoryByPid(currentCategoryId);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return BaseRespVo.success(data);
    }

    @RequestMapping("wx/goods/detail")
    public BaseRespVo goodsDetail(String id) {
        int goodsId;
        try {
            goodsId = Integer.parseInt(id);
        } catch (Exception e) {
            return BaseRespVo.error(null, 403, "参数错误！");
        }
        Map<String, Object> dataForVo = new HashMap<>();//封装给前端的数据
        Goods goods = productService.findGoodsById(goodsId);//info
        List<GoodsAttribute> attribute = productService.findGoodsAttributesByGoodsId(goodsId);//attribute
        Brand brand = productService.findBrandById(goods.getBrandId());//brand
        List<Comment> data = productService.findCommentByGoodsId(goodsId);
        Map<String, Object> comment = new HashMap<String, Object>();//comment
        comment.put("data", data);
        comment.put("count", 0);
        List<Issue> issue = issueService.selectAllIssues();//issue
        List<GrouponRules> groupon = grouponService.findGrouponRuleListByGoodsId(goodsId);//groupon
        List<GoodsProduct> productList = productService.findGoodsProductsByGoodsId(goodsId);//productList
        List<Map<String, Object>> specificationList=new ArrayList<>();//specificationList
        List<GoodsSpecification> specifications = productService.findGoodsSpecificationsByGoodsId(goodsId);
        for (GoodsSpecification goodsSpecification : specifications) {
            Map<String, Object> member=new HashMap<String, Object>();
            member.put("valueList",goodsSpecification);
            member.put("name",goodsSpecification.getSpecification());
            specificationList.add(member);
        }
        dataForVo.put("info", goods);
        dataForVo.put("attribute", attribute);
        dataForVo.put("brand", brand);
        dataForVo.put("groupon", groupon);
        dataForVo.put("issue", issue);
        dataForVo.put("productList", productList);
        dataForVo.put("specificationList", specificationList);
        dataForVo.put("comment", comment);
        dataForVo.put("shareImage", "");
        dataForVo.put("userHasCollect", 0);
        return BaseRespVo.success(dataForVo);
    }

    @RequestMapping("wx/goods/related")
    public BaseRespVo goodsRelated() {
        Goods goodsList = productService.findGoodsById(3);
        Map<String, Goods> data=new HashMap<>();
        data.put("goodsList",goodsList);
        return BaseRespVo.success(data);
    }

    @RequestMapping("wx/catalog/index")
    public BaseRespVo catelogIndex() {
        Map data = new HashMap();
        List<Category> categoryList = categoryService.findAllCateGoriesByLevel("L1");
        BaseRespVo baseRespVo = null;
        if (categoryList.size() > 0 && categoryList.get(0) != null) {
            baseRespVo = currentCatalog(categoryList.get(0).getId().toString());
        }

        if (baseRespVo != null) {
            data = (Map) baseRespVo.getData();
        }
        data.put("categoryList", categoryList);
        return BaseRespVo.success(data);
    }
}
