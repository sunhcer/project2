package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.vo.WxCollect;
import com.cskaoyan.mall.vo.WxCollectInfo;
import com.cskaoyan.mall.vo.WxCollectPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxCollectServiceImpl implements WxCollectService{

    @Autowired
    CollectMapper collectMapper;

    @Value("${myfile.img-prefix}")
    String myprefix;

    @Override
    public WxCollectInfo queryMyCollect(WxCollectPage wxCollectPage, int userId) {
        int page = wxCollectPage.getPage();
        int size = wxCollectPage.getSize();
        int type = wxCollectPage.getType();
        PageHelper.startPage(page,size);
        List<WxCollect> list = collectMapper.queryMyCollect(type, userId);
        for (WxCollect wxCollect : list) {
            String picUrl = wxCollect.getPicUrl();
            picUrl=myprefix+picUrl;
            wxCollect.setPicUrl(picUrl);
        }
        PageInfo<WxCollect> wxCollectPageInfo = new PageInfo<>(list);
        int pages = wxCollectPageInfo.getPages();
        WxCollectInfo wxCollectInfo = new WxCollectInfo();
        wxCollectInfo.setTotalPages(pages);
        wxCollectInfo.setCollectList(list);
        return wxCollectInfo;
    }
}
