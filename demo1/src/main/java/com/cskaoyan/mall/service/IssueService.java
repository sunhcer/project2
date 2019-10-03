package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.OrderPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 21:56
 */
public interface IssueService {

    BrandList selectIssueByCondition(OrderPage orderPage);
    int updateIssueById(Issue issue);

    void deleteIssueById(Issue issue);

    void insertIssue(Issue issue);
}
