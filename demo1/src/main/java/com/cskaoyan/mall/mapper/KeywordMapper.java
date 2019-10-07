package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.OrderPage;

import java.util.List;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

    List<Keyword> selectKeyWordByCondition(OrderPage orderPage);

    List<Keyword> selectKeyWordIsDefault();

    List<Keyword> selectKeyWordIsHot();

    List<String> selectKeywordsByKeywordLike(String keyword);

}
