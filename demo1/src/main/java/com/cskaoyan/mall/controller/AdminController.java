package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminListInfo;
import com.cskaoyan.mall.bean.OptionListInfo;
import com.cskaoyan.mall.bean.Options;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.LogService;
import com.cskaoyan.mall.service.RoleService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    LogService logService;

    @RequestMapping("/admin/admin/list")
    public BaseRespVo AdminList(int page, int limit, String username, String sort, String order) {
        AdminListInfo adminListInfo = adminService.selectAllAdmin(page,limit,username,sort,order);
        return BaseRespVo.success(adminListInfo);
    }

    @RequestMapping("/admin/role/options")
    public BaseRespVo options() {
        List<Options> options = roleService.selectAllOptions();
        return BaseRespVo.success(options);
    }

    @RequestMapping("/admin/role/list")
    public BaseRespVo roleList(int page, int limit, String name, String sort, String order) {
        OptionListInfo optionListInfo = roleService.selectAllOptionsDesc(page, limit, name, sort, order);
        return BaseRespVo.success(optionListInfo);
    }

    @RequestMapping("/admin/admin/create")
    public BaseRespVo createAdmin(@RequestBody Admin admin){
        Integer id = admin.getId();
        adminService.addAdmin(admin);
        Admin admin1 = adminService.selectAdminById(id);
        return BaseRespVo.success(admin1);
    }

    @RequestMapping("/admin/admin/delete")
    public BaseRespVo deleteAdmin(@RequestBody Admin admin) {
        Integer id = admin.getId();
        adminService.deleteAdminById(id);

        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/admin/update")
    public BaseRespVo updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        Integer id = admin.getId();
        Admin admin1 = adminService.selectAdminById(id);
        return BaseRespVo.success(admin1);
    }

    @RequestMapping("/admin/role/create")
    public BaseRespVo createRole(@RequestBody Role role) {
        roleService.addRole(role);
        String name = role.getName();
        Role role1 = roleService.selectRoleByName(name);
        return BaseRespVo.success(role1);
    }

    @RequestMapping("/admin/role/update")
    public BaseRespVo updateRole(@RequestBody Role role) {
        roleService.updateRole(role);

        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/role/delete")
    public BaseRespVo deleteRole(@RequestBody Role role) {
        roleService.deleteRole(role);

        return BaseRespVo.success(null);
    }

    @RequestMapping("/admin/log/list")
    public BaseRespVo logList(int page, int limit, String name, String sort, String order) {
        LogListInfo logListInfo = logService.selectAllLog(page,limit,name,sort,order);
        return BaseRespVo.success(logListInfo);
    }
}
