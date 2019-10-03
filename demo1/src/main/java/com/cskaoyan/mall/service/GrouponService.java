package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BrandList;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponBean;
import com.cskaoyan.mall.bean.OrderPage;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 20:55
 */
public interface GrouponService {
    BrandList<GrouponBean> getGrouponList(OrderPage orderPage);

    void updateGroupon(Groupon groupon);

    boolean insertGroupon(Groupon groupon);

    void deleteGroup(Groupon groupon);
}
