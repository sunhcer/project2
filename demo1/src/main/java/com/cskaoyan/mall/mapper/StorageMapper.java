package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    List<Storage> selectAllStorages(@Param("key") String key, @Param("name") String name);

    @Update("update cskaoyan_mall_storage set deleted = 1 where id = #{id}")
    void storageDelete(Integer id);
}
