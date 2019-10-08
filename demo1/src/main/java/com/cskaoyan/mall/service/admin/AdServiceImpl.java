package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    TopicMapper topicMapper;

    @Value("${myfile.img-prefix}")
    String imgprefix;
/*    @Value("${my.file.path}")
    String filePath;*/
    @Override
    public List<Ad> refAdPageList(int page, int limit) {
        //这个分页比之前的简单一点,暂时不用总数目和总页数
        //查询总数目
//        List<Ad> list1=adMapper.queryAllAd();
//        int totalAd=list1.size();
        //查询总页数
//        int totalPages=0;
//        if (totalAd%limit==0){
//            totalPages=totalAd/limit;
//        }else{
//            totalPages=totalAd/limit+1;
//        }
        //每页显示的条目 products集合  limit page_size offset (currentPage-1)*page_size
        int offsetNum=(page-1)*limit;
        List<Ad> list=adMapper.queryCurrentPageAdByPageAndLimit(limit,offsetNum);
        //遍历list,拼接ip前缀
        for (Ad ad : list) {
            String url=imgprefix+ad.getUrl();
//            url=IpUtils.appendIp(url);
            ad.setUrl(url);
        }
        return list;
    }

    @Override
    public int findTotalOfAllPage() {
        //查询总数目
        List<Ad> list1=adMapper.queryAllAd();
        int totalAd=list1.size();
        return totalAd;
    }

