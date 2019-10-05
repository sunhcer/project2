package com.cskaoyan.mall.vo;

public class CheckedStatus {

    private Integer[] productIds;

    private int isChecked;

    public Integer[] getProductIds() {
        return productIds;
    }

    public void setProductIds(Integer[] productIds) {
        this.productIds = productIds;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
