package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.WxConfigBean;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetWxConfigServiceImpl implements GetWxConfigService {
    @Autowired
    SystemMapper systemMapper;
    @Override
    public WxConfigBean showWxConfig() {
        String goods = systemMapper.selectKey(11);
        String list = systemMapper.selectKey(13);
        String brand = systemMapper.selectKey(15);
        String hot = systemMapper.selectKey(9);
        String indexnew = systemMapper.selectKey(2);
        String topic = systemMapper.selectKey(16);
        String share = systemMapper.selectKey(4);
        WxConfigBean wxConfigBean = new WxConfigBean(goods,list,brand,hot,indexnew,topic,share);
        return wxConfigBean;
    }

    @Override
    public void changWxConfig(WxConfigBean wxConfigBean) {
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_catlog_goods(),11);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_catlog_list(),13);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_brand(),15);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_hot(),9);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_new(),2);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_topic(),16);
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_share(),4);
    }
}
