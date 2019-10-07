package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.OrderPage;
import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.util.StringUtils;
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
    UserMapper userMapper;

    @Autowired
    KeywordMapper keywordMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

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

    @Override
    public List<Keyword> selectKeyWordIsDefault() {

        return keywordMapper.selectKeyWordIsDefault();
    }

    @Override
    public List<SearchHistory> getLastHistoryKeywords(Integer number) {
        if(number==null){
            number=0;
        }
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        return searchHistoryMapper.getLastHistoryKeywords(number,userId);
    }

    @Override
    public List<Keyword> selectKeyWordIsHot() {
        return keywordMapper.selectKeyWordIsHot();
    }

    @Override
    public Integer deleteHistory() {
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        return searchHistoryMapper.deleteByUserId(userId);
    }

    @Override
    public Integer addHistoryKeywords(String keyword) {
        SearchHistory searchHistory=new SearchHistory();
        searchHistory.setId(null);
        searchHistory.setAddTime(new Date());
        searchHistory.setUserId(userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName()));
        searchHistory.setKeyword(keyword);
        return searchHistoryMapper.insertSelective(searchHistory);
    }

   /* @Override
    public Keyword searchKeyWord(Keyword keyword) {
        keyword.setId(null);
        keyword.setKeyword("%"+StringUtils.removeEmpty(keyword.getKeyword())+"%");
        keyword.setUrl("%"+StringUtils.removeEmpty(keyword.getUrl())+"%");
        if (keyword.getIsDefault()==null){
            keyword.setIsDefault(false);
        }
        if(keyword.getIsHot()==null){
            keyword.setIsHot(false);
        }
        if


        return null;
    }
    逻辑与所需不匹配，废案
    */
}
