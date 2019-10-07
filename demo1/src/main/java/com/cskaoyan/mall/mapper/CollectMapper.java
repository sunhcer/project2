package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.vo.UserCollect;
import com.cskaoyan.mall.vo.UserPage;
import com.cskaoyan.mall.vo.WxCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<UserCollect> queryAllCollect(UserPage userPage);

    List<WxCollect> queryMyCollect(@Param("type") int type,@Param("userId") int userId);
}
