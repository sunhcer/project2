package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bo.GoodsList;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    CommentMapper commentMapper;

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
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
        PageHelper.startPage(page.getPage(),page.getLimit(),page.getSort()+" "+page.getOrder());
        //sort代表要查的子列，order代表排序规则，二者之间需要空格
        List<Goods> goods;
        if(page.getName()!=null||page.getGoodsSn()!=null) {//改进版：去除前后空格
            goods = goodsMapper.findGoodsByNameAndGoodsSn("%"+page.getName().trim()+"%", page.getGoodsSn().trim());
        }else{
            goods=goodsMapper.findAllGoods();
        }
        PageInfo<Goods> goodsPageInfo=new PageInfo<>(goods);
        long total=goodsPageInfo.getTotal();
        GoodsList goodsList=new GoodsList();
        goodsList.setItems(goods);
        goodsList.setTotal(total);
        return goodsList;
    }

    @Override
    public CommentsList findCommentsByPage(CommentsPage page) {
        PageHelper.startPage(page.getPage(),page.getLimit(),page.getSort()+" "+page.getOrder());
        //sort代表要查的子列，order代表排序规则，二者之间需要空格
        List<Comment> comments;
        if(page.getValueId()!=null||page.getUserId()!=null) {//改进版：去除前后空格
            comments = commentMapper.findCommentsByValueIdAndUserId(page.getValueId().trim(), page.getValueId().trim());
        }else{
            comments=commentMapper.findAllComments();
        }
        PageInfo<Comment> commentsPageInfo=new PageInfo<>(comments);
        long total=commentsPageInfo.getTotal();
       CommentsList commentsList=new CommentsList();
        commentsList.setItems(comments);
        commentsList.setTotal(total);
        return commentsList;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public int deleteCommentById(@RequestBody Comment comment) {
        return commentMapper.deleteByPrimaryKey(comment.getId());
    }

    @Override
    public Integer addGoods(Goods goods) {
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public Integer addGoodsAttribute(GoodsAttribute goodsAttribute) {

        return goodsAttributeMapper.insertSelective(goodsAttribute);
    }

    @Override
    public Integer addGoodsSpecification(GoodsSpecification goodsSpecification) {
        return goodsSpecificationMapper.insertSelective(goodsSpecification);
    }

    @Override
    public Integer addGoodsProduct(GoodsProduct goodsProduct) {
        return goodsProductMapper.insertSelective(goodsProduct);
    }
}
