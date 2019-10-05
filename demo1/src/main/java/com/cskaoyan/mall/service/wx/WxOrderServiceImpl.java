package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.vo.WxOrderPage;
import com.cskaoyan.mall.vo.WxOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:28
 */
@RestController
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public WxOrderVo getOrderByShowType(WxOrderPage page) {
        //首先在这里要拿到人的信息
        return null;
    }
}
