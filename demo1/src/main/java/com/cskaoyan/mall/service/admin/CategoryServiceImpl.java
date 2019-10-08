package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${myfile.img-prefix}")
    String imgPrefix;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        //找L1的全部级别的  并且删除状态要为0
        List<Category> categoryList = categoryMapper.findAllCateGoriesByLevel("L1");


        List<Category> categories = insertUrlToCategories(categoryList);
        return categories;

    }

    /**
     * 仅仅获取两个参数 label和 value  获取L1的
     *
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

        String picUrl = category.getPicUrl();
        String iconUrl = category.getIconUrl();
        category.setPicUrl(picUrl.replace(imgPrefix, ""));
        category.setIconUrl(iconUrl.replace(imgPrefix, ""));
        List<Category> children = category.getChildren();
        if (children != null){
            for (Category catagory : children) {
                category.setPicUrl(catagory.getPicUrl().replace(imgPrefix, ""));
                category.setIconUrl(catagory.getIconUrl().replace(imgPrefix, ""));
            }
        }

        int inserRow = categoryMapper.insertSelective(category);
        return inserRow;
    }

    @Override
    public void updateCategory(Category category) {
        //将更新时间换为现在
        category.setUpdateTime(new Date());
        String picUrl = category.getPicUrl();
        String iconUrl = category.getIconUrl();
        category.setPicUrl(picUrl.replace(imgPrefix, ""));
        category.setIconUrl(iconUrl.replace(imgPrefix, ""));
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 删除category 并且删除其子类 (其实只是将其被删除状态置为1)
     *
     * @param category
     */
    @Override
    public void deleteCategory(Category category) {
        List<Category> categoryList = category.getChildren();

        categoryMapper.deleteById(category.getId());
        if (categoryList != null) {
            for (Category category1 : categoryList) {
                //将其子类删除状态全部置为1
                categoryMapper.deleteById(category1.getId());
            }
        }
    }

    /**
     * 根据id查找对应category
     *
     * @param Id id
     */
    @Override
    public Category findCategoryById(Integer Id) {
        Category category = categoryMapper.selectByPrimaryKey(Id);
        insertUrlToCategory(category);
        return category;
    }

    /**
     * 根据pid查找对应category
     *
     * @param Pid pid
     */
    @Override
    public List<Category> findCategoryByPid(Integer Pid) {
        List<Category> categoryList = categoryMapper.findByParentId(Pid);
        List<Category> categories = insertUrlToCategories(categoryList);
        return categories;
    }

    private List<Category> insertUrlToCategories(List<Category> categoryList) {
        for (Category category : categoryList) {
            insertUrlToCategory(category);
        }
        return categoryList;
    }

    private void insertUrlToCategory(Category category) {
        if (category!=null&&category.getPicUrl()!=null)
            category.setPicUrl(imgPrefix + category.getPicUrl());
        if (category!=null&&category.getIconUrl()!=null)
            category.setIconUrl(imgPrefix + category.getIconUrl());
        if(category!=null&&category.getChildren()!=null){
            category.setChildren(insertUrlToCategories(category.getChildren()));
        }
    }

    /**
     * 根据level查找category 参数目前只能为“L1”/“L2”
     *
     * @param level
     * @return
     */
    @Override
    public List<Category> findAllCateGoriesByLevel(String level) {
        List<Category> categoryList = categoryMapper.findAllCateGoriesByLevel(level);
        List<Category> categories = insertUrlToCategories(categoryList);
        return categories;

    }


}
