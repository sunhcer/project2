package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.vo.AddOrDeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxAddOrDeleteServiceImpl implements WxAddOrDeleteService {
    @Autowired
    CollectMapper collectMapper;

    @Override
    public AddOrDeleteVo addOrDelete(int type, int valueId) {
        List<Integer> integers = collectMapper.queryAllCollect2();
        AddOrDeleteVo addOrDeleteVo = new AddOrDeleteVo();
        for (Integer integer : integers) {
            if(integer.equals(valueId)){
                collectMapper.deleteValueId(valueId);
                addOrDeleteVo.setType("delete");
                return addOrDeleteVo;
            }
        }
     collectMapper.insert(type,valueId);
        addOrDeleteVo.setType("add");
        return addOrDeleteVo;
    }
}
