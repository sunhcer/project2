package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.MarkConfigBean;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GetMallConfigeServiceImpl implements GetMallConfigeService {
    @Autowired
    SystemMapper systemMapper;

    @Override
    public MarkConfigBean showMallConfige() {
        String address = systemMapper.selectKey("cskaoyan_mall_mall_address");
        String name = systemMapper.selectKey("cskaoyan_mall_mall_name");
        String phone = systemMapper.selectKey("cskaoyan_mall_mall_phone");
        String qq = systemMapper.selectKey("cskaoyan_mall_mall_qq");
        MarkConfigBean markConfigBean = new MarkConfigBean(address,name,phone,qq);
        return markConfigBean;
    }

    @Override
    public void changeMallConfige(MarkConfigBean markConfigBean) {
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_address(),"cskaoyan_mall_mall_address");
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_name(),"cskaoyan_mall_mall_name");
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_phone(),"cskaoyan_mall_mall_phone");
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_qq(),"cskaoyan_mall_mall_qq");
    }
}
