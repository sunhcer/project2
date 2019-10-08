package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.service.wx.WxFeedbackService;
import com.cskaoyan.mall.service.wx.WxFootPrintService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.DeleteFootVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxFeedbackController {
    @Autowired
    WxFeedbackService wxFeedbackService;
    @Autowired
    WxFootPrintService wxFootPrintService;
    @RequestMapping("wx/feedback/submit")
    public BaseRespVo submitFeedB(@RequestBody Feedback feedback){
      wxFeedbackService.feedBack(feedback);
        BaseRespVo success = BaseRespVo.success(null);
        return success;
    }
    @RequestMapping("wx/footprint/delete")
    public BaseRespVo footPrintD(@RequestBody DeleteFootVo deleteFootVo){
        wxFootPrintService.footDeleteById(deleteFootVo);
        BaseRespVo success = BaseRespVo.success(null);
        return success;
    }
}
