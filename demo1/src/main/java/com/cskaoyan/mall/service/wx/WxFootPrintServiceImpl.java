package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.vo.WxCollectPage;
import com.cskaoyan.mall.vo.WxFoot;
import com.cskaoyan.mall.vo.WxFootInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxFootPrintServiceImpl implements WxFootPrintService {

    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public WxFootInfo queryMyFoot(WxCollectPage wxCollectPage, int userId) {
        int page = wxCollectPage.getPage();
        int size = wxCollectPage.getSize();
        PageHelper.startPage(page,size);
        //先定userid 是100
        List<WxFoot> list = footprintMapper.queryMyFoot(userId);
        PageInfo<WxFoot> wxFootPageInfo = new PageInfo<>(list);
        long total = wxFootPageInfo.getTotal();
        //wxFootPageInfo.getPageNum();
        int pages = wxFootPageInfo.getPages();
        WxFootInfo wxFootInfo = new WxFootInfo();
        wxFootInfo.setFootprintList(list);
        wxFootInfo.setTotalPages(pages);
        return wxFootInfo;
    }
}
