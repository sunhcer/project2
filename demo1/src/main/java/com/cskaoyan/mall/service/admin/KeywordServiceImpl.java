package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.OrderPage;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 17:23
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public BrandList<Keyword> selectKeyWordByCondition(OrderPage orderPage) {
        PageHelper.startPage(orderPage.getPage(), orderPage.getLimit());
        if (orderPage.getSort() != null && orderPage.getOrder() != null) {
            PageHelper.orderBy(orderPage.getSort() + " " + orderPage.getOrder());
        }
        if (orderPage.getKeyword() != null && "".equals(orderPage.getKeyword().trim())) {
            //如果keyword不是null 并且是空字符串 将其设置成null
            orderPage.setKeyword(null);
        }
        if (orderPage.getUrl() != null && "".equals(orderPage.getUrl().trim())) {
            //如果url不是null 并且是空字符串 将其设置成null
            orderPage.setUrl(null);
        }

        if (orderPage.getUrl() != null) {
            orderPage.setUrl("%" + orderPage.getUrl() + "%");
        }
        if (orderPage.getKeyword() != null) {
            orderPage.setKeyword("%" + orderPage.getKeyword() + "%");
        }
        List<Keyword> keywordList = keywordMapper.selectKeyWordByCondition(orderPage);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(keywordList);
        long total = keywordPageInfo.getTotal();
        BrandList<Keyword> list = new BrandList<Keyword>();
        list.setTotal(total);
        list.setItems(keywordList);
        return list;
    }

    @Override
    public void insertKeyword(Keyword keyword) {
        keyword.setId(null);
        int inserNum = keywordMapper.insertSelective(keyword);
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        keyword.setDeleted(true);
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKeySelective(keyword);
    }

    @Override
    public void updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKeySelective(keyword);
    }
}
