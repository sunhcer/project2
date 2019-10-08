package com.cskaoyan.mall.bean;

import javax.lang.model.element.NestingKind;

/**
 * 类简介：评价分页数据接收
 * 创建时间: 2019-09-30 20:22
 *
 * @author EGGE
 */
public class CommentsPage {
  private int page;
  private int limit;
//  private String userId;
  private Integer userId;
//  private String valueId;
  private Integer valueId;
  private String sort;
  private String order;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CommentsPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", userId=" + userId +
                ", valueId=" + valueId +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
