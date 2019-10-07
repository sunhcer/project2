package com.cskaoyan.mall.vo;

public class WxTopicPicCount {
    int hasPicCount;
    int allCount;

    public WxTopicPicCount() {
    }

    public WxTopicPicCount(int hasPicCount, int allCount) {
        this.hasPicCount = hasPicCount;
        this.allCount = allCount;
    }

    @Override
    public String toString() {
        return "WxTopicPicCount{" +
                "hasPicCount=" + hasPicCount +
                ", allCount=" + allCount +
                '}';
    }

    public int getHasPicCount() {
        return hasPicCount;
    }

    public void setHasPicCount(int hasPicCount) {
        this.hasPicCount = hasPicCount;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }
}
