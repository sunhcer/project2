package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.util.TransferCodeToText;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:28
 */
@RestController
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Value("${myfile.img-prefix}")
    String myprefix;

    @Override
    public WxOrderVo getOrderByShowType(WxOrderPage page) {
        PageHelper.startPage(page.getPage(), page.getSize());
        //首先在这里要拿到人的信息
        int userId = 1;
        List<Order> orderList = null;
        if (page.getShowType() == 0) {
            //代表全部订单
            orderList = orderMapper.selectOneUserAllOrders(userId);
        } else {
            //代表分着的订单
            //showType = 0全部   全部
            //showType = 1待付款   101
            //showType = 2待发货   201
            //showType = 3待收货   301
            //showType = 4待评价   401
            orderList = orderMapper.selectOneUserStateOrders(userId, page.getShowType() + "01");
        }
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        long total = pageInfo.getTotal();       //总页数
        int pages = pageInfo.getPages();
        WxOrderVo wxOrderVo = new WxOrderVo();

        //前置准备 设置两个参数  总条目数 和 总页数
        wxOrderVo.setCount(total);
        wxOrderVo.setTotalPages(pages);
        ArrayList<WxOrderDetailVo> wxOrderDetailVos = new ArrayList<>();

        //将每一个订单的状态都取出来
        if (orderList != null) {
            for (Order order : orderList) {
                WxOrderDetailVo wxOrderDetailVo = new WxOrderDetailVo();

                //订单状态
                int orderStatus = order.getOrderStatus();
                String statusText = TransferCodeToText.transferStatusCodeToString(orderStatus + "");
                wxOrderDetailVo.setOrderStatusText(statusText);
                //订单编号
                wxOrderDetailVo.setOrderSn(order.getOrderSn());

                //实付款
                wxOrderDetailVo.setActualPrice(order.getActualPrice());

                //根据订单id 选中该订单的商品
                Integer orderId = order.getId();
                List<OrderGoods> goodsList = orderGoodsMapper.selectOrderGoodsByOrderId(orderId);
                for (OrderGoods orderGoods : goodsList) {
                    orderGoods.setPicUrl(myprefix + orderGoods.getPicUrl());
                }
                wxOrderDetailVo.setGoodsList(goodsList);
                wxOrderDetailVo.setId(orderId);

                //查找handleOption状态
                HandleOption handleOption = handleOption(order);
                wxOrderDetailVo.setHandleOption(handleOption);

                wxOrderDetailVos.add(wxOrderDetailVo);
            }
        }
        wxOrderVo.setData(wxOrderDetailVos);
        return wxOrderVo;
    }

    @Override
    public WxOrderDetailData getOrderByOrderId(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectOrderGoodsByOrderId(orderId);

        for (OrderGoods orderGood : orderGoods) {
            orderGood.setPicUrl(myprefix + orderGood.getPicUrl());
        }

        WxOrderDetailData wxOrderDetailData = new WxOrderDetailData();
        HandleOption handleOption = handleOption(order);

        order.setHandleOption(handleOption);

        wxOrderDetailData.setOrderInfo(order);

        wxOrderDetailData.setOrderGoods(orderGoods);
        return wxOrderDetailData;
    }

    @Override
    public Map<String, Integer> getStateNum() {
        //showType = 0全部   全部
        //showType = 1待付款   101
        //showType = 2待发货   201
        //showType = 3待收货   301
        //showType = 4待评价   401
        String unpaidStatusId = "101";
        String unshipStatusId = "201";
        String unrecvStatusId = "301";
        String uncommentStatusId = "401";
        int userId = 1;
        int unpaidNum = orderMapper.selectCountByOrderStatus(userId, unpaidStatusId);
        int unshipNum = orderMapper.selectCountByOrderStatus(userId, unshipStatusId);
        int unrecvNum = orderMapper.selectCountByOrderStatus(userId, unrecvStatusId);
        int uncommentNum = orderMapper.selectCountByOrderStatus(userId, uncommentStatusId);
        HashMap<String, Integer> order = new HashMap<>();
        order.put("unrecv", unrecvNum);
        order.put("unship", unshipNum);
        order.put("unpaid", unpaidNum);
        order.put("uncomment", uncommentNum);
        return order;
    }

    private HandleOption handleOption(Order order) {
        //根据订单的状态码等其他信息 判断订单状态信息
        HandleOption handleOption = new HandleOption();
        if (order.getOrderStatus() == 101){
            handleOption.setPay(true);
            handleOption.setCancel(true);
        }else if (order.getOrderStatus() == 201){
            handleOption.setRefund(true);
        }else if (order.getOrderStatus() == 301){
            handleOption.setConfirm(true);
        }else if (order.getOrderStatus() == 401 || order.getOrderStatus() == 402){
            handleOption.setComment(true);
        }
        /*少一个delete comment rebuy*/
        if (order.getOrderStatus() == 403){
            handleOption.setRebuy(true);
        }
        return handleOption;
    }

    @Override
    public void cancelOrder(int orderId) {
        orderMapper.setOrderStatus(orderId, 102);
    }

    @Override
    public void prepayOrder(Integer orderId) {
        orderMapper.setOrderStatus(orderId, 201);
    }

    @Override
    public void refundOrder(Integer orderId) {
        orderMapper.setOrderStatus(orderId, 202);
    }

    @Override
    public void confirmOrder(Integer orderId) {
        orderMapper.setOrderStatus(orderId, 401);
    }
}
