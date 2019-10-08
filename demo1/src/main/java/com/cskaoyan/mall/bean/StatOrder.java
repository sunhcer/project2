package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatOrder {

    private double amount;

    private int orders;

    private int customers;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;

    private double pcr;

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

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public double getPcr() {
        return pcr;
    }

    public void setPcr(double pcr) {
        this.pcr = pcr;
    }

    @Override
    public String toString() {
        return "StatOrder{" +
                "amount=" + amount +
                ", orders=" + orders +
                ", customers=" + customers +
                ", day=" + day +
                ", pcr=" + pcr +
                '}';
    }
}
