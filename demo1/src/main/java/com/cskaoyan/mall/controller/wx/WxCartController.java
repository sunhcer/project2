package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.service.wx.WxCartService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CartListInfo;
import com.cskaoyan.mall.vo.CheckedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxCartController {

    @Autowired
    WxCartService wxCartService;

    @RequestMapping("/wx/cart/index")
    public BaseRespVo cartIndex() {
        CartListInfo cartListInfo = wxCartService.selectCartList();
        return BaseRespVo.success(cartListInfo);
    }

    @RequestMapping("/wx/cart/checked")
    public BaseRespVo cartChecked(@RequestBody CheckedStatus checkedStatus) {
        wxCartService.checkCart(checkedStatus);
        CartListInfo cartListInfo = wxCartService.selectCartList();
        return BaseRespVo.success(cartListInfo);
    }

    @RequestMapping("/wx/cart/update")
    public BaseRespVo cartUpdate(@RequestBody Cart cart) {
        wxCartService.updateCart(cart);
        return BaseRespVo.success(null);
    }

    @RequestMapping("/wx/cart/delete")
    public BaseRespVo cartDelete(@RequestBody CheckedStatus checkedStatus) {
        wxCartService.deleteCart(checkedStatus);
        CartListInfo cartListInfo = wxCartService.selectCartList();
        return BaseRespVo.success(cartListInfo);
    }

    @RequestMapping("/wx/cart/goodscount")
    public BaseRespVo showGoodsCount() {
        CartListInfo cartListInfo = wxCartService.selectCartList();
        int goodsCount = cartListInfo.getCartTotal().getGoodsCount();
        return BaseRespVo.success(goodsCount);
    }

    @RequestMapping("/wx/cart/add")
    public BaseRespVo addCart(@RequestBody Cart cart) {
        wxCartService.addCart(cart);
        CartListInfo cartListInfo = wxCartService.selectCartList();
        int goodsCount = cartListInfo.getCartTotal().getGoodsCount();
        return BaseRespVo.success(goodsCount);
    }
}
