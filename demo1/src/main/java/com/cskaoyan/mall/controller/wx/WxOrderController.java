package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.service.wx.WxOrderService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxOrderPage;
import com.cskaoyan.mall.vo.WxOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:22
 */
@RestController
public class WxOrderController {

    @Autowired
    WxOrderService wxOrderService;
    ///wx/order/list
    @RequestMapping("/wx/order/list")
    public BaseRespVo order(WxOrderPage page){
        WxOrderVo wxOrderVo = wxOrderService.getOrderByShowType(page);
        return null;
    }
}
