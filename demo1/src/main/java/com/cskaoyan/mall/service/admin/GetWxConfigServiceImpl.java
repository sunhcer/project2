package com.cskaoyan.mall.service.admin;

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
        String goods = systemMapper.selectKey("cskaoyan_mall_wx_catlog_goods");
        String list = systemMapper.selectKey("cskaoyan_mall_wx_catlog_list");
        String brand = systemMapper.selectKey("cskaoyan_mall_wx_index_brand");
        String hot = systemMapper.selectKey("cskaoyan_mall_wx_index_hot");
        String indexnew = systemMapper.selectKey("cskaoyan_mall_wx_index_new");
        String topic = systemMapper.selectKey("cskaoyan_mall_wx_index_topic");
        String share = systemMapper.selectKey("cskaoyan_mall_wx_share");
        WxConfigBean wxConfigBean = new WxConfigBean(goods,list,brand,hot,indexnew,topic,share);
        return wxConfigBean;
    }

    @Override
    public void changWxConfig(WxConfigBean wxConfigBean) {
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_catlog_goods(),"cskaoyan_mall_wx_catlog_goods");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_catlog_list(),"cskaoyan_mall_wx_catlog_list");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_brand(),"cskaoyan_mall_wx_index_brand");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_hot(),"cskaoyan_mall_wx_index_hot");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_new(),"cskaoyan_mall_wx_index_new");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_index_topic(),"cskaoyan_mall_wx_index_topic");
        systemMapper.changeKey(wxConfigBean.getCskaoyan_mall_wx_share(),"cskaoyan_mall_wx_share");
    }
}
