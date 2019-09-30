package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.BrandPage;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.mall.service.MarkService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/admin/region/list")
    public BaseRespVo region(){
        List<Region> regionList = markService.getAllRegion();
        BaseRespVo success = BaseRespVo.success(regionList);
        return success;
    }

    ///admin/brand/list?page=1&limit=20&sort=add_time&order=desc
    @RequestMapping("/admin/brand/list")
    public BaseRespVo brand(BrandPage page){
        BrandList brandList = brandService.getBrandList(page);
        BaseRespVo respVo = BaseRespVo.success(brandList);
        return respVo;
    }
}
