package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.service.wx.WxSearchService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-07 16:05
 *
 * @author EGGE
 */
@RestController
public class WxSearchController {

    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("wx/search/index")
    public BaseRespVo searchIndex(){
    return null;
    }

    @RequestMapping("/wx/search/helper")
    public BaseRespVo searchHelper(String keyword) {
        List<String> keywords = wxSearchService.searchHelper(keyword);
        return BaseRespVo.success(keywords);
    }
}
