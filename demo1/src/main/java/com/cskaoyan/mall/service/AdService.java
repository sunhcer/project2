package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdService {
    List<Ad> refAdPageList(int page, int limit);

//    BaseRespVo<Storage> createAdImage(MultipartFile file) throws IOException;

    BaseRespVo<Ad>  addAd(Ad ad);

    List<Ad> queryAdPageList(AdReceive adReceive);

    BaseRespVo<Ad> updateAd(Ad ad);

    BaseRespVo<Ad> deleteAd(Integer id);

    int findTotalOfAllPage();

    int findTotalOfLikePage(AdReceive adReceive);

    BaseRespVo findAllCoupon(int page, int limit);

    BaseRespVo findLikeCouponByReceive(CouponReceive receive);

    BaseRespVo createCoupon(CouponArray coupon);

    BaseRespVo couponRead(int id);

    BaseRespVo couponUpdate(CouponArray coupon);

    BaseRespVo couponDelete(int id);

    BaseRespVo topicList(TopicReceive receive);

    BaseRespVo queryLikeTopicPage(TopicReceive receive);

    BaseRespVo topicCreate(TopicArray topicArray);

    BaseRespVo topicUpdate(TopicArray topicArray);

    BaseRespVo topicDelete(TopicArray topicArray);

    BaseRespVo couponListuser(CouponQueryUser couponQueryUser);

    BaseRespVo couponListuserByStatus(CouponQueryUser couponQueryUser);

    List<Coupon> findAllCoupon();

    List<TopicArray> findTopicLastAdd(Integer i);
}
