package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.ProductService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类简介：后台商品管理模块
 * 当前方法：
 * 创建时间: 2019-09-30 14:06
 *
 * @author EGGE
 */
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 处理请求：显示商品类别和商家
     * 方法用途：返回商品类别和商家列表
     * 操作简介：将返回值封装到CatAndBrandVo类中
     *
     * @author EGGE
     * @date 2019-09-30 16:17:23
     * @return
     **/
    @RequestMapping("/admin/goods/catAndBrand")
    public BaseRespVo catAndBrand(){
        List<CatAndBrandVo> brands=productService.findAllBrandToVo();
        List<CatAndBrandVo> categories=productService.findAllCategoriesToVo();
/*        无法自动封装为date.brands和date.categories
        List<List> dateList=new ArrayList<List>();
        dateList.add(brands);
        dateList.add(categories);
        BaseRespVo success = BaseRespVo.success(dateList);
        return success;*/
        Map date=new HashMap();
        date.put("brandList",brands);
        date.put("categoryList",categories);
        return BaseRespVo.success(date);
    }
    @RequestMapping("admin/goods/list")
    public BaseRespVo productList(){
        return null;
    }
}
