package com.cskaoyan.mall.vo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/8
 * @Time 8:56
 */
public class WxSubmitOrderIdBean {
    Integer addressId;
    Integer cartId;
    Integer couponId;
    Integer grouponRulesId;
    Integer grouponLinkId;
    String message;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getGrouponRulesId() {
        return grouponRulesId;
    }

    public void setGrouponRulesId(Integer grouponRulesId) {
        this.grouponRulesId = grouponRulesId;
    }

    public Integer getGrouponLinkId() {
        return grouponLinkId;
    }

    public void setGrouponLinkId(Integer grouponLinkId) {
        this.grouponLinkId = grouponLinkId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
