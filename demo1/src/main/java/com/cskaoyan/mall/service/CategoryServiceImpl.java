package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 22:11
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        //找L1的全部级别的
        return categoryMapper.findAllCateGoriesByLevel("L1");
    }

    /**
     * 仅仅获取两个参数 label和 value  获取L1的
     * @return
     */
    @Override
    public List<CatAndBrandVo> getAllLevel1() {
        return categoryMapper.findLevel1Categories();
    }

    @Override
    public int insertCategory(Category category) {
        category.setId(null);
        category.setAddTime(new Date());
        category.setUpdateTime(new Date());
        int inserRow = categoryMapper.insertSelective(category);
        return inserRow;
    }

    @Override
    public void updateCategory(Category category) {
        //将更新时间换为现在
        category.setUpdateTime(new Date());
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
