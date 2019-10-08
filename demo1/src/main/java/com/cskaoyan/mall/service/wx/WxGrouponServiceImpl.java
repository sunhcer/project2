package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxGrouponServiceImpl implements WxGrouponService {

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Value("${myfile.img-prefix}")
    String myprefix;

    @Override
    public WxGrouponMyInfo queryWxMyGroupon(int showType, int userId) {
        WxGrouponMyInfo wxGrouponMyInfo = new WxGrouponMyInfo();
        List<Groupon> groupons = grouponMapper.queryGrouponByUserId(userId);
        //找到该用户所有团购
        if(showType==0) {   //自己发起的
            List<Groupon> createGroupons=new ArrayList<Groupon>();
            for (Groupon groupon : groupons) {
                Integer creatorUserId = groupon.getCreatorUserId();
                Integer userId1 = groupon.getUserId();
                if(userId1==creatorUserId){
                    createGroupons.add(groupon);
                }
            }//得到了所有的自己创建的团购

            wxGrouponMyInfo.setCount(createGroupons.size());//count
            List<WxGrouponMy> data = new ArrayList<>();
            for (Groupon createGroupon : createGroupons) {
                WxGrouponMy wxGrouponMy = new WxGrouponMy();
                wxGrouponMy.setGroupon(createGroupon);//groupon    3
                wxGrouponMy.setOrderStatusText("已取消(系统)");//orderStatusText   1
                int creatorUserId=createGroupon.getCreatorUserId();//找到创建者的name
                User creatorUser = userMapper.selectByPrimaryKey(createGroupon.getCreatorUserId());
                wxGrouponMy.setCreator(creatorUser.getUsername());//creator   2
                Integer orderId = createGroupon.getOrderId();
                wxGrouponMy.setOrderId(orderId);//orderId   4
                //通过orderId 找到 orderSn actualPrice
                Order order = orderMapper.selectByPrimaryKey(orderId);
                wxGrouponMy.setOrderSn(order.getOrderSn());//orderSn    5
                wxGrouponMy.setActualPrice(order.getActualPrice().doubleValue());//actualPrice    6
                wxGrouponMy.setJoinerCount(1);   //joinerCount   7
                Integer rulesId = createGroupon.getRulesId();
                GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(rulesId);
                wxGrouponMy.setRules(grouponRules);//rules     9
                WxGrouponGood wxGrouponGood = new WxGrouponGood();
                wxGrouponGood.setGoodsName(grouponRules.getGoodsName());
                wxGrouponGood.setId(grouponRules.getId());
                wxGrouponGood.setNumber(1);
                wxGrouponGood.setPicUrl(grouponRules.getPicUrl());
                List<WxGrouponGood> list = new ArrayList<>();
                list.add(wxGrouponGood);
                wxGrouponMy.setGoodsList(list);//goodsList   8
                wxGrouponMy.setId(createGroupon.getId());//  id    10
                wxGrouponMy.setCreator(true); //isCreator   11
                HandleOption handleOption = new HandleOption();
                handleOption.setCancel(false);
                handleOption.setDelete(true);
                handleOption.setComment(false);
                handleOption.setPay(true);
                handleOption.setConfirm(false);
                handleOption.setRefund(false);
                handleOption.setRebuy(false);
                wxGrouponMy.setHandleOption(handleOption);
                data.add(wxGrouponMy);   //add进入data
            }
            wxGrouponMyInfo.setData(data);
            return wxGrouponMyInfo;

        }else if(showType==1) {   //参加的
            List<Groupon> joinGroupons=new ArrayList<Groupon>();
            for (Groupon groupon : groupons) {
                Integer creatorUserId = groupon.getCreatorUserId();
                Integer userId1 = groupon.getUserId();
                if(userId1!=creatorUserId){
                    joinGroupons.add(groupon);
                }
            }
            List<WxGrouponMy> data = new ArrayList<>();
            wxGrouponMyInfo.setCount(joinGroupons.size());
            for (Groupon joinGroupon : joinGroupons) {
                WxGrouponMy wxGrouponMy = new WxGrouponMy();
                wxGrouponMy.setGroupon(joinGroupon);//groupon    3
                wxGrouponMy.setOrderStatusText("已取消(系统)");//orderStatusText   1
                int creatorUserId=joinGroupon.getCreatorUserId();//找到创建者的name
                User creatorUser = userMapper.selectByPrimaryKey(joinGroupon.getCreatorUserId());
                wxGrouponMy.setCreator(creatorUser.getUsername());//creator   2
                Integer orderId = joinGroupon.getOrderId();
                wxGrouponMy.setOrderId(orderId);//orderId   4
                //通过orderId 找到 orderSn actualPrice
                Order order = orderMapper.selectByPrimaryKey(orderId);
                wxGrouponMy.setOrderSn(order.getOrderSn());//orderSn    5
                wxGrouponMy.setActualPrice(order.getActualPrice().doubleValue());//actualPrice    6
                wxGrouponMy.setJoinerCount(1);   //joinerCount   7
                Integer rulesId = joinGroupon.getRulesId();
                GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(rulesId);
                wxGrouponMy.setRules(grouponRules);//rules     9
                WxGrouponGood wxGrouponGood = new WxGrouponGood();
                wxGrouponGood.setGoodsName(grouponRules.getGoodsName());
                wxGrouponGood.setId(grouponRules.getId());
                wxGrouponGood.setNumber(1);
                wxGrouponGood.setPicUrl(grouponRules.getPicUrl());
                List<WxGrouponGood> list = new ArrayList<>();
                list.add(wxGrouponGood);
                wxGrouponMy.setGoodsList(list);//goodsList   8
                wxGrouponMy.setId(joinGroupon.getId());//  id    10
                wxGrouponMy.setCreator(true); //isCreator   11
                HandleOption handleOption = new HandleOption();
                handleOption.setCancel(false);
                handleOption.setDelete(true);
                handleOption.setComment(false);
                handleOption.setPay(true);
                handleOption.setConfirm(false);
                handleOption.setRefund(false);
                handleOption.setRebuy(false);
                wxGrouponMy.setHandleOption(handleOption);
                data.add(wxGrouponMy);   //add进入data
            }
            wxGrouponMyInfo.setData(data);
            return wxGrouponMyInfo;
        }
        return null;
    }

    @Override
    public WxGrouponDetail queryWxGrouponDetail(int grouponId, int userId) {
        //传入的grouponId   代表 groupon表前的Id
        WxGrouponDetail wxGrouponDetail = new WxGrouponDetail();
        wxGrouponDetail.setLinkGrouponId(grouponId);    //LinkGrouponId   7
        //          GROUPON!!!!
        Groupon groupon = grouponMapper.selectByPrimaryKey(grouponId);
        wxGrouponDetail.setGroupon(groupon);    //groupon  2
        Integer rulesId = groupon.getRulesId();
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(rulesId);
        wxGrouponDetail.setRules(grouponRules); //rules  6
        User creatorFromGroupon = userMapper.selectByPrimaryKey(groupon.getCreatorUserId());
        WxGrouponCreator creator = new WxGrouponCreator();
        creator.setNickname(creatorFromGroupon.getNickname());
        creator.setAvatar(creatorFromGroupon.getAvatar());
        wxGrouponDetail.setCreator(creator);// creator 1
        WxGrouponCreator creator1=new WxGrouponCreator();
        creator1.setAvatar(myprefix+"/a/5/5/6/3/5/5/8/8ab151b3-6f62-4100-b5dd-8db23c646a2e-25.jpg");
        creator1.setNickname("aaaaaa");
        ArrayList<WxGrouponCreator> wxGrouponCreators = new ArrayList<>();
        wxGrouponCreators.add(creator1);
        //wxGrouponJoiners.setJoiners(joiners);
        wxGrouponDetail.setJoiners(wxGrouponCreators);   //joiners   3
        Order order = orderMapper.selectByPrimaryKey(groupon.getId());
        HandleOption handleOption = handleOption(order);
        order.setHandleOption(handleOption);
        order.setOrderStatusText("已取消(系统)");
        wxGrouponDetail.setOrderInfo(order);//  orderInfo   4
        Integer goodsId = grouponRules.getGoodsId();//    拿到了goods_id
        WxGrouponGood orderGood = new WxGrouponGood();
        orderGood.setNumber(1);//1
        orderGood.setPicUrl(grouponRules.getPicUrl());//2
        Integer orderId = groupon.getOrderId();//拿到order_id  找到actualprice
        Order order1 = orderMapper.selectByPrimaryKey(orderId);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        orderGood.setRetailPrice(goods.getRetailPrice().doubleValue());
        orderGood.setOrderId(orderId);//3
        orderGood.setGoodsId(grouponRules.getGoodsId());//4
        String[] strings = new String[]{"标准"};
        orderGood.setGoodsSpecificationValues(strings);//5
        orderGood.setGoodsName(grouponRules.getGoodsName());//7
        orderGood.setId(grouponRules.getId());//6
        ArrayList<WxGrouponGood> wxGrouponGoods = new ArrayList<>();
        wxGrouponGoods.add(orderGood);
        wxGrouponDetail.setOrderGoods(wxGrouponGoods);//OrderGoods  5
        return wxGrouponDetail;
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
            handleOption.setRebuy(true);
            if (order.getComments() > 0) {
                handleOption.setComment(true);
            }
        }
        return handleOption;
    }
}
