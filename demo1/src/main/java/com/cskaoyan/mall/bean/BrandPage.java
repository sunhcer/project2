package com.cskaoyan.mall.bean;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 14:41
 */
public class Page {
    private int page;
    private int limit;
    private String sort_time;
    private String desc;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort_time() {
        return sort_time;
    }

    public void setSort_time(String sort_time) {
        this.sort_time = sort_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort_time='" + sort_time + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
