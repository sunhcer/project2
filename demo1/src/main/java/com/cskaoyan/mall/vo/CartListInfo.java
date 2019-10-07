package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Cart;

import java.util.List;

public class CartListInfo {

    private List<Cart> cartList;

    private CartTotal cartTotal;

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public CartTotal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }
}
