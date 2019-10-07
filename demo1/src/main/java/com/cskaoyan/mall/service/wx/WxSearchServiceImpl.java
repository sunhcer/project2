package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxSearchServiceImpl implements WxSearchService {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<String> searchHelper(String keyword) {
        keyword = "%" + keyword + "%";
        List<String> keywords = keywordMapper.selectKeywordsByKeywordLike(keyword);
        return keywords;
    }
}
