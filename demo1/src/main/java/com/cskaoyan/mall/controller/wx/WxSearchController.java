package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-07 16:05
 *
 * @author EGGE
 */
@RestController
public class WxSearchController {

    @RequestMapping("wx/search/index")
    public BaseRespVo searchIndex(){
    return null;
    }

}
