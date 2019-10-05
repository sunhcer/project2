package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminListInfo;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.vo.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${myfile.img-prefix}")
    String prefix;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    AdminService adminService;

    @Autowired
    PermissionService permissionService;

    @Override
    public AdminListInfo selectAllAdmin(int page,int limit,String username, String sort,String order) {
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Admin> admins;
        if(username == null) {
            admins = adminMapper.selectAllAdmin();
        } else {
            username = "%" + username + "%";
            admins = adminMapper.selectAdminByNameLike(username);
        }

        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        AdminListInfo adminListInfo = new AdminListInfo();

        for (Admin admin : admins) {
            admin.setAvatar(prefix + admin.getAvatar());
        }
        adminListInfo.setItems(admins);
        adminListInfo.setTotal(total);

        return adminListInfo;
    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        String avatar = admin.getAvatar();
        avatar = avatar.replace(prefix,"");
        admin.setAvatar(avatar);
        adminMapper.insertSelective(admin);
    }

    @Override
    public Admin selectAdminById(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public List<String> selectPermissionsByName(String principal) {
        Admin admin = adminMapper.selectAdminByName(principal);
        Integer[] roleIds = admin.getRoleIds();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            List<String> permissions = permissionMapper.selectPermissionById(roleIds[i]);
            strings.addAll(permissions);
        }
        return strings;
    }

    @Override
    public List<String> selectRolesByName(String principal) {
        Admin admin = adminMapper.selectAdminByName(principal);
        Integer[] roleIds = admin.getRoleIds();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            String roleName = roleMapper.selectRoleNameById(roleIds[i]);
            strings.add(roleName);
        }
        return strings;
    }

    @Override
    public Admin selectAdminByName(String principal) {
        Admin admin = adminMapper.selectAdminByName(principal);
        return admin;
    }

    @Override
    public UserInfo getRoleMessage(String principal) {
        Admin admin = adminService.selectAdminByName(principal);
        String avatar = admin.getAvatar();
        String username = admin.getUsername();
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(prefix + avatar);
        userInfo.setName(username);

        List<String> roles = adminService.selectRolesByName(principal);
        userInfo.setRoles(roles);

        Integer[] roleIds = admin.getRoleIds();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            if(roleIds[i] == 1) {
                List<String> allPermissions = permissionService.selectAllPermissions();
                for (String allPermission : allPermissions) {
                    String api = permissionMapper.selectApiByPermission(allPermission);
                    strings.add(api);
                    userInfo.setPerms(strings);
                }
                return userInfo;
            }
            List<String> perms = permissionService.selectAllPermissionsById(roleIds[i]);
            for (String perm : perms) {
                String s = permissionMapper.selectApiByPermission(perm);
                strings.add(s);
            }
        }
        userInfo.setPerms(strings);

        return userInfo;
    }


//
//    @Override
//    public void insertAdmin(AdminInfo adminInfo) {
//         adminMapper.insertAdmin(adminInfo);
//    }
}
