package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Region;
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

    @RequestMapping("/admin/region/list")
    public BaseRespVo region(){
        List<Region> regionList = markService.getAllRegion();
        BaseRespVo success = BaseRespVo.success(regionList);
        return success;
    }
}
