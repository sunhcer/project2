package com.cskaoyan.mall.vo;

public class HotListInfo {

    private boolean isHot;

    private boolean isNew;

    private int page;

    private int size;

    private String order;

    private String sort;

    private int categoryId;

    private String keyword;

    @Override
    public String toString() {
        return "HotListInfo{" +
                "isHot=" + isHot +
                ", isNew=" + isNew +
                ", page=" + page +
                ", size=" + size +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                ", categoryId=" + categoryId +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public boolean getIsNew() {
        return this.isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
