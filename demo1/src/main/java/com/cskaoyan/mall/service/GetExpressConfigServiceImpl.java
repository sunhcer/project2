package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.ExpressConfigBean;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetExpressConfigServiceImpl implements GetExpressConfigService {
    @Autowired
    SystemMapper systemMapper;
    @Override
    public ExpressConfigBean showExpressConfige() {
        String freightMin = systemMapper.selectKey(5);
        String freightValue = systemMapper.selectKey(7);
        ExpressConfigBean expressConfigBean = new ExpressConfigBean(freightMin,freightValue);
        return expressConfigBean;
    }

    @Override
    public void changeExpressConfig(ExpressConfigBean expressConfigBean) {
         systemMapper.changeKey(expressConfigBean.getCskaoyan_mall_express_freight_min(),5);
         systemMapper.changeKey(expressConfigBean.getCskaoyan_mall_express_freight_value(),7);
    }
}
