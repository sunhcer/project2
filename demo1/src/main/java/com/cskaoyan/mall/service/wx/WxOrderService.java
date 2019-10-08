package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.*;

import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:27
 */
public interface WxOrderService {
    WxOrderVo getOrderByShowType(int userId, WxOrderPage page);

    WxOrderDetailData getOrderByOrderId(int orderId);

    Map getStateNum(int userId);

    void cancelOrder(int orderId);

    void prepayOrder(Integer orderId);

    void refundOrder(Integer orderId);

    void confirmOrder(Integer orderId);

    void commentOrder(Comment comment);

    WxOrderCheckoutBean checkOrder(int userId, WxSubmitOrderIdBean orderIdBean);

    OrderGoods selectOrderGoods(int orderId, int goodsId);

    int submitOrder(int userId, WxSubmitOrderIdBean orderIdBean);
}
