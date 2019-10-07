package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.vo.CatAndBrandVo;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 22:09
 */
public interface CategoryService {
    List<Category> getAllCategory();

    List<CatAndBrandVo> getAllLevel1();

    int insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

    Category findCategoryById(Integer Id);

    List<Category> findCategoryByPid(Integer Pid);

    List<Category> findAllCateGoriesByLevel(String level);
}
