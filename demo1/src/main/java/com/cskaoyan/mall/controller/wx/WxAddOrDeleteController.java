package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Collect2;
import com.cskaoyan.mall.service.wx.WxAddOrDeleteService;
import com.cskaoyan.mall.vo.AddOrDeleteVo;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxAddOrDeleteController {
    @Autowired
    WxAddOrDeleteService wxAddOrDeleteService;

    @RequestMapping("wx/collect/addordelete")
    public BaseRespVo addDelete(@RequestBody Collect2 collect2) {
        AddOrDeleteVo addOrDeleteVo = wxAddOrDeleteService.addOrDelete(collect2.getType(),collect2.getValueId());
        BaseRespVo success = BaseRespVo.success(addOrDeleteVo);
        return success;
    }
}
