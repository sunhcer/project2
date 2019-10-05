package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.ExpressConfigBean;
import com.cskaoyan.mall.service.admin.GetExpressConfigService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运费配置
 */
@RestController
public class ExpressConfigController {
    @Autowired
    GetExpressConfigService getExpressConfigService;
    @RequestMapping(value = "admin/config/express",method = RequestMethod.GET )
    public BaseRespVo getConfig(){
        ExpressConfigBean expressConfigBean = getExpressConfigService.showExpressConfige();
        BaseRespVo respVo = BaseRespVo.success(expressConfigBean);
        return respVo;
    }
    @RequestMapping(value = "admin/config/express",method = RequestMethod.POST )
    public BaseRespVo changeConfig(@RequestBody ExpressConfigBean expressConfigBean){
         getExpressConfigService.changeExpressConfig(expressConfigBean);
        BaseRespVo respVo = BaseRespVo.success(null);
        return respVo;
    }
}
