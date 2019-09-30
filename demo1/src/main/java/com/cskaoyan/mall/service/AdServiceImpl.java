package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.util.IpUtils;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;

    @Value("${my.file.path}")
    String filePath;
    @Override
    public List<Ad> refAdPageList(int page, int limit) {
        //这个分页比之前的简单一点,暂时不用总数目和总页数
        //查询总数目
//        List<Ad> list=adMapper.queryAllAd();
//        int totalAd=list.size();
        //查询总页数
//        int totalPages=0;
//        if (totalAd%limit==0){
//            totalPages=totalAd/limit;
//        }else{
//            totalPages=totalAd/limit+1;
//        }
        //每页显示的条目 products集合  limit page_size offset (currentPage-1)*page_size
        int offsetNum=(page-1)*20;
        List<Ad> list=adMapper.queryCurrentPageAdByPageAndLimit(limit,offsetNum);
        return list;
    }

    @Override
    public BaseRespVo<Storage> createAdImage(MultipartFile file) throws IOException {
        //文件上传api
        //getName=======file
        //getContentType=======image/jpeg
        //getSize=====29393
        //getOriginalFilename========eluoyiliu.jpg
        BaseRespVo<Storage> baseRespVo = new BaseRespVo<>();
        Storage storage = new Storage();
        storage.setName(file.getOriginalFilename());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String imageType=file.getContentType();
        String key=null;
        //图片为gif格式,返回一个错误{"errno":502,"errmsg":"系统内部错误"}
        if("image/jpeg".equals(imageType)){
             key=uuid+".jpg";
            storage.setKey(key);
            storage.setType(imageType);
        }else if ("image/png".equals(imageType)){
             key=uuid+".png";
            storage.setKey(key);
            storage.setType(imageType);
        }else{
            //这里暂时只考虑了这两种格式,其他的比如gif,返回系统错误
            baseRespVo.setErrmsg("系统内部错误");
            baseRespVo.setErrno(502);
            baseRespVo.setData(null);
            return baseRespVo;
        }
        long imageSize=file.getSize();
        int size1=(int)imageSize;
        storage.setSize(size1);
//        String url="http://localhost/wx/storage/fetch/"+key;
        String url="/wx/storage/fetch/"+key;
        storage.setUrl(url);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        String addTime=simpleDateFormat.format(date);
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        //存入数据库并且用一个after返回带有id的storage
        int storage1=adMapper.insertAdImage(storage);
        //存入本地
        File file1 = new File(filePath, key);
        file.transferTo(file1);
        String url1= IpUtils.appendIp(url);
        System.out.println("url1========"+url1);
        storage.setUrl(url1);
        baseRespVo.setData(storage);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @Override
    public BaseRespVo<Ad> addAd(Ad ad) {
        BaseRespVo<Ad> adBaseRespVo = new BaseRespVo<>();
        adBaseRespVo.setErrmsg("成功");
        Date date = new Date();
        ad.setAddTime(date);
        ad.setUpdateTime(date);
        int ref= adMapper.addAd(ad);
        adBaseRespVo.setData(ad);
        return adBaseRespVo;
    }
}