/*    @Override
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
    }*/

    @Override
    public BaseRespVo<Ad> addAd(Ad ad) {
        BaseRespVo<Ad> adBaseRespVo = new BaseRespVo<>();
        adBaseRespVo.setErrmsg("成功");
        Date date = new Date();
        String url=ad.getUrl().replace(imgprefix,"");
        //剪切url前缀,存到数据库
//        url=IpUtils.SplicePreIp(url);
        ad.setUrl(url);
        ad.setAddTime(date);
        ad.setUpdateTime(date);
        ad.setDeleted(false);
        //判断前端传来的link是否为null,这个和修改传的link默认空串不一样,是null
        int ref=0;
        if (ad.getLink()!=null) {
             ref = adMapper.addAd(ad);
        }else{
           ref=adMapper.addAdWithoutLink(ad);
        }
        //拼接url返回
//        url=IpUtils.appendIp(url);
        url=imgprefix+url;
        ad.setUrl(url);
        adBaseRespVo.setData(ad);
        return adBaseRespVo;
    }

    @Override
    public List<Ad> queryAdPageList(AdReceive adReceive) {
        int page=adReceive.getPage();
        int limit=adReceive.getLimit();
        int offsetNum=(page-1)*limit;
        String name=adReceive.getName();
        String content=adReceive.getContent();
        if (name!=null) {
           name  = "%" + name + "%";
        }
        if (content!=null) {
         content = "%" + content + "%";
        }
        List<Ad> list=adMapper.queryPageListAdByPageAndLimit(limit,offsetNum,name,content);
        for (Ad ad : list) {
            String url=imgprefix+ad.getUrl();
//            url=IpUtils.appendIp(url);

            ad.setUrl(url);

        }
        return list;
    }

    @Override
    public int findTotalOfLikePage(AdReceive adReceive) {
        int page=adReceive.getPage();
        int limit=adReceive.getLimit();
        int offsetNum=(page-1)*limit;
        String name=adReceive.getName();
        String content=adReceive.getContent();
        if (name!=null) {
            name  = "%" + name + "%";
        }
        if (content!=null) {
            content = "%" + content + "%";
        }
        List<Ad> list=adMapper.queryAllPageListAdByPageAndLimit(name,content);
        int total=list.size();
        return total;
    }

    @Override
    public BaseRespVo<Ad> updateAd(Ad ad) {
        //这里name和content前端设置不可以为空,link可以为空串,link: "",这样不影响插入
        String preUrl=ad.getUrl();
//        String url=IpUtils.SplicePreIp(preUrl);
        String url=preUrl.replace(imgprefix,"");
        ad.setUrl(url);
        Date date = new Date();
        ad.setUpdateTime(date);
        adMapper.updateAdById(ad);
        //返回带前缀的url
        ad.setUrl(preUrl);
        BaseRespVo success = BaseRespVo.success(ad);
        return success;
    }

    @Override
    public BaseRespVo<Ad> deleteAd(Integer id) {
        adMapper.deleteByPrimaryKey(id);
        BaseRespVo<Ad> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @Override
    public BaseRespVo findAllCoupon(int page, int limit) {
        int total=adMapper.selectCouponAmount();
        //分页
        int offsetNum=(page-1)*limit;
        List<Coupon> list=adMapper.queryCurrentPageCouponByPageAndLimit(limit,offsetNum);
        CouponRef couponRef = new CouponRef(list, total);
        BaseRespVo success = BaseRespVo.success(couponRef);
        return success;
    }

    @Override
    public BaseRespVo findLikeCouponByReceive(CouponReceive receive) {
        //找到模糊查询总数目,
        String name=receive.getName();
        int type1=receive.getType();
        int status1=receive.getStatus();
        if (name!=null) {
            name="%"+name+"%";
        }
       int total = adMapper.selectLikeCouponAmount(name,type1,status1);
        //分页
        int page = receive.getPage();
        int limit = receive.getLimit();
        int offsetNum=(page-1)*limit;
        List<Coupon> list=adMapper.selectLikeCouponPage(name,type1,status1,limit,offsetNum);
        CouponRef couponRef = new CouponRef(list, total);
        BaseRespVo success = BaseRespVo.success(couponRef);
        return success;
    }

    @Override
    public BaseRespVo createCoupon(CouponArray coupon) {
        Date date = new Date();
        coupon.setAddTime(date);
        coupon.setUpdateTime(date);
        coupon.setDeleted(false);
        String s = UUID.randomUUID().toString().replace("-","");
        coupon.setCode(s);
        int ref=couponMapper.insertCouponByAll(coupon);
        BaseRespVo success = BaseRespVo.success(coupon);
        return success;
    }

    @Override
    public BaseRespVo couponRead(int id) {
        CouponArray coupon=couponMapper.selectById(id);
        BaseRespVo success = BaseRespVo.success(coupon);
        return success;
    }

    @Override
    public BaseRespVo couponUpdate(CouponArray coupon) {
        Date date = new Date();
        coupon.setUpdateTime(date);
        couponMapper.couponUpdate(coupon);
        BaseRespVo success = BaseRespVo.success(coupon);
        return success;
    }

    @Override
    public BaseRespVo couponDelete(int id) {
        //假删除,将delete更新为1
        couponMapper.couponDeleteById(id);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @Override
    public BaseRespVo topicList(TopicReceive receive) {
        //查询总页数
        int total=topicMapper.queryTopicAmount();
        //分页
        int currentPage=receive.getPage();
        int pagesize=receive.getLimit();
        int offsetNum=(currentPage-1)*pagesize;
        List<TopicArray> list=topicMapper.queryAllTopic(pagesize,offsetNum);
        for (TopicArray topicArray : list) {
            //拼接url
            String url=imgprefix+topicArray.getPicUrl();
            topicArray.setPicUrl(url);
        }
        TopicArrayPage topicArrayPage = new TopicArrayPage(list, total);
        BaseRespVo success = BaseRespVo.success(topicArrayPage);
        return success;
    }

    @Override
    public BaseRespVo queryLikeTopicPage(TopicReceive receive) {
        //拼接关键字
        String title=receive.getTitle();
        String subtitle = receive.getSubtitle();
        if (title!=null&&title.trim()!=null){
            title="%"+title+"%";
        }
        if (subtitle!=null&&subtitle.trim()!=null){
            subtitle="%"+subtitle+"%";
        }
        //模糊查询总数
        //查询总页数
        int total=topicMapper.queryLikeTopicAmount(title,subtitle);
        //分页
        int currentPage=receive.getPage();
        int pagesize=receive.getLimit();
        int offsetNum=(currentPage-1)*pagesize;
        List<TopicArray> list=topicMapper.queryLikeTopicPage(title,subtitle,pagesize,offsetNum);
        for (TopicArray topicArray : list) {
            //拼接url
            String url=imgprefix+topicArray.getPicUrl();
            topicArray.setPicUrl(url);
        }
        TopicArrayPage topicArrayPage = new TopicArrayPage(list, total);
        BaseRespVo success = BaseRespVo.success(topicArrayPage);
        return success;
    }

    @Override
    public BaseRespVo topicCreate(TopicArray topicArray) {
        Date date = new Date();
        topicArray.setAddTime(date);
        topicArray.setUpdateTime(date);
        topicArray.setDeleted(false);
        //after
        //剪切url
        String url=topicArray.getPicUrl().replace(imgprefix,"");
        topicArray.setPicUrl(url);
        int ref=topicMapper.AddTopic(topicArray);
        //拼接url
        url=imgprefix+topicArray.getPicUrl();
        topicArray.setPicUrl(url);
        BaseRespVo success = BaseRespVo.success(topicArray);
        return success;
    }

    @Override
    public BaseRespVo topicUpdate(TopicArray topicArray) {
        //这里更新时忽略addtime,查询时加上addtime
        //剪切url
        String url=topicArray.getPicUrl().replace(imgprefix,"");
        topicArray.setPicUrl(url);
        topicArray.setUpdateTime(new Date());
        topicArray.setDeleted(false);
        topicMapper.topicUpdate(topicArray);
        //拼接url
        url=imgprefix+topicArray.getPicUrl();
        topicArray.setPicUrl(url);
        BaseRespVo success = BaseRespVo.success(topicArray);
        return success;
    }

    @Override
    public BaseRespVo topicDelete(TopicArray topicArray) {
        topicMapper.topicDelete(topicArray.getId());
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setErrmsg("成功");
        return objectBaseRespVo;
    }

    @Override
    public BaseRespVo couponListuser(CouponQueryUser couponQueryUser) {
        List<CouponUser> list=couponMapper.queryCouponUserByCouponId(couponQueryUser.getCouponId());
        //总页数
        int total=list.size();
        //分页
        int currentPage=couponQueryUser.getPage();
        int pagesize=couponQueryUser.getLimit();
        int offsetNum=(currentPage-1)*pagesize;
        List<CouponUser> list1=couponMapper.queryCouponUserPage(pagesize,offsetNum,couponQueryUser.getCouponId());
        CouponUserPage couponUserPage = new CouponUserPage(list1, total);
        BaseRespVo success = BaseRespVo.success(couponUserPage);
        return success;
    }

    @Override
    public BaseRespVo couponListuserByStatus(CouponQueryUser couponQueryUser) {
        //总页数
        List<CouponUser> list=couponMapper.queryCouponUserByCouponIdAndStatus(couponQueryUser.getCouponId(),
                couponQueryUser.getStatus());
        int total=list.size();
        //分页
        int currentPage=couponQueryUser.getPage();
        int pagesize=couponQueryUser.getLimit();
        int offsetNum=(currentPage-1)*pagesize;
        List<CouponUser> list1=couponMapper.queryCouponUserPageByStatus(pagesize,offsetNum,couponQueryUser.getCouponId(),
                couponQueryUser.getStatus());
        CouponUserPage couponUserPage = new CouponUserPage(list1, total);
        BaseRespVo success = BaseRespVo.success(couponUserPage);
        return success;
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return couponMapper.findAllCoupon();
    }

    @Override
    public List<TopicArray> findTopicLastAdd(Integer i) {
        List<TopicArray> topicLastAdd = topicMapper.findTopicLastAdd(i);
        for (TopicArray topicArray : topicLastAdd) {
            if(topicArray.getPicUrl()!=null){
                topicArray.setPicUrl(imgprefix+topicArray.getPicUrl());
            }
        }
        return topicLastAdd;
    }
}
