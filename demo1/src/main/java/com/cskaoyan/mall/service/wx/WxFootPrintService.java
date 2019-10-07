package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.WxCollectPage;
import com.cskaoyan.mall.vo.WxFootInfo;

public interface WxFootPrintService {

    WxFootInfo queryMyFoot(WxCollectPage wxCollectPage,int userId);

}
