package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 13:02
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Region> getAllRegion() {
        return regionMapper.selectAllProvince();
    }
}
