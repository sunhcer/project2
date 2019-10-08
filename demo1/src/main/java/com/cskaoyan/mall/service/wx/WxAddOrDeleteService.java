package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.AddOrDeleteVo;

public interface WxAddOrDeleteService {
    //添加或取消收藏
   AddOrDeleteVo  addOrDelete(int type,int valueId);
}
