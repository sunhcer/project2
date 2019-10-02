package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.OrderPage;
import com.cskaoyan.mall.mapper.IssueMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 21:56
 */
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Override
    public BrandList selectIssueByCondition(OrderPage orderPage) {
        PageHelper.startPage(orderPage.getPage(), orderPage.getLimit());
        if (orderPage.getOrder() != null && orderPage.getSort() != null){
            PageHelper.orderBy(orderPage.getSort() + " " + orderPage.getOrder());
        }
        if (orderPage.getQuestion() != null && "".equals(orderPage.getQuestion().trim())){
            //当问题是空字符串时候 将其置为null
            orderPage.setQuestion(null);
        }
        if (orderPage.getQuestion() != null){
            //如果不是空字符串 前后拼接%
            String question = orderPage.getQuestion();
            orderPage.setQuestion("%" +question+ "%");
        }
        List<Issue> issueList = issueMapper.selectByCondition(orderPage);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issueList);
        BrandList<Issue> issueBrandList = new BrandList<>();
        issueBrandList.setItems(issueList);
        issueBrandList.setTotal(issuePageInfo.getTotal());
        return issueBrandList;
    }

    @Override
    public int updateIssueById(Issue issue) {
        issue.setUpdateTime(new Date());
        int inserNum = issueMapper.updateByPrimaryKeySelective(issue);
        return inserNum;
    }

    @Override
    public void deleteIssueById(Issue issue) {
        issue.setDeleted(true);
        issueMapper.updateByPrimaryKeySelective(issue);
    }

    @Override
    public void insertIssue(Issue issue) {
        issueMapper.insertIssue(issue);
    }
}
