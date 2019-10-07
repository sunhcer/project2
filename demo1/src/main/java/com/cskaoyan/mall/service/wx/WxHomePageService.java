package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxCommentArray;
import com.cskaoyan.mall.vo.WxCommentList;

public interface WxHomePageService {
    BaseRespVo couponList(int page, int size);

    BaseRespVo couponReceive(int couponId, String username);

    BaseRespVo grouponList(int page, int size);

    BaseRespVo brandWxList(int page, int size);

    BaseRespVo wxBrandDetail(int id);

    BaseRespVo topicList(int page, int size);

    BaseRespVo topicWxDetail(int id);

    BaseRespVo topicWxRelated(int id);

    BaseRespVo wxTopicCommentList(WxCommentList wxCommentList);

    BaseRespVo wxCommentPost(WxCommentArray wxCommentArray,String username);

    BaseRespVo wxcommentCount(WxCommentList wxCommentList);

    BaseRespVo goodsWxCategory(int id);

    BaseRespVo goodsWxList(int categoryId, int page, int size);
}
