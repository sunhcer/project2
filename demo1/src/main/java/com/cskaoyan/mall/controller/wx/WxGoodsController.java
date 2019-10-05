package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.service.admin.ProductService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 类简介：微信商品模块，测试用
 * 当前方法：
 * 创建时间: 2019-10-04 15:29
 *
 * @author EGGE
 */
@RestController
public class WxGoodsController {
    @Autowired
    ProductService productService;

    @RequestMapping("wx/goods/count")
    public BaseRespVo countGoods() {
        Integer amountOfGoods = productService.findAmountOfGoods();
        Map<String, Integer> data=new HashMap<String, Integer>();
        data.put("goodsCount",amountOfGoods);
        return BaseRespVo.success(data);
    }

}
