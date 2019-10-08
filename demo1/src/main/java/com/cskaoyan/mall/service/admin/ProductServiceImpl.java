package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bo.GoodsList;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ProductServiceImpl implements ProductService {
    @Value("${myfile.img-prefix}")
    String imgPrefix;

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

    /**
     * 查找所有品牌，只包括名称和id
     *
     * @return 封装的品牌名称和id
     */
    @Override
    public List<CatAndBrandVo> findAllBrandToVo() {
        return brandMapper.findAllBrandToVo();
    }

    /**
     * 查找所有商品类别，只包含id和名称
     *
     * @return 封装的类别id和名称
     */
    @Override
    public List<CatAndBrandVo> findAllCategoriesToVo() {
        return categoryMapper.findAllCategoriesToVo();
    }

    /**
     * 查找所有商品
     *
     * @return 所有商品列表
     */
    @Override
    public List<Goods> findAllGoods() {
        List<Goods> allGoods = goodsMapper.findAllGoods();
        for (Goods good : allGoods) {
            goodsUrlConnect(good);
        }
        return allGoods;
    }

    /**
     * 查找商品数量
     *
     * @return 返回全商品数量
     */
    @Override
    public Integer findAmountOfGoods() {
        return goodsMapper.findAmountOfGoods();
    }

    /**
     * 查找分页商品
     *
     * @param page 分页信息
     * @return 该页商品
     */
    @Override
    public GoodsList findGoodsByPage(GoodsPage page) {
        PageHelper.startPage(page.getPage(), page.getLimit(), page.getSort() + " " + page.getOrder());
        //sort代表要查的子列，order代表排序规则，二者之间需要空格
        List<Goods> goods;
        page.setName(page.getName() == null ? "" : "".equals(page.getName().trim())?"":page.getName().trim());
        page.setGoodsSn(page.getGoodsSn() == null ? "" : "".equals(page.getGoodsSn().trim())?"":page.getGoodsSn().trim());
        if (!"".equals(page.getName())||!"".equals(page.getGoodsSn())) {//改进版：去除前后空格
            goods = goodsMapper.findGoodsByNameAndGoodsSn("%" + page.getName() + "%", page.getGoodsSn());
        } else {
            goods = goodsMapper.findAllGoods();
        }
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        for (Goods good : goods) {
            goodsUrlConnect(good);
        }
        long total = goodsPageInfo.getTotal();
        GoodsList goodsList = new GoodsList();
        goodsList.setItems(goods);
        goodsList.setTotal(total);
        return goodsList;
    }

    /**
     * 消除 商品 url 前缀
     * @param good 商品信息
     */
    private void goodsUrlReplace(Goods good) {
        if (good!=null&&good.getPicUrl() != null)
            good.setPicUrl(good.getPicUrl().replace(imgPrefix, ""));
        if(good!=null&&good.getGallery()!=null){
            for(int i=0;i<good.getGallery().length;i++){
                if(good.getGallery()[i]!=null){
                    good.getGallery()[i]=good.getGallery()[i].replace(imgPrefix,"");
                }
            }
        }

    }


    private void goodsUrlConnect(Goods good) {
        if (good!=null&&good.getPicUrl() != null)
            good.setPicUrl(imgPrefix + good.getPicUrl());
        if(good!=null&&good.getGallery()!=null){
            for(int i=0;i<good.getGallery().length;i++) {
                if (good.getGallery()[i] != null ) {
                    good.getGallery()[i] = imgPrefix + good.getGallery()[i];
                }
            }
        }
    }

    /**
     * 查找分页评论
     *
     * @param page 分页信息
     * @return 该分页评论信息
     */
    @Override
    public CommentsList findCommentsByPage(CommentsPage page) {
        PageHelper.startPage(page.getPage(), page.getLimit(), page.getSort() + " " + page.getOrder());
        //sort代表要查的子列，order代表排序规则，二者之间需要空格
        List<Comment> comments;
//        page.setValueId(page.getValueId()==null?"":"".equals(page.getValueId().trim())?"":page.getValueId().trim());
//        page.setUserId(page.getUserId()==null?"":"".equals(page.getUserId().trim())?"":page.getUserId().trim());
        if (page.getUserId()!=null||page.getValueId()!=null) {//改进版：去除前后空格
            comments = commentMapper.findCommentsByValueIdAndUserId(page.getValueId(), page.getUserId());
        } else {
            comments = commentMapper.findAllComments();
        }
        for (Comment comment : comments) {
            commentUrlConnect(comment);
        }
        PageInfo<Comment> commentsPageInfo = new PageInfo<>(comments);
        long total = commentsPageInfo.getTotal();
        CommentsList commentsList = new CommentsList();
        commentsList.setItems(comments);
        commentsList.setTotal(total);
        return commentsList;
    }

    /**
     * 根据留言id查找留言
     *
     * @param commentId 留言id
     * @return 留言信息
     */
    @Override
    public Comment findCommentById(Integer commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        commentUrlConnect(comment);
        return comment;
    }

    private void commentUrlConnect(Comment comment) {
        if (comment.getPicUrls() != null) {
            String[] picUrls = comment.getPicUrls();
            for (int i = 0; i < picUrls.length; i++) {
                picUrls[i] = imgPrefix + picUrls[i];
            }
            comment.setPicUrls(picUrls);
        }
    }

    /**
     * 上传留言
     *
     * @param comment 留言信息
     * @return 上传留言数量
     */
    @Override
    public int updateComment(Comment comment) {
        commentUrlReplace(comment);
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     * 消除comment url前缀
     * @param comment comment信息
     */
    public void commentUrlReplace(Comment comment) {
        if (comment.getPicUrls() != null) {
            String[] picUrls = comment.getPicUrls();
            for (int i = 0; i < picUrls.length; i++) {
                if (picUrls[i] != null ) {
                    picUrls[i] = picUrls[i].replace(imgPrefix, "");
                }
            }
            comment.setPicUrls(picUrls);
        }
    }

    /**
     * 根据id删除留言信息
     *
     * @param comment 留言信息
     * @return 删除条数
     */
    @Override
    public int deleteCommentById(@RequestBody Comment comment) {
        return commentMapper.deleteByPrimaryKey(comment.getId());
    }

    /**
     * 新增商品
     *
     * @param goods 商品信息
     * @return 新增条数
     */
    @Override
    public Integer addGoods(Goods goods) {
        goodsUrlReplace(goods);
        return goodsMapper.insertSelective(goods);
    }

    /**
     * 新增商品参数
     *
     * @param goodsAttribute 商品参数信息
     * @return 新增条数
     */
    @Override
    public Integer addGoodsAttribute(GoodsAttribute goodsAttribute) {//无url
        return goodsAttributeMapper.insertSelective(goodsAttribute);
    }

    /**
     * 新增商品规格信息
     *
     * @param goodsSpecification 商品规格
     * @return 新增条数
     */
    @Override
    public Integer addGoodsSpecification(GoodsSpecification goodsSpecification) {
        if (goodsSpecification.getPicUrl() != null)
            goodsSpecification.setPicUrl(goodsSpecification.getPicUrl().replace(imgPrefix, ""));
        return goodsSpecificationMapper.insertSelective(goodsSpecification);
    }

    /**
     * 新增商品货物信息
     *
     * @param goodsProduct 商品货物信息
     * @return 新增条数
     */
    @Override
    public Integer addGoodsProduct(GoodsProduct goodsProduct) {
        if (goodsProduct.getUrl() != null)
            goodsProduct.setUrl(goodsProduct.getUrl().replace(imgPrefix, ""));
        return goodsProductMapper.insertSelective(goodsProduct);
    }

    /**
     * 根据商品id查找商品
     *
     * @param id 商品ID
     * @return 新增条数
     */
    @Override
    public Goods findGoodsById(int id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        goodsUrlConnect(goods);
        return goods;
    }

    /**
     * 根据商品id查找商品参数
     *
     * @param goodsId 商品id
     * @return 商品参数集合
     */
    @Override
    public List<GoodsAttribute> findGoodsAttributesByGoodsId(int goodsId) {//无url
        return goodsAttributeMapper.findGoodsAttributesByGoodsId(goodsId);
    }

    /**
     * 根据商品id查找对应货物信息
     *
     * @param goodsId 商品id
     * @return 货物信息
     */
    @Override
    public List<GoodsProduct> findGoodsProductsByGoodsId(int goodsId) {
        List<GoodsProduct> goodsProducts = goodsProductMapper.findGoodsProductsByGoodsId(goodsId);
        for (GoodsProduct goodsProduct : goodsProducts) {
            if (goodsProduct.getUrl() != null) {
                goodsProduct.setUrl(imgPrefix + goodsProduct.getUrl());
            }
        }
        return goodsProducts;
    }

    /**
     * 根据商品id查找对应的规格信息
     *
     * @param goodsId 商品id
     * @return 规格信息
     */
    @Override
    public List<GoodsSpecification> findGoodsSpecificationsByGoodsId(int goodsId) {
        List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.findGoodsSpecificationsByGoodsId(goodsId);
        for (GoodsSpecification goodsSpecification : goodsSpecifications) {
            if (goodsSpecification.getPicUrl() != null) {
                goodsSpecification.setPicUrl(imgPrefix + goodsSpecification.getPicUrl());
            }
        }
        return goodsSpecifications;
    }

    /**
     * 更新商品信息
     *
     * @param goods 商品信息
     * @return 更新条数
     */
    @Override
    public int updateGoods(Goods goods) {
        goodsUrlReplace(goods);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 根据id查找商品参数
     *
     * @param id 参数id
     * @return 商品参数
     */
    @Override
    public GoodsAttribute findGoodsAttributesById(Integer id) {
        return goodsAttributeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据参数id 修改商品参数
     *
     * @param goodsAttribute 商品参数
     * @return 更新条数
     */
    @Override
    public int updateGoodsAttribute(GoodsAttribute goodsAttribute) {
        return goodsAttributeMapper.updateByPrimaryKeySelective(goodsAttribute);
    }

    /**
     * 根据id查找商品规格参数
     *
     * @param id 商品规格id
     * @return 商品规格信息
     */
    @Override
    public GoodsSpecification findGoodsSpecificationById(Integer id) {
        GoodsSpecification goodsSpecification = goodsSpecificationMapper.selectByPrimaryKey(id);
        if (goodsSpecification != null && goodsSpecification.getPicUrl() != null) {
            goodsSpecification.setPicUrl(imgPrefix + goodsSpecification.getPicUrl());
        }
        return goodsSpecification;
    }

    /**
     * 根据id修改商品规格
     *
     * @param goodsSpecification 商品规格
     * @return 修改条数
     */
    @Override
    public int updateGoodsSpecification(GoodsSpecification goodsSpecification) {
        if (goodsSpecification.getPicUrl() != null) {
            goodsSpecification.setPicUrl(goodsSpecification.getPicUrl().replace(imgPrefix, ""));
        }
        return goodsSpecificationMapper.updateByPrimaryKeySelective(goodsSpecification);
    }

    /**
     * 根据货物id查找货物信息
     *
     * @param id 货物id
     * @return 货物信息
     */
    @Override
    public GoodsProduct findGoodsProductsById(Integer id) {
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(id);
        if (goodsProduct != null && goodsProduct.getUrl() != null) {
            goodsProduct.setUrl(imgPrefix + goodsProduct.getUrl());
        }
        return goodsProduct;
    }

    /**
     * 根据货物id修改货物信息
     *
     * @param goodsProduct 货物信息
     * @return 修改条数
     */
    @Override
    public int updateGoodsProducts(GoodsProduct goodsProduct) {
        if (goodsProduct.getUrl() != null) {
            goodsProduct.setUrl(goodsProduct.getUrl().replace(imgPrefix, ""));
        }
        return goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
    }

    @Override
    public List<Brand> findAllBrand() {
        List<Brand> allBrandDetail = brandMapper.findAllBrandDetail();
        for (Brand brand : allBrandDetail) {
            if(brand!=null&&brand.getPicUrl()!=null)
                brand.setPicUrl(imgPrefix+brand.getPicUrl());
        }
        return allBrandDetail;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> l1 = categoryMapper.findAllCateGoriesByLevel("L1");
        for (Category category : l1) {
            if (category.getIconUrl()!=null) {
                category.setIconUrl(imgPrefix + category.getIconUrl());
            }
            if (category.getPicUrl()!=null) {
                category.setPicUrl(imgPrefix + category.getPicUrl());
            }
        }
        return l1;
    }

    /**
     * 根据类别返回商品，首页专用
     *
     * @param id 商品类别id
     * @return 商品列表
     */
    @Override
    public List<Goods> findGoodsByCategoryIdForIndex(Integer id) {
        List<Goods> goodsByCategoryId = goodsMapper.findGoodsByCategoryId(id);
        List<Category> subCategories = categoryMapper.findByParentId(id);
        for (Category subCategory : subCategories) {
            if(subCategory!=null&&subCategory.getId()!=null) {
                List<Goods> goodsAppend = goodsMapper.findGoodsByCategoryId(subCategory.getId());
                goodsByCategoryId.addAll(goodsAppend);
            }
        }

        for (Goods goods : goodsByCategoryId) {
            goodsUrlConnect(goods);
            /*if (goods.getPicUrl()!=null) {
                goods.setPicUrl(imgPrefix + goods.getPicUrl());
            }
            if(goods.getGallery()!=null){
                for(int i=0;i<goods.getGallery().length;i++){
                    if(goods.getGallery()[i]!=null){
                        goods.getGallery()[i]=imgPrefix+goods.getGallery()[i];
                    }
                }*/
            }
        if (goodsByCategoryId.size() > 10) {
            return goodsByCategoryId.subList(0, 10);
        }
        return goodsByCategoryId;
        //返回前面十个商品，此处可改进
    }

    @Override
    public List<Goods> findGoodsLastAdd(Integer number) {
        List<Goods> goodsLastAdd = goodsMapper.findGoodsLastAdd(number);
        return urlConnect(goodsLastAdd);
    }

    private List<Goods> urlConnect(List<Goods> goodsLastAdd) {
        for (Goods goods : goodsLastAdd) {//拼接picurl
            goodsUrlConnect(goods);
            /*if (goods.getPicUrl() != null && !goods.getPicUrl().startsWith("http")) {
                goods.setPicUrl(imgPrefix + goods.getPicUrl());
            }
            if(goods.getGallery()!=null){//拼接gallery
                for (int j=0;j< goods.getGallery().length;j++) {
                    if (goods.getGallery()[j] != null && !goods.getGallery()[j].startsWith("http")) {
                        goods.getGallery()[j]=imgPrefix + goods.getGallery()[j];
                    }
                }*/
            }
        return goodsLastAdd;
    }

    @Override
    public Brand findBrandById(Integer brandId) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        if (brand != null && brand.getPicUrl() != null) {
            brand.setPicUrl(imgPrefix + brand.getPicUrl());
        }
        return brand;
    }

    @Override
    public List<Comment> findCommentByGoodsId(Integer goodsId) {//当前数据库评论并无商品id，故无法返回需要的数据
        List<Comment> commentsByGoodsId = commentMapper.findCommentsByGoodsId(goodsId);
        for (Comment comment : commentsByGoodsId) {
            if (comment != null && comment.getPicUrls() != null) {
                String[] urls=comment.getPicUrls();
                for (int i=0;i<urls.length;i++) {
                    urls[i]=imgPrefix+urls[i];
                }
            }
        }
        return commentsByGoodsId;
    }

    @Override
    public Integer deleteGoodsAttributeById(Integer id) {
        return goodsAttributeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteGoodsProductById(Integer id) {
        return goodsProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteGoodsSpecificationById(Integer id) {
        return goodsSpecificationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteGoodsByid(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 找到最新热销商品
     * @param i 数量
     * @return 商品表
     */
    @Override
    public List<Goods> findGoodsIsHotLastAdd(Integer i) {
        List<Goods> goodsIsHotLastAdd = goodsMapper.findGoodsIsHotLastAdd(i);
        return urlConnect(goodsIsHotLastAdd);
    }
}
