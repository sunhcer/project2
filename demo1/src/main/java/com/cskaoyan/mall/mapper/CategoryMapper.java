package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CatAndBrandVo> findAllCategoriesToVo();

    List<CatAndBrandVo> findLevel1Categories();

    List<Category> findAllCateGoriesByLevel(String level);

    void deleteById(Integer id);

    Category queryCurrentCategory(@Param("id") int id);

    Category queryWxParentCategory(@Param("pid") int pid);

    List<Category> queryWxBrotherCategory(@Param("pid") int pid,@Param("id") int id);

    List<Category> findByParentId(Integer pid);

}
