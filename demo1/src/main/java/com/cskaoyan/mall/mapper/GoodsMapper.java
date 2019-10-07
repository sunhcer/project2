package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.vo.CheckOrderGood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAllGoods();

    Integer findAmountOfGoods();

    List<Goods> findGoodsByNameAndGoodsSn(@Param("name")String name, @Param("goodsSn") String goodsSn);

    StatGood[] getStatGoods();

    List<Goods> findGoodsByCategoryId(Integer id);

    List<Goods> findGoodsIsHotLastAdd(Integer i);

    List<Goods> findGoodsLastAdd(Integer number);


    int queryWxCategoryGoodsCount(@Param("categoryId") int categoryId);

    List<Goods> queryWxGoodslist(@Param("categoryId") int categoryId,@Param("offsetNum") int offsetNum,@Param("pageSize") int size);

    int queryWxNewCategoryGoodsCount(@Param("isNew") boolean isNew);

    List<Goods> queryWxNewGoodsList(@Param("isNew") boolean isNew,@Param("order") String order, @Param("sort") String sort,@Param("size") int size,@Param("offsetNum") int offsetNum);

    List<Goods> queryWXCurrentNewGoodsList(@Param("categoryId") int categoryId,@Param("isNew") boolean isNew,@Param("order") String order, @Param("sort") String sort,@Param("size") int size,@Param("offsetNum") int offsetNum);

    CheckOrderGood selectByGoodsSn(String goodsSn);
    List<Goods> selectAllHotGoods();

    List<Goods> selectGoodsByNameLike(String keyword);

    List<Goods> selectGoodsByNameLikeAndCategoryId(@Param("keyword") String keyword,@Param("categoryId") int categoryId);

}
