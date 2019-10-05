package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.BaseRespVo;

public interface WxHomePageService {
    BaseRespVo couponList(int page, int size);
}
