package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.WxConfigBean;
import com.cskaoyan.mall.service.GetWxConfigService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxConfigController {

    @Autowired
    GetWxConfigService getWxConfigService;

    @RequestMapping(value = "admin/config/wx",method = RequestMethod.GET)
    public BaseRespVo getConfig(){
        WxConfigBean wxConfigBean = getWxConfigService.showWxConfig();
        BaseRespVo respVo = BaseRespVo.success(wxConfigBean);
        return respVo;
    }
    @RequestMapping(value = "admin/config/wx",method = RequestMethod.POST)
    public BaseRespVo changeConfig(@RequestBody WxConfigBean wxConfigBean){
         getWxConfigService.changWxConfig(wxConfigBean);
        BaseRespVo respVo = BaseRespVo.success(null);
        return respVo;
    }
}
