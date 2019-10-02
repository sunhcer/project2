package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.bean.MarkConfigBean;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GetMallConfigeServiceImpl implements GetMallConfigeService {
    @Autowired
    SystemMapper systemMapper;

    @Override
    public MarkConfigBean showMallConfige() {
        String address = systemMapper.selectKey(14);
        String name = systemMapper.selectKey(6);
        String phone = systemMapper.selectKey(12);
        String qq = systemMapper.selectKey(8);
        MarkConfigBean markConfigBean = new MarkConfigBean(address,name,phone,qq);
        return markConfigBean;
    }

    @Override
    public void changeMallConfige(MarkConfigBean markConfigBean) {
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_address(),6);
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_name(),14);
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_phone(),12);
        systemMapper.changeKey(markConfigBean.getCskaoyan_mall_mall_qq(),8);
    }
}
