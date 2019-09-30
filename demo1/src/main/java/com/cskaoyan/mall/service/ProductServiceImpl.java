package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 14:07
 *
 * @author EGGE
 */
@Service
public class ProductServiceImpl implements  ProductService {
    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<CatAndBrandVo> findAllBrandToVo() {
        return brandMapper.findAllBrandToVo();
    }

    @Override
    public List<CatAndBrandVo> findAllCategoriesToVo() {
        return categoryMapper.findAllCategoriesToVo();
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    @Override
    public Integer findAmountOfGoods() {
        return goodsMapper.findAmountOfGoods();
    }

    @Override
    public GoodsList findGoodsByPage(GoodsPage page) {
        PageHelper.startPage(page.getPage(),page.getLimit(),page.getDesc());
        List<Goods> goods=goodsMapper.findAllGoods();
        PageInfo<Goods> goodsPageInfo=new PageInfo<>(goods);
        long total=goodsPageInfo.getTotal();
        GoodsList goodsList=new GoodsList();
        goodsList.setItems(goods);
        goodsList.setTotal(total);
        return goodsList;
    }
}
