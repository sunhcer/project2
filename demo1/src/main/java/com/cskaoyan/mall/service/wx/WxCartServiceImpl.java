package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.vo.CartListInfo;
import com.cskaoyan.mall.vo.CartTotal;
import com.cskaoyan.mall.vo.CheckedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxCartServiceImpl implements WxCartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public void addCart(Cart cart) {
            cartMapper.insert(cart);
        }

    @Override
    public CartListInfo selectCartList() {

        CartTotal cartTotal = new CartTotal();
        CartListInfo cartListInfo = new CartListInfo();
        List<Cart> carts =  cartMapper.selectCartAll();
        List<Cart> checkedCarts =  cartMapper.selectCartAllChecked();

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
