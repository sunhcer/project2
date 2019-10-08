package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatGood {

    private double amount;

    private int orders;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date day;

    private int products;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "StatGood{" +
                "amount=" + amount +
                ", orders=" + orders +
                ", day=" + day +
                ", products=" + products +
                '}';
    }
}
