package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Address;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 10:44
 */

/**
 * "actualPrice":89.00,
 * "orderTotalPrice":89.00,
 * "couponPrice":0,
 * "availableCouponLength":3,
 * "couponId":0,
 * "freightPrice":0,
 * "checkedGoodsList":[],
 * "goodsTotalPrice":89.00,
 * "addressId":44
 */
public class WxOrderCheckoutBean {
    BigDecimal grouponPrice;
    int grouponRulesId;
    Address checkedAddress;
    double actualPrice;
    double orderTotalPrice;
    double couponPrice;
    int availableCouponLength;
    int couponId;
    double freightPrice;
    List<CheckOrderGood> checkedGoodsList;
    BigDecimal goodsTotalPrice;
    int addressId;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getGrouponRulesId() {
        return grouponRulesId;
    }

    public void setGrouponRulesId(int grouponRulesId) {
        this.grouponRulesId = grouponRulesId;
    }

    public Address getCheckedAddress() {
        return checkedAddress;
    }

    public void setCheckedAddress(Address checkedAddress) {
        this.checkedAddress = checkedAddress;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public int getAvailableCouponLength() {
        return availableCouponLength;
    }

    public void setAvailableCouponLength(int availableCouponLength) {
        this.availableCouponLength = availableCouponLength;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public double getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(double freightPrice) {
        this.freightPrice = freightPrice;
    }

    public List<CheckOrderGood> getCheckedGoodsList() {
        return checkedGoodsList;
    }

    public void setCheckedGoodsList(List<CheckOrderGood> checkedGoodsList) {
        this.checkedGoodsList = checkedGoodsList;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public BigDecimal getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }
}
