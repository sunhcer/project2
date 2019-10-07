package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.vo.CartListInfo;
import com.cskaoyan.mall.vo.CartTotal;
import com.cskaoyan.mall.vo.CheckedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxCartServiceImpl implements WxCartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void addCart(Cart cart) {
        //只接收到number,productId,goodsId
        Integer goodsId = cart.getGoodsId();

        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(cart.getProductId());
        //设置goodsSn
        cart.setGoodsSn(goods.getGoodsSn());
        //设置userId
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        cart.setUserId(userId);
        //设置goodsName
        cart.setGoodsName(goods.getName());
        //设置price
        cart.setPrice(goodsProduct.getPrice());
        //设置pic_url
        cart.setPicUrl(goods.getPicUrl());
        //设置specifications
        cart.setSpecifications(goodsProduct.getSpecifications());
        //设置add,update time
        cart.setAddTime(new Date());
        cart.setUpdateTime(new Date());
        //设置checked
        cart.setChecked(false);
        //设置deleted
        cart.setDeleted(false);
        cartMapper.insert(cart);
        }

    @Override
    public CartListInfo selectCartList() {

        CartTotal cartTotal = new CartTotal();
        CartListInfo cartListInfo = new CartListInfo();
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        List<Cart> carts =  cartMapper.selectCartAll(userId);
        List<Cart> checkedCarts =  cartMapper.selectCartAllChecked(userId);

        cartListInfo.setCartList(carts);

        double checkedGoodsAmount = 0;
        for (Cart checkedCart : checkedCarts) {
            checkedGoodsAmount = checkedGoodsAmount + checkedCart.getNumber() * checkedCart.getPrice().doubleValue();
        }
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);

//        int checkedCount = cartMapper.selectAllCheckedCount();
        cartTotal.setCheckedGoodsCount(checkedCarts.size());


        double goodsAmount = 0;
        for (Cart cart : carts) {
            goodsAmount = goodsAmount + cart.getNumber() * cart.getPrice().doubleValue();
        }
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setGoodsCount(carts.size());

        cartListInfo.setCartTotal(cartTotal);
        return cartListInfo;
    }

    @Override
    public void checkCart(CheckedStatus checkedStatus) {
        Integer[] productIds = checkedStatus.getProductIds();
        int isChecked = checkedStatus.getIsChecked();
        for (int i = 0; i < productIds.length; i++) {
            cartMapper.updateCheckedByProductId(productIds[i],isChecked);
        }
    }

    @Override
    public void updateCart(Cart cart) {
        cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public void deleteCart(CheckedStatus checkedStatus) {
        Integer[] productIds = checkedStatus.getProductIds();
        for (int i = 0; i < productIds.length; i++) {
            cartMapper.deleteByProductId(productIds[i]);
        }
    }
}
