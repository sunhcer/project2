package com.cskaoyan.mall.controller.admin;


import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.admin.*;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 12:43
 */
@RestController
public class MarketController {

    @Autowired
    MarkService markService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    IssueService issueService;
    @Autowired
    StorageService storageService;
    @Autowired
    KeywordService keywordService;


    @RequestMapping("/admin/region/list")
    public BaseRespVo region() {
        List<Region> regionList = markService.getAllRegion();
        BaseRespVo success = BaseRespVo.success(regionList);
        return success;
    }

    ///admin/brand/list?page=1&limit=20&sort=add_time&order=desc
    @RequestMapping("/admin/brand/list")
    public BaseRespVo brand(BrandPage page) {
        BrandList brandList = brandService.getBrandList(page);
        BaseRespVo respVo = BaseRespVo.success(brandList);
        return respVo;
    }

    ///admin/brand/update
    @RequestMapping("/admin/brand/update")
    public BaseRespVo updateBrand(@RequestBody Brand brand) {
        brandService.updateBrand(brand);
        BaseRespVo success = BaseRespVo.success(brand);
        return success;
    }

    ///admin/brand/delete
    @RequestMapping("/admin/brand/delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand) {
        brandService.deleteBrandById(brand.getId());
        BaseRespVo success = BaseRespVo.success(null);
        return success;
    }

    ///admin/brand/create
    @RequestMapping("/admin/brand/create")
    public BaseRespVo createBrand(@RequestBody Brand brand){
        brandService.insertBrand(brand);
        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/category/list")
    public BaseRespVo getCategoryList() {
        List<Category> allCategory = categoryService.getAllCategory();
        BaseRespVo success = BaseRespVo.success(allCategory);
        return success;
    }

    /**
     * 获取所有的一级标签
     *
     * @return
     */
    @RequestMapping("/admin/category/l1")
    public BaseRespVo getAllLevel1() {
        List<CatAndBrandVo> lists = categoryService.getAllLevel1();
        BaseRespVo success = BaseRespVo.success(lists);
        return success;
    }

    @RequestMapping("/admin/category/create")
    public BaseRespVo createCategory(@RequestBody Category category) {
        int insertNum = categoryService.insertCategory(category);
        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/category/update")
    public BaseRespVo updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/category/delete")
    public BaseRespVo deleteCategory(@RequestBody Category category) {
        //删除category 并且删除其子类
        categoryService.deleteCategory(category);
        return BaseRespVo.success(null);
    }

    ///admin/order/list
    @RequestMapping("/admin/order/list")
    public BaseRespVo getOrderList(OrderPage orderPage) {
        BrandList orderByState = orderService.getOrderByState(orderPage);
        BaseRespVo success = BaseRespVo.success(orderByState);
        return success;
    }

    ///admin/order/detail
    @RequestMapping("/admin/order/detail")
    public BaseRespVo getOrderDetail(int id){
        OrderDetail orderDetail = orderService.getOrderDetail(id);
        BaseRespVo success = BaseRespVo.success(orderDetail);
        return success;
    }

    ///admin/order/ship
    @RequestMapping("/admin/order/ship")
    public BaseRespVo addOrderShip(@RequestBody Order order){
        order.setId(order.getOrderId());
        order.setOrderStatus(301);
        order.setUpdateTime(new Date());
        orderService.updateOrder(order);
        return BaseRespVo.success(null);
    }

    ///admin/order/refund
    @RequestMapping("/admin/order/refund")
    public BaseRespVo orderRefund(@RequestBody Map map){
        Order order = new Order();
        order.setOrderStatus(203);
        order.setId(Integer.parseInt(map.get("orderId").toString()));
        //退款的钱存在哪个表里
        order.setUpdateTime(new Date());
        orderService.updateOrder(order);
        return BaseRespVo.success(null);
    }

    ///admin/issue/list?page=1&limit=20&sort=add_time&order=desc
    @RequestMapping("/admin/issue/list")
    public BaseRespVo getIssue(OrderPage orderPage){
        BrandList brandList = issueService.selectIssueByCondition(orderPage);
        BaseRespVo success = BaseRespVo.success(brandList);
        return success;
    }

    ///admin/issue/update
    @RequestMapping("/admin/issue/update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        issueService.updateIssueById(issue);
        return BaseRespVo.success(null);
    }

    ///admin/issue/create
    @RequestMapping("/admin/issue/create")
    public BaseRespVo insertIssue(@RequestBody Issue issue){
        issueService.insertIssue(issue);
        return BaseRespVo.success(issue);
    }

    @RequestMapping("/admin/storage/create")
    public BaseRespVo storageImg(MultipartFile file) throws IOException {
        Storage storage = storageService.insertStorage(file);
        return BaseRespVo.success(storage);
    }

    ///admin/issue/delete
    @RequestMapping("/admin/issue/delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        issueService.deleteIssueById(issue);
        return BaseRespVo.success(null);
    }

    ///admin/keyword/list
    @RequestMapping("/admin/keyword/list")
    public BaseRespVo selectKeyword(OrderPage orderPage){
        BrandList<Keyword> keywordBrandList = keywordService.selectKeyWordByCondition(orderPage);
        return BaseRespVo.success(keywordBrandList);
    }

    ///admin/keyword/create
    @RequestMapping("/admin/keyword/create")
    public BaseRespVo insertKeyword(@RequestBody Keyword keyword){
        keywordService.insertKeyword(keyword);
        return BaseRespVo.success(null);
    }

    ///admin/keyword/delete
    @RequestMapping("/admin/keyword/delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword){
        keywordService.deleteKeyword(keyword);
        return BaseRespVo.success(null);
    }

    //admin/keyword/update
    @RequestMapping("/admin/keyword/update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword){
        keywordService.updateKeyword(keyword);
        return BaseRespVo.success(null);
    }
}
