package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.AdReceive;
import com.cskaoyan.mall.bean.CouponReceive;
import com.cskaoyan.mall.bean.Storage;
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
}
