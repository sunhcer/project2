package com.cskaoyan.mall.service.wx;


import com.cskaoyan.mall.vo.HotListInfo;
import com.cskaoyan.mall.vo.HotListVo;
import org.springframework.stereotype.Service;

@Service
public class WxGoodServiceImpl implements WxGoodService {
    @Override
    public HotListVo hotListInfo(HotListInfo hotListInfo) {
        int page = hotListInfo.getPage();
        int size = hotListInfo.getSize();
        boolean hot = hotListInfo.isHot();
        return null;
    }

    @Override
    public HotListVo keywordListInfo(HotListInfo hotListInfo) {
        return null;
    }

    @Override
    public HotListVo firstListInfo(HotListInfo hotListInfo) {
        return null;
    }
}
