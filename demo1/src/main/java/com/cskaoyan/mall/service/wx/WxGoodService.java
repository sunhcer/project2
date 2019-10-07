package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.HotListInfo;
import com.cskaoyan.mall.vo.HotListVo;

import java.util.List;

public interface WxGoodService {
    HotListVo hotListInfo(HotListInfo hotListInfo);

    HotListVo keywordListInfo(HotListInfo hotListInfo);

    HotListVo firstListInfo(HotListInfo hotListInfo);

    List<Goods> selectGoodsRelated(int id);
}
