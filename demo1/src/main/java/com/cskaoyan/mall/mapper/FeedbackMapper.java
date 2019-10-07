package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.vo.UserPage;

import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);
    //
    void insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> queryAllFeedback(UserPage userPage);
}
