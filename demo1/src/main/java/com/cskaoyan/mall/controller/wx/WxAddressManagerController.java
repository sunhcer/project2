package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.wx.AddressManageService;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.DeleteAddVo;
import com.cskaoyan.mall.vo.WxAddressPjVo;
import com.cskaoyan.mall.vo.WxReturnAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WxAddressManagerController {
     @Autowired
     AddressManageService addressManageService;

     @Autowired
     UserMapper userMapper;

     @RequestMapping("wx/address/list")
     public BaseRespVo queryAllAdd(){
         List<Address> addresses = addressManageService.queryAllAddress();

         ArrayList<Object> list = new ArrayList<>();

         for(Address address:addresses){
             WxAddressPjVo wxAddressPjVo = new WxAddressPjVo(address);
             list.add(wxAddressPjVo);
         }
         BaseRespVo success = BaseRespVo.success(list);
         return success;
     }
     @RequestMapping("wx/region/list")
     public BaseRespVo allProvince(Integer pid){
         List<Region> regions = addressManageService.queryByPid(pid);
         BaseRespVo success = BaseRespVo.success(regions);
         return success;
     }
     @RequestMapping("wx/address/save")
    public BaseRespVo insertAdd(@RequestBody Address address){
         int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
         int i = 0;
         int id=address.getId();
         if(id==0){
             i = addressManageService.insertAddress(address,userId);
         }else {
             i = addressManageService.updateByPrimaryKey(address);
         }
         BaseRespVo success = BaseRespVo.success(i);
         return success;
     }
     @RequestMapping("wx/address/detail")
    public BaseRespVo detailAddress(Integer id){
         Address address = addressManageService.queryById(id);
         WxReturnAddVo wxReturnAddVo = new WxReturnAddVo(address);
         BaseRespVo success = BaseRespVo.success(wxReturnAddVo);
         return success;

     }
     @RequestMapping("wx/address/delete")
      public BaseRespVo deleteAdd(@RequestBody DeleteAddVo deleteAddVo){
         addressManageService.deleteAddressById(deleteAddVo);
         return BaseRespVo.success(null);
     }
}
