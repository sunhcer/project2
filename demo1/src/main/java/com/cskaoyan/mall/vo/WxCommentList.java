package com.cskaoyan.mall.vo;

public class WxCommentList {
    int valueId;
    int type;
    int showType;
    int page;
    int size;

    @Override
    public String toString() {
        return "WxCommentList{" +
                "valueId=" + valueId +
                ", type=" + type +
                ", showType=" + showType +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
