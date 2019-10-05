package com.cskaoyan.mall.service.admin;

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
        //找L1的全部级别的  并且删除状态要为0
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

    /**
     * 删除category 并且删除其子类 (其实只是将其被删除状态置为1)
     * @param category
     */
    @Override
    public void deleteCategory(Category category) {
        List<Category> categoryList = category.getChildren();

        categoryMapper.deleteById(category.getId());
        if (categoryList != null){
            for (Category category1 : categoryList) {
                //将其子类删除状态全部置为1
                categoryMapper.deleteById(category1.getId());
            }
        }
    }
}
