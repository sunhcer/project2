package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.vo.UserCollect;
import com.cskaoyan.mall.vo.UserPage;
import com.cskaoyan.mall.vo.WxCollect;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<UserCollect> queryAllCollect(UserPage userPage);

    //query all valueId
    List<Integer> queryAllCollect2();
    //add valueID
    void insert(@Param("type") int type,@Param("valueId") int valueId);
    //delete valueID
    void deleteValueId(@Param("valueId") int valueId);

    List<WxCollect> queryMyCollect(@Param("type") int type,@Param("userId") int userId);

    int queryMyCollectByUserIdAndValueId(@Param("userId") int userId,@Param("valueId") int valueId);

    int insertMyCollect
            (@Param("userId") int userId, @Param("valueId") int valueId, @Param("addTime")Date time);

    int deleteMyCollectByUserIdAndValueId
            (@Param("userId") int userId,@Param("valueId") int valueId);
}
