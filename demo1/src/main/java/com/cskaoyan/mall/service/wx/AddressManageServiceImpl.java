package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.vo.DeleteAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressManageServiceImpl implements AddressManageService {
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    RegionMapper regionMapper;

    @Autowired
    UserMapper userMapper;
    //查询所有地址
    @Override
    public List<Address> queryAllAddress(){
        List<Address> wxAddressBeans = addressMapper.queryAllAdd();
        return wxAddressBeans;
    }
    //选择 省 市 区
    @Override
    public List<Region> queryByPid(Integer pid) {
        List<Region> regions = regionMapper.selectAllProvince2(pid);
        return regions;
    }
    //添加地址
    @Override
    public int insertAddress(Address address,int userId) {
        User user=userMapper.selectByPrimaryKey(userId);
        String name=user.getUsername();//拿到名字
        address.setName(name);
        address.setUserId(userId);
        int insert = addressMapper.insert(address);


        return insert;
    }
    //编辑时显示地址信息
    @Override
    public Address queryById(Integer id) {
        Address address = addressMapper.queryByID(id);
        return address;

    }
    //删除地址
    @Override
    public void deleteAddressById(DeleteAddVo deleteAddVo) {

        addressMapper.deleteByPrimaryKey(deleteAddVo.getId());
    }


}
