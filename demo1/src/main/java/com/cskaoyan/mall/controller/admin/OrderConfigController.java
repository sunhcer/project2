package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.OrderConfigBean;
import com.cskaoyan.mall.service.admin.GetOrderConfigService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderConfigController {
    @Autowired
    GetOrderConfigService getOrderConfigService;
    @RequestMapping(value = "admin/config/order",method = RequestMethod.GET)
    public BaseRespVo getConfig(){
        OrderConfigBean orderConfigBean = getOrderConfigService.showOrderConfig();
        BaseRespVo respVo = BaseRespVo.success(orderConfigBean);
        return respVo;
    }
    @RequestMapping(value = "admin/config/order",method = RequestMethod.POST)
    public BaseRespVo changeConfig(@RequestBody OrderConfigBean orderConfigBean){
        getOrderConfigService.changeOrderConfig(orderConfigBean);
        BaseRespVo respVo = BaseRespVo.success(null);
        return respVo;
    }
}
