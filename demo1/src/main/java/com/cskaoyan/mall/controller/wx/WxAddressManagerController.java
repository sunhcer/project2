package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.service.wx.AddressManageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WxAddressManagerController {
     @Autowired
     AddressManageService addressManageService;

     @RequestMapping("wx/address/list")
     public BaseRespVo queryAllAdd(){
         //Map<String,Object> data =new HashMap<String, Object>();
         List<Address> wxAddressBeans = addressManageService.queryAllAddress();
         BaseRespVo success = BaseRespVo.success(wxAddressBeans);
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
         int i = addressManageService.insertAddress(address);
         BaseRespVo success = BaseRespVo.success(i);
         return success;
     }
     @RequestMapping("wx/address/detail")
    public BaseRespVo detailAddress(Integer id){
         List<Region> regions = addressManageService.queryById(id);
         BaseRespVo success = BaseRespVo.success(regions);
         return success;
     }
     @RequestMapping("wx/address/delete")
      public BaseRespVo deleteAdd(Integer id){
         addressManageService.deleteAddressById(id);
         return BaseRespVo.success(null);
     }
}
