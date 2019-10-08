package com.cskaoyan.mall.service.wx;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.vo.HotListInfo;
import com.cskaoyan.mall.vo.HotListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGoodServiceImpl implements WxGoodService {

    @Value("${myfile.img-prefix}")
    String imgPrefix;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public HotListVo hotListInfo(HotListInfo hotListInfo) {
        int page = hotListInfo.getPage();
        int limit = hotListInfo.getSize();
        String order = hotListInfo.getOrder();
        String sort = hotListInfo.getSort();
        int categoryId = hotListInfo.getCategoryId();
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Goods> goods;
        if(categoryId == 0) {
            goods = goodsMapper.selectAllHotGoods();
        } else {
            goods = goodsMapper.findGoodsByCategoryId(categoryId);
        }
        List<Category> categories = categoryMapper.selectAllCategory();
        PageInfo<Goods> adminPageInfo = new PageInfo<>();
        long count = adminPageInfo.getTotal();
        HotListVo hotListVo = new HotListVo();
        hotListVo.setCount(count);
        hotListVo.setGoodsList(goods);
        hotListVo.setFilterCategoryList(categories);
        return hotListVo;
    }

    @Override
    public HotListVo keywordListInfo(HotListInfo hotListInfo) {

        int page = hotListInfo.getPage();
        int limit = hotListInfo.getSize();
        String order = hotListInfo.getOrder();
        String sort = hotListInfo.getSort();
        int categoryId = hotListInfo.getCategoryId();
        String keyword = hotListInfo.getKeyword();
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Goods> goods;
        keyword = "%" + keyword + "%";
        if(categoryId == 0) {
            goods = goodsMapper.selectGoodsByNameLike(keyword);
        } else {
            goods = goodsMapper.selectGoodsByNameLikeAndCategoryId(keyword,categoryId);
        }
        List<Category> categories = categoryMapper.selectAllCategory();
        PageInfo<Goods> adminPageInfo = new PageInfo<>();
        long count = adminPageInfo.getTotal();
        HotListVo hotListVo = new HotListVo();
        hotListVo.setCount(count);
        hotListVo.setGoodsList(goods);
        hotListVo.setFilterCategoryList(categories);
        return hotListVo;
    }


    @Override
    public HotListVo firstListInfo(HotListInfo hotListInfo) {
        //找到总数
        boolean isNew=hotListInfo.getIsNew();
        long count=(long)goodsMapper.queryWxNewCategoryGoodsCount(isNew);
        //找到商品分页列表
        int page=hotListInfo.getPage();
        int size=hotListInfo.getSize();
        int offsetNum=(page-1)*size;
        String order=hotListInfo.getOrder();
        String sort=hotListInfo.getSort();
        List<Goods> goodsList =null;
        //根据categoryId来分发
        int categoryId=hotListInfo.getCategoryId();
        if (categoryId==0) {
            //返回全部分类
            goodsList = goodsMapper.queryWxNewGoodsList(isNew, order, sort, size, offsetNum);
        }else{
            //返回选定分类
            goodsList=goodsMapper.queryWXCurrentNewGoodsList(categoryId,isNew, order, sort, size, offsetNum);
        }
        //拼接url
        for (Goods goods : goodsList) {
            String picUrl=imgPrefix+goods.getPicUrl();
            goods.setPicUrl(picUrl);
        }
        List<Category> filterCategoryList=categoryMapper.queryWxFilterCategoryList();
        HotListVo hotListVo = new HotListVo();
        hotListVo.setCount(count);
        hotListVo.setGoodsList(goodsList);
        hotListVo.setFilterCategoryList(filterCategoryList);
        return hotListVo;
    }

    @Override
    public List<Goods> selectGoodsRelated(int id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        Integer brandId = goods.getBrandId();
        List<Goods> goodsList = goodsMapper.selectGoodsByBrandId(brandId);
        return goodsList;
    }
}
