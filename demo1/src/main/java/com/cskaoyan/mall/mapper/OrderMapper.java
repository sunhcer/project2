package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderPage;
import com.cskaoyan.mall.bean.StatOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectAllOrder();

    List<Order> selectOrderByState(String statusArray);

    List<Order> selectOrderByCondition(OrderPage page);

    Integer findAmountOfOrders();

    StatOrder[] getStatOrders();

    List<Order> selectOneUserAllOrders(int userId);

    List<Order> selectOneUserStateOrders(@Param("userId") int userId,@Param("orderStatus") String orderStatus);

    int selectCountByOrderStatus(@Param("userId") int userId,@Param("statusId") String statusId);

    void setOrderStatus(@Param("orderId")int orderId,@Param("statusId") int statusId);

    void updateComments(Integer orderId);
}
