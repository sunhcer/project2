package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.vo.AdminInfo;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 添加管理员
 */
@RestController
public class StorageController {
    @RequestMapping("admin/storage/create")
    public BaseRespVo create(){
        AdminInfo adminInfo = new AdminInfo();
       //adminInfo.setAddTime("2019-09-30 01:08:30");
       //adminInfo.setId(708);
       //adminInfo.setKey("m4qp1cn25hdxwoqvptnq.jpg");
       //adminInfo.setName( "37090301_9.jpg");
       //adminInfo.setSize(112004);
       //adminInfo.setType("image/jpeg");
       //adminInfo.setUpdateTime("2019-09-30 01:08:30");
       //adminInfo.setUrl( "http://192.168.2.100:8081/wx/storage/fetch/m4qp1cn25hdxwoqvptnq.jpg");
        BaseRespVo respVo=BaseRespVo.success(adminInfo);
        return respVo;

    }
}
