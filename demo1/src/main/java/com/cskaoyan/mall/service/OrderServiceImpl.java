package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 17:09
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public BrandList getOrderByState(OrderPage page) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        if (page.getOrder()!=null && page.getSort() != null){
            PageHelper.orderBy(page.getSort() + " " + page.getOrder());
        }
        List<Order> orderList = new ArrayList<>();
        long total = 0;
        //如果状态数组是null  则代表查询所有
        //有的可能为 “”  但是这样也是全部搜索

        if (page.getOrderStatusArray() != null &&page.getOrderStatusArray().size() == 0){
            page.setOrderStatusArray(null);
        }
        if (page.getOrderSn() != null && "".equals(page.getOrderSn().trim())){
            //当它为空字符串
            page.setOrderSn(null);
        }

        if (page.getOrderStatusArray() == null
                && page.getUserId() == null
                && page.getOrderSn() == null) {
            orderList = orderMapper.selectAllOrder();
            PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
            total = orderPageInfo.getTotal();
        } else if (page.getUserId() != null || page.getOrderSn() != null) {
            //至少有一个不为null 一起查询 查询后还要判断状态是否满足
            List<Order> lists = orderMapper.selectOrderByCondition(page);
            //获取总数  但是还要和最终结果判断
            long num = new PageInfo<>(lists).getTotal();
            if (lists != null){
                //查询结果为不null
                if (page.getOrderStatusArray() != null){
                    //当状态数组也不为空 并且 查询结果不为空
                    List<Integer> statusList = page.getOrderStatusArray();
                    for (Order order : lists) {
                        if (statusList.contains(order.getOrderStatus())){
                            //如果状态限制中包含该状态 加入结果数组中
                            orderList.add(order);
                            total++;
                        }
                    }
                }else{
                    //查询结果不为空  状态数组为空
                    orderList.addAll(lists);
                    total = num;
                }
            }else{
                //结果数组为空
                total = 0;
            }

        } else {
            //如果是某一个状态 则返回具体的
            //101 未付款    102 用户取消    103  系统取消
            //201  已付款   202  申请退款   203  已退款
            //301  已发货   401  用户收货   402  系统收货
            List<Integer> orderStatusArray = page.getOrderStatusArray();
            String string = Arrays.toString(orderStatusArray.toArray());
            string = string.replace("[", "(");
            string = string.replace("]", ")");
            orderMapper.selectOrderByState(string);
        }
        BrandList<Order> orderBrandList = new BrandList<>();
        orderBrandList.setItems(orderList);
        orderBrandList.setTotal(total);
        return orderBrandList;
    }

    @Override
    public OrderDetail getOrderDetail(int id) {
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(id);
        Order order = orderMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setOrderGoods(orderGoodsList);
        orderDetail.setUser(user);
        return orderDetail;
    }

    @Override
    public void updateOrder(Order order) {
        int inserNum = orderMapper.updateByPrimaryKeySelective(order);
    }
}
