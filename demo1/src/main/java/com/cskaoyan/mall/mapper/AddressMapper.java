package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.vo.UserAddress;
import com.cskaoyan.mall.vo.UserPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    //删除地址
    void deleteByPrimaryKey(@Param("id")Integer id);
    //插入地址
    int insert(Address address);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer  id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<UserAddress> queryUsersAddress(UserPage userPage);
    //显示所有地址
    List<Address> queryAllAdd();
   //
    Address selectProvince();
    Address selectCity();
    Address selectArea();

}
