package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bo.GoodsList;
import com.cskaoyan.mall.vo.CatAndBrandVo;

import java.util.List;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 14:07
 *
 * @author EGGE
 */
public interface ProductService {
    List<CatAndBrandVo> findAllBrandToVo();

    List<CatAndBrandVo> findAllCategoriesToVo();

    List<Goods> findAllGoods();

    Integer findAmountOfGoods();

    GoodsList findGoodsByPage(GoodsPage page);

    CommentsList findCommentsByPage(CommentsPage page);

    Comment findCommentById(Integer commentId);

    int updateComment(Comment comment);

    int deleteCommentById(Comment comment);

    Integer addGoods(Goods goods);

    Integer addGoodsAttribute(GoodsAttribute goodsAttribute);

    Integer addGoodsSpecification(GoodsSpecification goodsSpecification);

    Integer addGoodsProduct(GoodsProduct goodsProduct);

    Goods findGoodsById(int id);

    List<GoodsAttribute> findGoodsAttributesByGoodsId(int goodsId);

    List<GoodsProduct> findGoodsProductsByGoodsId(int goodsId);

    List<GoodsSpecification> findGoodsSpecificationsByGoodsId(int goodsId);

    int updateGoods(Goods goods);

    GoodsAttribute findGoodsAttributesById(Integer id);

    int updateGoodsAttribute(GoodsAttribute goodsAttribute);

    GoodsSpecification findGoodsSpecificationById(Integer id);

    int updateGoodsSpecification(GoodsSpecification goodsSpecification);

    GoodsProduct findGoodsProductsById(Integer id);

    int updateGoodsProducts(GoodsProduct goodsProduct);

    List<Brand> findAllBrand();

    List<Category> findAllCategories();

    List<Goods> findGoodsByCategoryIdForIndex(Integer id);

    List<Goods> findGoodsIsHotLastAdd(Integer i);

    List<Goods> findGoodsLastAdd(Integer number);

    Brand findBrandById(Integer brandId);

    List<Comment> findCommentByGoodsId(Integer goodsId);

    Integer deleteGoodsAttributeById(Integer id);

    Integer deleteGoodsProductById(Integer id);

    Integer deleteGoodsSpecificationById(Integer id);

    Integer deleteGoodsByid(Integer id);

    List<CatAndBrandVo> findAllCategoriesToVoByLevel();
}
