package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.bean.AdminInfo;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("admin/admin/create")
    public BaseRespVo create(@RequestBody AdminInfo adminInfo){
        //System.out.println(111);
        adminService.insertAdmin(adminInfo);
        BaseRespVo respVo=BaseRespVo.success(adminInfo);
        return respVo;
    }



}
