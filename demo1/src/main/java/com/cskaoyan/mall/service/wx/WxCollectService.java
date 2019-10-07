package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.WxCollectInfo;
import com.cskaoyan.mall.vo.WxCollectPage;

public interface WxCollectService {

    WxCollectInfo queryMyCollect(WxCollectPage wxCollectPage,int userId);

}
