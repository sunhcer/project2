package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.service.wx.WxOrderService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxOrderDetailData;
import com.cskaoyan.mall.vo.WxOrderPage;
import com.cskaoyan.mall.vo.WxOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        return BaseRespVo.success(wxOrderVo);
    }

    ///wx/order/detail?orderId=311
    @RequestMapping("/wx/order/detail")
    public BaseRespVo detail(int orderId){
        WxOrderDetailData wxOrderDetailData = wxOrderService.getOrderByOrderId(orderId);
        return BaseRespVo.success(wxOrderDetailData);
    }

    ///wx/user/index
    @RequestMapping("/wx/user/index")
    private BaseRespVo wxUserIndex(){
        Map order = wxOrderService.getStateNum();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("order", order);
        return BaseRespVo.success(map);
    }

    ///wx/order/cancel
    @RequestMapping("/wx/order/cancel")
    public BaseRespVo cancelOrder(@RequestBody Order order){
        Integer orderId = order.getOrderId();
        wxOrderService.cancelOrder(orderId);
        return BaseRespVo.success(null);
    }

    ///wx/order/prepay
    @RequestMapping("/wx/order/prepay")
    public BaseRespVo prepay(@RequestBody Order order){
        wxOrderService.prepayOrder(order.getOrderId());
        return BaseRespVo.success(null);
    }

    ///wx/order/refund
    @RequestMapping("/wx/order/refund")
    public BaseRespVo refund(@RequestBody Order order){
        wxOrderService.refundOrder(order.getOrderId());
        return BaseRespVo.success(null);
    }

    ///wx/order/confirm
    @RequestMapping("/wx/order/confirm")
    public BaseRespVo confirm(@RequestBody Order order){
        wxOrderService.confirmOrder(order.getOrderId());
        return BaseRespVo.success(null);
    }
}
