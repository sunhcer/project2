package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.service.wx.WxOrderService;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WxOrderService wxOrderService;
    @Autowired
    StorageService storageService;
    @Autowired
    UserMapper userMapper;
    ///wx/order/list
    @RequestMapping("/wx/order/list")
    public BaseRespVo order(WxOrderPage page){
        logger.warn("用户" + SecurityUtils.getSubject().getSession());
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxOrderVo wxOrderVo = wxOrderService.getOrderByShowType(userId, page);
        return BaseRespVo.success(wxOrderVo);
    }

    ///wx/order/detail?orderId=311
    @RequestMapping("/wx/order/detail")
    public BaseRespVo detail(int orderId){
//        logger.warn("用户" + SecurityUtils.getSubject().getSession());
        WxOrderDetailData wxOrderDetailData = wxOrderService.getOrderByOrderId(orderId);
        return BaseRespVo.success(wxOrderDetailData);
    }

    ///wx/user/index
    @RequestMapping("/wx/user/index")
    private BaseRespVo wxUserIndex(){
        Subject subject = SecurityUtils.getSubject();
//        logger.warn("用户账号" + subject.getPrincipal().toString());
//        logger.warn("用户sessionid" + subject.getSession().getId());
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        Map order = wxOrderService.getStateNum(userId);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("order", order);
        return BaseRespVo.success(map);
    }

    ///wx/order/cancel
    @RequestMapping("/wx/order/cancel")
    public BaseRespVo cancelOrder(@RequestBody Order order){
//        logger.warn("用户" + SecurityUtils.getSubject().getPrincipal().toString());
//        logger.warn("用户" + SecurityUtils.getSubject().getSession());
        Integer orderId = order.getOrderId();
        wxOrderService.cancelOrder(orderId);
        return BaseRespVo.success(null);
    }

    ///wx/order/prepay
    @RequestMapping("/wx/order/prepay")
    public BaseRespVo prepay(@RequestBody Order order){
        wxOrderService.prepayOrder(order.getOrderId());
        return BaseRespVo.success(0);
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

    ///wx/order/comment
    //{"content":"太好了","star":5,"hasPicture":false,"picUrls":[]}
    @RequestMapping("/wx/order/comment")
    public BaseRespVo comment(@RequestBody Comment comment){
        wxOrderService.commentOrder(comment);
        return BaseRespVo.success(null);
    }

    ///wx/storage/upload
    @RequestMapping("/wx/storage/upload")
    public BaseRespVo fileUpload(MultipartFile file) throws IOException {
        file.getOriginalFilename();
        Storage storage = storageService.insertStorage(file);

        return BaseRespVo.success(storage);
    }

    ///wx/cart/checkout?cartId=0&addressId=0&couponId=0&grouponRulesId=0
    @RequestMapping("/wx/cart/checkout")
    public BaseRespVo checkoutGoods(int cartId, int addressId, int couponId, int grouponRulesId){
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        WxOrderCheckoutBean wxOrderCheckoutBean = wxOrderService.checkOrder(userId, cartId, addressId, couponId, grouponRulesId);
        return BaseRespVo.success(wxOrderCheckoutBean);
    }

    ///wx/order/goods?orderId=4&goodsId=1109008
    @RequestMapping("/wx/order/goods")
    public BaseRespVo orderGoods(int orderId, int goodsId){
        OrderGoods orderGoods = wxOrderService.selectOrderGoods(orderId, goodsId);
        return BaseRespVo.success(orderGoods);
    }

    ///wx/order/submit
    @RequestMapping("/wx/order/submit")
    public BaseRespVo submitOrder(@RequestBody Map map){
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        String addressId = map.get("addressId").toString();
        Object message = map.get("message");
        int order = wxOrderService.submitOrder(userId, addressId, message);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("orderId", order);
        return BaseRespVo.success(hashMap);
    }
}
