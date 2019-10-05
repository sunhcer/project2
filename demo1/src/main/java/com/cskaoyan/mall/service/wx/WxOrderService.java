package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.vo.WxOrderPage;
import com.cskaoyan.mall.vo.WxOrderVo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:27
 */
public interface WxOrderService {
    WxOrderVo getOrderByShowType(WxOrderPage page);
}
