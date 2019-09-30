package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.AdService;
import com.cskaoyan.mall.vo.AdPageList;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class AdController {
    @Autowired
    AdService adService;

    //推广管理--首页
    @RequestMapping("/ad/list")
    public BaseRespVo adList(@RequestParam int page,@RequestParam int limit,@RequestParam String sort,@RequestParam String order){
        //这里接收的四个参数,第一个当前页面数,第二个每页限制数量,其他两个暂时不知道有什么用
        List<Ad> adList=adService.refAdPageList(page,limit);
        if (adList!=null) {
            AdPageList adPageList = new AdPageList(adList.size(), adList);
            BaseRespVo success = BaseRespVo.success(adPageList);
            return success;
        }else{
            return null;
        }
    }

    //推广管理--添加广告--图片上传
    @RequestMapping("/storage/create")
    public BaseRespVo createAdImage(@RequestBody MultipartFile file) throws IOException {
        //前台有判空校验,所以直接用
        BaseRespVo<Storage> baseRespVo=adService.createAdImage(file);
        return baseRespVo;
    }

    //推广管理--添加广告
    @RequestMapping("/ad/create")
    public BaseRespVo<Ad> addAd(@RequestBody Ad ad){
        //前端添加广告某一栏为空就无法发起请求,所以这里不对ad做空值校验
        BaseRespVo<Ad> adBaseRespVo1=adService.addAd(ad);
        return adBaseRespVo1;
    }
}
