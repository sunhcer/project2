package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Stat;
import com.cskaoyan.mall.service.StatService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计图的controller
 * author: ywx
 */
@RestController
@RequestMapping("/admin/stat/")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseRespVo statUser() {

        Stat stat = statService.statUser();
        return BaseRespVo.success(stat);
    }

    @RequestMapping("order")
    public BaseRespVo statOrder() {

        Stat stat = statService.statOrder();
        return BaseRespVo.success(stat);
    }

    @RequestMapping("goods")
    public BaseRespVo statGood() {

        Stat stat = statService.statGood();
        return BaseRespVo.success(stat);
    }
}
