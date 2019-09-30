package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.ProductService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类简介：后台商品管理模块
 * 当前方法：
 * 创建时间: 2019-09-30 14:06
 *
 * @author EGGE
 */
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 处理请求：显示商品类别和商家
     * 方法用途：返回商品类别和商家列表
     * 操作简介：将返回值封装到CatAndBrandVo类中
     *
     * @author EGGE
     * @date 2019-09-30 16:17:23
     * @return
     **/
    @RequestMapping("/admin/goods/catAndBrand")
    public BaseRespVo catAndBrand(){
        List<CatAndBrandVo> brands=productService.findAllBrandToVo();
        List<CatAndBrandVo> categories=productService.findAllCategoriesToVo();
/*        无法自动封装为date.brands和date.categories
        List<List> dateList=new ArrayList<List>();
        dateList.add(brands);
        dateList.add(categories);
        BaseRespVo success = BaseRespVo.success(dateList);
        return success;*/
        Map date=new HashMap();
        date.put("brandList",brands);
        date.put("categoryList",categories);
        return BaseRespVo.success(date);
    }
    @RequestMapping("admin/goods/list")
    public BaseRespVo productList(GoodsPage page){
       /* 使用心得分页插件会更简单
        List<Goods> items=productService.findGoodsByPage(page);
        Integer total=productService.findAmountOfGoods();
        if(total==null){
            total=0;
        }
        Map data=new HashMap();
        data.put("total",total);
        data.put("items",items);
        return BaseRespVo.success(data);*/
        GoodsList data=productService.findGoodsByPage(page);
        return BaseRespVo.success(data);
    }

    /**
     *
     * @param page
     * @return
     */
    @RequestMapping("admin/comment/list")
    public BaseRespVo productList(CommentsPage page) {
        int i;
        if(page.getUserId()!=null) {
            try {//尝试将userId转化成int，失败则返回error信息
                i = Integer.parseInt(page.getUserId());
            } catch (Exception e) {
                return BaseRespVo.error(null, 402, "参数错误");
            }
        }
        if(page.getValueId()!=null){
            try {//尝试将valueId转化成int，失败则返回error信息
                i = Integer.parseInt(page.getValueId());
            } catch (Exception e) {
                return BaseRespVo.error(null, 402, "参数错误");
            }
        }
        CommentsList data=productService.findCommentsByPage(page);
        return BaseRespVo.success(data);
    }
    @RequestMapping("admin/order/reply")
    public BaseRespVo replyComment(@RequestBody CommitReplyRequest commitReplyRequest) {
        Integer commentId=commitReplyRequest.getCommentId();
        String content=commitReplyRequest.getContent();
        if(commentId==null||content==null)
            return BaseRespVo.error(null, 402, "参数错误");
        Comment comment=productService.findCommentById(commentId);
        if(comment==null||comment.getDeleted())//deleted代表逻辑删除
            return BaseRespVo.error(null, 404, "未能找到该留言，请检查参数");
        if(comment.getContent()!=null)
            return BaseRespVo.error(null, 622, "订单商品已回复!");
        productService.updateComment(comment);
        //未能获取成功时数据，后考虑修改
        return BaseRespVo.success(null);

    }
}
