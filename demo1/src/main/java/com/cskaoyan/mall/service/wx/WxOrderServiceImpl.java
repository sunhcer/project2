package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.admin.ProductServiceImpl;
import com.cskaoyan.mall.util.TransferBig2Double;
import com.cskaoyan.mall.util.TransferCodeToText;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.lang.System;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    SystemMapper systemMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    RegionMapper regionMapper;

    @Override
    public WxOrderVo getOrderByShowType(int userId, WxOrderPage page) {
        PageHelper.startPage(page.getPage(), page.getSize());
        //首先在这里要拿到人的信息
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
    public Map<String, Integer> getStateNum(int userId) {
        //showType = 0全部   全部
        //showType = 1待付款   101
        //showType = 2待发货   201
        //showType = 3待收货   301
        //showType = 4待评价   401
        String unpaidStatusId = "101";
        String unshipStatusId = "201";
        String unrecvStatusId = "301";
        String uncommentStatusId = "401";
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
        if (order.getOrderStatus() == 101) {
            handleOption.setPay(true);
            handleOption.setCancel(true);
        } else if (order.getOrderStatus() == 201) {
            handleOption.setRefund(true);
        } else if (order.getOrderStatus() == 301) {
            handleOption.setConfirm(true);
        } else if (order.getOrderStatus() == 401 || order.getOrderStatus() == 402) {
            handleOption.setRebuy(true);
            if (order.getComments() > 0) {
                handleOption.setComment(true);
            }
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

    @Override
    public void commentOrder(Comment comment) {
        /*必须将*/
        comment.setId(null);
        comment.setType((byte) 3);
        //订单id      不知道哪个字段是订单id
        comment.setValueId(comment.getOrderGoodsId());
        comment.setType((byte) 3);
        if (comment.getPicUrls() != null) {
            String[] picUrls = comment.getPicUrls();
            for (int i = 0; i < picUrls.length; i++) {
                picUrls[i] = picUrls[i].replace(myprefix, "");
            }
            comment.setPicUrls(picUrls);
        }
        //将订单商品表中的comment改成10
        orderGoodsMapper.updateComment(comment.getOrderGoodsId(), 10);
        //并且订单表中的comments字段-1
        OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(comment.getOrderGoodsId());
        orderMapper.updateComments(orderGoods.getOrderId());

        commentMapper.insertSelective(comment);
    }

    @Override
    public WxOrderCheckoutBean checkOrder(int userId, Integer cartId, int addressId, int couponId, int grouponRulesId) {
        List<Cart> cartList = null;
        if (cartId == null) {
            cartList = cartMapper.selectUserAllCheckedCart(userId);
        }else{
            cartList = new ArrayList<>();
            cartList.add(cartMapper.selectByPrimaryKey(cartId));
        }

        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRulesId);
        Address address = addressMapper.selectByPrimaryKey(addressId);
        WxOrderCheckoutBean wxOrderCheckoutBean = new WxOrderCheckoutBean();

        //先算总价
        double totalMoney = 0;

        //设置其中的参数
        //商品列表
        List<CheckOrderGood> goodsList = new ArrayList<>();
        for (Cart cart : cartList) {
            CheckOrderGood goods = goodsMapper.selectByGoodsSn(cart.getGoodsSn());
            goods.setPicUrl(myprefix + goods.getPicUrl());
            goods.setProductId(cart.getProductId().toString());
            goods.setNumber(cart.getNumber());
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(cart.getProductId());
            goods.setSpecifications(goodsProduct.getSpecifications());
            totalMoney = totalMoney + goods.getPrice() * cart.getNumber();
            goodsList.add(goods);
        }
        wxOrderCheckoutBean.setCheckedGoodsList(goodsList);

        //优惠券不知道怎设置
        wxOrderCheckoutBean.setCouponId(couponId);
        if (coupon != null) {
            wxOrderCheckoutBean.setCouponId(couponId);
            wxOrderCheckoutBean.setCouponPrice(coupon.getDiscount().doubleValue());
        }else{
            wxOrderCheckoutBean.setCouponId(0);
            wxOrderCheckoutBean.setCouponPrice(0);
        }

        //团购设置
        wxOrderCheckoutBean.setGrouponRulesId(grouponRulesId);
        if (grouponRules != null) {
            wxOrderCheckoutBean.setGrouponPrice(grouponRules.getDiscount());
        }else{
            wxOrderCheckoutBean.setGrouponPrice(BigDecimal.valueOf(0));
        }

        //地址设置
        wxOrderCheckoutBean.setAddressId(addressId);
        if (address != null) {
            wxOrderCheckoutBean.setCheckedAddress(address);
        }

        //运费
        double freightMin = Double.parseDouble(systemMapper.selectKey("cskaoyan_mall_express_freight_min"));
        double freight_value = Double.parseDouble(systemMapper.selectKey("cskaoyan_mall_express_freight_value"));


        for (Cart cart : cartList) {
            int number = cart.getNumber();
            double goodPrice = cart.getPrice().doubleValue();
            totalMoney = totalMoney + number * goodPrice;
        }
        wxOrderCheckoutBean.setOrderTotalPrice(totalMoney);
        wxOrderCheckoutBean.setActualPrice(totalMoney);
        wxOrderCheckoutBean.setGoodsTotalPrice(TransferBig2Double.double2Big(totalMoney));

        if (totalMoney < freightMin) {
            //订单价格小于最低运费限制
            wxOrderCheckoutBean.setFreightPrice(freight_value);
        }else{
            wxOrderCheckoutBean.setFreightPrice(0);
        }

        return wxOrderCheckoutBean;
    }

    @Override
    public OrderGoods selectOrderGoods(int orderId, int goodsId) {
        OrderGoods orderGoods = orderGoodsMapper.selectOrderGoodsByOrderIdAndGoodsId(orderId, goodsId);
        orderGoods.setPicUrl(myprefix + orderGoods.getPicUrl());
        return orderGoods;
    }

    @Override
    public int submitOrder(int userId, String addressId, Object message) {
        int addressIdnum = Integer.parseInt(addressId);
        WxOrderCheckoutBean wxOrderCheckoutBean = checkOrder(userId, 0, addressIdnum, 0, 0);
        //删除选中的购物车
        cartMapper.deleteUserAllCheckedCart(userId);
        Address address = wxOrderCheckoutBean.getCheckedAddress();


        Order order = new Order();
        order.setUserId(userId);
        order.setActualPrice(TransferBig2Double.double2Big(wxOrderCheckoutBean.getActualPrice()));
        order.setOrderSn(getRandom());
        order.setOrderStatus(101);
        order.setConsignee(address.getName());
        order.setMobile(address.getMobile());
        order.setGoodsPrice(wxOrderCheckoutBean.getGoodsTotalPrice());
        order.setGrouponPrice(wxOrderCheckoutBean.getGrouponPrice());

        //地址
        String province = regionMapper.selectAddressByCode(address.getProvinceId());
        String city = regionMapper.selectAddressByCode(address.getCityId());
        String area = regionMapper.selectAddressByCode(address.getAreaId());
        order.setAddress(province + " " + city + " " + area);


        if (message != null){
            order.setMessage(message.toString());
        }

        order.setGoodsPrice(wxOrderCheckoutBean.getGoodsTotalPrice());
        order.setFreightPrice(TransferBig2Double.double2Big(wxOrderCheckoutBean.getFreightPrice()));
        order.setCouponPrice(TransferBig2Double.double2Big(wxOrderCheckoutBean.getCouponPrice()));
        double orderPrice = wxOrderCheckoutBean.getGoodsTotalPrice().doubleValue() + wxOrderCheckoutBean.getFreightPrice() - wxOrderCheckoutBean.getCouponPrice();
        order.setOrderPrice(BigDecimal.valueOf(orderPrice));
        //用户积分减免  ????
        order.setIntegralPrice(BigDecimal.valueOf(0));
        order.setGoodsPrice(wxOrderCheckoutBean.getGrouponPrice());
        order.setActualPrice(TransferBig2Double.double2Big(wxOrderCheckoutBean.getActualPrice()));

        //orderPrice订单费用， = goods_price + freight_price - coupon_price
        List<CheckOrderGood> list = wxOrderCheckoutBean.getCheckedGoodsList();
        order.setComments((short) list.size());
        order.setAddTime(new Date());

        orderMapper.insertSelective(order);
        order = orderMapper.selectByPrimaryKey(order.getId());
        int orderId = order.getId();
        for (CheckOrderGood checkOrderGood : list) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setAddTime(new Date());
            orderGoods.setChecked(true);
            orderGoods.setOrderId(orderId);
            if (checkOrderGood.getId() != null){
                orderGoods.setGoodsId(checkOrderGood.getId());
            }
            orderGoods.setSpecifications(checkOrderGood.getSpecifications());
            orderGoods.setPicUrl(checkOrderGood.getPicUrl());
            orderGoods.setGoodsSn(checkOrderGood.getGoodsSn());
            orderGoods.setPrice(TransferBig2Double.double2Big(checkOrderGood.getPrice()));
            orderGoods.setNumber((short) checkOrderGood.getNumber());
            orderGoods.setPicUrl(orderGoods.getPicUrl().replace(myprefix, ""));
            orderGoodsMapper.insertSelective(orderGoods);
        }
        return orderId;
    }

    private String getRandom(){
        Date date=new Date();//此时date为当前的时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(date);
        int random = (int) (Math.random() * 1000000);
        return format + random;
    }
}
