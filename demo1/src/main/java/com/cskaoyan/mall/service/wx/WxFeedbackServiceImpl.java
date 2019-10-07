package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxFeedbackServiceImpl implements WxFeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public void feedBack(Feedback feedback) {
       feedbackMapper.insert(feedback);

    }
}
