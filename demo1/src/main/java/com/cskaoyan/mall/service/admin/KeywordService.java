package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.OrderPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 17:23
 */
public interface KeywordService {
    BrandList<Keyword> selectKeyWordByCondition(OrderPage orderPage);

    void insertKeyword(Keyword keyword);

    void deleteKeyword(Keyword keyword);

    void updateKeyword(Keyword keyword);
}