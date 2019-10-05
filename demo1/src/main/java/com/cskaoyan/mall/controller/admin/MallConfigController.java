package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.service.admin.GetMallConfigeService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.bean.MarkConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商场配置管理
 */
@RestController
public class MallConfigController {

    @Autowired
    GetMallConfigeService getMallConfigeService;

    @RequestMapping(value = "admin/config/mall",method = RequestMethod.GET)

    public BaseRespVo getConfige(){
        MarkConfigBean markConfigBean = getMallConfigeService.showMallConfige();
        BaseRespVo respVo=BaseRespVo.success(markConfigBean);
        return respVo;
    }

    @RequestMapping(value = "admin/config/mall",method = RequestMethod.POST)
    public BaseRespVo changeConfige(@RequestBody MarkConfigBean markConfigBean) {
       getMallConfigeService.changeMallConfige(markConfigBean);
       BaseRespVo respVo=BaseRespVo.success(null);
       return respVo;
   }
}
