package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.vo.AddOrDeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxAddOrDeleteServiceImpl implements WxAddOrDeleteService {
    @Autowired
    CollectMapper collectMapper;

    @Override
    public AddOrDeleteVo addOrDelete(int userId, int valueId) {

        AddOrDeleteVo addOrDeleteVo = new AddOrDeleteVo();
        int i = collectMapper.queryMyCollectByUserIdAndValueId(userId, valueId);
        if(i==0){   //没有收藏过  需要添加进去
            Date date = new Date();
            int i1 = collectMapper.insertMyCollect(userId, valueId, date);
            System.out.println(i1);
            addOrDeleteVo.setType("add");
            return addOrDeleteVo;
        }else if(i==1){   //需要删除
            int i2 = collectMapper.deleteMyCollectByUserIdAndValueId(userId, valueId);
            System.out.println(i2);
            addOrDeleteVo.setType("delete");
            return addOrDeleteVo;
        }
        return null;
    }
}
