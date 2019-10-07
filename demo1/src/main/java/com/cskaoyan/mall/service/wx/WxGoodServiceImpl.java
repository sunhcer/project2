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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGoodServiceImpl implements WxGoodService {

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
        return null;
    }
}
