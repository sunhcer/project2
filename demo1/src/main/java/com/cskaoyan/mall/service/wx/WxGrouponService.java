package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.WxGrouponDetail;
import com.cskaoyan.mall.vo.WxGrouponMyInfo;

public interface WxGrouponService {

    WxGrouponMyInfo queryWxMyGroupon(int showType,int userId);

    WxGrouponDetail queryWxGrouponDetail(int grouponId,int userId);
}
