package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import com.cskaoyan.mall.vo.WxUserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WxHomePageServiceImpl implements WxHomePageService {

    @Value("${myfile.img-prefix}")
    String imgPrefix;

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    TopicMapper topicMapper;

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public BaseRespVo couponList(int page, int size) {
        PageHelper.startPage(page,size);
        List<CouponArray> list=couponMapper.queryWxCouponPage();
        PageInfo<CouponArray> couponArrayPageInfo = new PageInfo<>(list);
        int count=(int)couponArrayPageInfo.getTotal();
        WxCouponPage wxCouponPage = new WxCouponPage(list, count);
        BaseRespVo success = BaseRespVo.success(wxCouponPage);
        return success;
    }

    @Override
    public BaseRespVo grouponList(int page, int size) {
//        "groupon_price": 11.90,团购单价=零售价-团购优惠
//         CounterPrice": 32.90,原价
//         "retailPrice": 12.90 零售单价
        //查询总数
        int total=grouponRulesMapper.queryGrouponRulesAmount();
        int offsetNum=(page-1)*size;
        List<WxGrouponRuleData> list=grouponRulesMapper.queryGrouponRulesDataList(size,offsetNum);
        //遍历更改"groupon_price": 11.90,团购单价=零售价-团购优惠
        for (WxGrouponRuleData wxGrouponRuleData : list) {
            BigDecimal discount = wxGrouponRuleData.getGrouponPrice();
            Goods goods = wxGrouponRuleData.getGoods();
            BigDecimal realGrouponPrice = goods.getRetailPrice().subtract(discount);
            wxGrouponRuleData.setGrouponPrice(realGrouponPrice);
            //拼接goods的url前缀
            String picUrl=imgPrefix+goods.getPicUrl();
            goods.setPicUrl(picUrl);
            wxGrouponRuleData.setGoods(goods);
        }
        WxCouponPage<WxGrouponRuleData> wxGrouponRuleDataWxCouponPage = new WxCouponPage<>(list, total);
        BaseRespVo success = BaseRespVo.success(wxGrouponRuleDataWxCouponPage);
        return success;
    }

    @Override
    public BaseRespVo couponReceive(int couponId, String username) {
        //根据username找出userId
        int userId=userMapper.queryUserIdByUsername(username);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        //根据userId和couponId找到CouponUser
        List<CouponUser> couponUser= couponUserMapper.queryCouponUserByUserCouponId(userId,couponId);
        //找到该劵的每人限制领取的数量;判断是否多领了
        int limit=couponMapper.queryCouponLimitByCouponId(couponId);
        //找到该劵的剩余总数
        Coupon coupon=couponMapper.queryCouponByCouponId(couponId);
        int restTotal=coupon.getTotal();
        if (couponUser.size()<limit){//该用户持有的该劵数量没有超过限制
            if (restTotal>0) {//仓库还有该劵的剩余
                //向Coupon-user表中插入一条数据,并且减少Coupon表中的total
                couponUserMapper.insertWxCouponUser(coupon,userId);
                couponMapper.updateWxCouponTotal(restTotal-1,couponId);
                objectBaseRespVo.setErrno(0);
                objectBaseRespVo.setErrmsg("成功");
                return objectBaseRespVo;
            }else{
                objectBaseRespVo.setErrno(740);
                objectBaseRespVo.setErrmsg("优惠劵被抢空啦");
                return objectBaseRespVo;
            }
        }else{
            objectBaseRespVo.setErrno(740);
            objectBaseRespVo.setErrmsg("领劵数超过限制");
            return objectBaseRespVo;
        }
    }

    @Override
    public BaseRespVo brandWxList(int page, int size) {
        //查询总条数
        int total=brandMapper.queryAllWxBrandAmount();
        //总页数
        int totalPages=0;
        if(total%size==0){
            totalPages=total/size;
        }else{
            totalPages=total/size+1;
        }
        //offsetNum
        int offsetNum=(page-1)*size;
        //List<Brand>
        List<Brand> list=brandMapper.queryWxBrandPage(size,offsetNum);
        //拼接url
        for (Brand brand : list) {
            String url=imgPrefix+brand.getPicUrl();
            brand.setPicUrl(url);
        }
        WxBrandPage<Brand> brandWxBrandPage = new WxBrandPage<>(list, totalPages);
        BaseRespVo success = BaseRespVo.success(brandWxBrandPage);
        return success;
    }

    @Override
    public BaseRespVo wxBrandDetail(int id) {
        Brand brand=brandMapper.queryWxBrandById(id);
        //拼接picUrl
        String url=imgPrefix+brand.getPicUrl();
        brand.setPicUrl(url);
        WxBrandRef wxBrandRef = new WxBrandRef(brand);
        BaseRespVo success = BaseRespVo.success(wxBrandRef);
        return success;
    }

    @Override
    public BaseRespVo topicList(int page, int size) {
        PageHelper.startPage(page, size);
        List<Topic> list=topicMapper.queryWxAllTopic();
        PageInfo<Topic> topicPageInfo = new PageInfo<>(list);
        int count = (int)topicPageInfo.getTotal();
        //拼接url
        for (Topic topic : list) {
            String url=imgPrefix+topic.getPicUrl();
            topic.setPicUrl(url);
        }
        WxTopicPage wxTopicPage = new WxTopicPage(list, count);
        BaseRespVo success = BaseRespVo.success(wxTopicPage);
        return success;
    }

    @Override
    public BaseRespVo topicWxDetail(int id) {
        TopicArray topicArray=topicMapper.queryTopicArrayById(id);
        TopicDetailWxPage topicDetailWxPage = new TopicDetailWxPage(topicArray);
        //拼接url
        String url=imgPrefix+topicArray;
//        String[] goods=new String[100];
        topicArray.setPicUrl(url);

        BaseRespVo success = BaseRespVo.success(topicDetailWxPage);
        return success;
    }

    @Override
    public BaseRespVo topicWxRelated(int id) {
        int min=id-10;
        int max=id+10;
        List<TopicArray> list=topicMapper.queryWxRelatedTopic(min,max);
        //拼接url
        for (TopicArray topicArray : list) {
            String url=imgPrefix+topicArray.getPicUrl();
            topicArray.setPicUrl(url);
        }
        BaseRespVo success = BaseRespVo.success(list);
        return success;
    }

    @Override
    public BaseRespVo wxTopicCommentList(WxCommentList wxCommentList) {

        int pagesize=wxCommentList.getSize();
        int currentPage=wxCommentList.getPage();
        int offsetNum=(currentPage-1)*pagesize;
        int typeId=wxCommentList.getType();
        int valueId=wxCommentList.getValueId();
        //查询总数量;
        int count=commentMapper.queryWxTopicCommentAmount(typeId,valueId);
        //分页
        List<WxUserComment> list=commentMapper.querywxTopicCommentList(typeId,valueId,pagesize,offsetNum);
        WxUserCommentPage<WxUserComment> wxUserCommentPage = new WxUserCommentPage<>(list, count, currentPage);
        BaseRespVo success = BaseRespVo.success(wxUserCommentPage);
        return success;
    }

    @Override
    public BaseRespVo wxCommentPost(WxCommentArray wxCommentArray,String username) {
        //返回带id,hasPicture,addTime,updateTime,userId的评论
        //前台有空评论校验,这里不做判空
        Date date = new Date();
        wxCommentArray.setAddTime(date);
        wxCommentArray.setUpdateTime(date);
        String [] picUrls=wxCommentArray.getPicUrls();
        Boolean hasPicture=true;
        if (picUrls==null){
            hasPicture=false;
        }else{
            hasPicture=true;
        }
        wxCommentArray.setHasPicture(hasPicture);
        //得到userId
        int id = userMapper.queryUserIdByUsername(username);
        wxCommentArray.setUserId(id);
        commentMapper.insertwxCommentPost(wxCommentArray,id);
        BaseRespVo success = BaseRespVo.success(wxCommentArray);
        return success;
    }

    @Override
    public BaseRespVo wxcommentCount(WxCommentList wxCommentList) {
        //总数量;
        int allCount=0;
        //有图的数量
        int hasPicCount=0;
        int valueId=wxCommentList.getValueId();
        int type=wxCommentList.getType();
        List<WxCommentArray> list=commentMapper.querywxTopicPicComment(valueId,type);
        if (list!=null){
            allCount=list.size();
            for (WxCommentArray wxCommentArray : list) {
                if (wxCommentArray.getPicUrls()!=null){
                    hasPicCount+=1;
                }
            }
            WxTopicPicCount wxTopicPicCount = new WxTopicPicCount(hasPicCount, allCount);
            BaseRespVo success = BaseRespVo.success(wxTopicPicCount);
            return success;
        }
        return BaseRespVo.success(new WxTopicPicCount());
    }

    @Override
    public BaseRespVo goodsWxCategory(int id) {

        //查找pid,如果pid=0,则为往下找子目录,如果不为0,先找到父目录
//        int pid=currentCategory.getPid();
        Category parentCategory=categoryMapper.queryWxParentCategory(id);
        if(parentCategory.getPid()==0){//当前为父目录编号
        //查找兄弟类目--老师返回的兄弟list包含自己,并且将第一个设置为当前分类
        List<Category> brotherCategory=categoryMapper.queryWxBrotherCategory(id,id);
        //查找当前类目---老师的category表说明的keywords字段是json,但是自己前端接收的又是String
        Category currentCategory=brotherCategory.get(0);
        WxGoodsCategoryPage<Category> categoryWxGoodsCategoryPage = new WxGoodsCategoryPage<>(parentCategory, currentCategory, brotherCategory);
        BaseRespVo success = BaseRespVo.success(categoryWxGoodsCategoryPage);
        return success;
        }else{
            Category realParentCategory=categoryMapper.queryWxParentCategory(parentCategory.getPid());
            List<Category> brotherCategory=categoryMapper.queryWxBrotherCategory(parentCategory.getPid(),parentCategory.getPid());
//            Category currentCategory=brotherCategory.get(0);
            Category currentCategory=categoryMapper.queryWxParentCategory(id);
            WxGoodsCategoryPage<Category> categoryWxGoodsCategoryPage = new WxGoodsCategoryPage<>(parentCategory, currentCategory, brotherCategory);
            BaseRespVo success = BaseRespVo.success(categoryWxGoodsCategoryPage);
            return success;
        }
    }

    @Override
    public BaseRespVo goodsWxList(int categoryId, int page, int size) {
        //找到过滤的兄弟分类list,或者这里直接返回一个空试试看
        //找到总数
        int count=goodsMapper.queryWxCategoryGoodsCount(categoryId);
        //找商品分页列表
        int offsetNum=(page-1)*size;
        List<Goods> goodsList=goodsMapper.queryWxGoodslist(categoryId,offsetNum,size);
        WxGoodsListPage<Goods> goodsWxGoodsListPage = new WxGoodsListPage<>(count, goodsList);
        BaseRespVo success = BaseRespVo.success(goodsWxGoodsListPage);
        return success;
    }
}
