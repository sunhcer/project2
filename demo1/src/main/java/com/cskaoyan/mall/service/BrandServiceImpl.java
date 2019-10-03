package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.BrandPage;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 15:09
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;
    //将配置文件里面的前缀存入该字符串
    @Value("${myfile.img-prefix}")
    String img_prefix;

    @Override
    public BrandList getBrandList(BrandPage page) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Brand> brands = new ArrayList<>();
        if (page.getSort_time() != null) {
            PageHelper.orderBy(page.getSort_time() + " " + page.getDesc());
        }
        if (page.getName() != null && "".equals(page.getName().trim())) {
            page.setName(null);
        }
        if (page.getId() != null || page.getName() != null) {
            //就是id或者姓名的字段不为null
            if (page.getName() != null) {
                page.setName("%" + page.getName() + "%");
            }
            brands = brandMapper.selectByCondition(page);
        }else{
            brands = brandMapper.findAllBrandDetail();
        }
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        long total = brandPageInfo.getTotal();
        BrandList brandList = new BrandList<Brand>();
        brandList.setTotal(total);

        if (brands != null){
            //不为null 进行操作
            for (Brand brand : brands) {
                if (brand.getPicUrl() != null){
                    //将前缀存入
                    brand.setPicUrl(img_prefix + brand.getPicUrl());
                }
            }
        }
        brandList.setItems(brands);
        return brandList;
    }

    @Override
    public void updateBrand(Brand brand) {
        System.out.println(img_prefix);
        //将当前时间设置为更改时间
        brand.setUpdateTime(new Date());
        if (brand.getPicUrl() != null) {
            String url = brand.getPicUrl().replace(img_prefix, "");
            brand.setPicUrl(url);
        }
        int num = brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteBrandById(Integer id) {
        //假删除  将deleted状态置为1
        brandMapper.deleteById(id);
    }

    @Override
    public void insertBrand(Brand brand) {
        if (brand.getPicUrl() != null) {
            String url = brand.getPicUrl().replace(img_prefix, "");
            brand.setPicUrl(url);
        }
        int insertNum = brandMapper.insertSelective(brand);
    }
}
