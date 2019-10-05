package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.WxOrderDetailData;
import com.cskaoyan.mall.vo.WxOrderPage;
import com.cskaoyan.mall.vo.WxOrderVo;

import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:27
 */
public interface WxOrderService {
    WxOrderVo getOrderByShowType(WxOrderPage page);

    WxOrderDetailData getOrderByOrderId(int orderId);

    Map getStateNum();

    void cancelOrder(int orderId);

    void prepayOrder(Integer orderId);

    void refundOrder(Integer orderId);

    void confirmOrder(Integer orderId);
}
