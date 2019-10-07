package com.cskaoyan.mall.service.wx;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.vo.HotListInfo;
import com.cskaoyan.mall.vo.HotListVo;
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
        int size = hotListInfo.getSize();
        boolean hot = hotListInfo.getIsHot();
        return null;
    }

    @Override
    public HotListVo keywordListInfo(HotListInfo hotListInfo) {
        return null;
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
        List<Category> filterCategoryList=categoryMapper.queryWxFilterCategoryList();
        HotListVo hotListVo = new HotListVo();
        hotListVo.setCount(count);
        hotListVo.setGoodsList(goodsList);
        hotListVo.setFilterCategoryList(filterCategoryList);
        return hotListVo;
    }
}
