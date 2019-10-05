package com.cskaoyan.mall.controller.admin;


import com.cskaoyan.mall.bean.UsersListInfo;
import com.cskaoyan.mall.service.admin.UserService;
import com.cskaoyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //1显示所有用户
    @RequestMapping("admin/user/list")
    public BaseRespVo getAdminUserList(UserPage userPage){
        UsersListInfo usersListInfo= userService.selectAllUsers(userPage);
        BaseRespVo<UsersListInfo> usersListInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = usersListInfoBaseRespVo.success(usersListInfo);
        return success;
    }

    @RequestMapping("admin/address/list")
    public BaseRespVo getAdminAddressList(UserPage userPage){
        UsersAddressInfo usersAddressInfo = userService.selectUsersAddress(userPage);// name   userId
        BaseRespVo<UsersAddressInfo> usersAddressInfoBaseRespVo=new BaseRespVo<>();
        BaseRespVo success = usersAddressInfoBaseRespVo.success(usersAddressInfo);
        return success;
    }

    @RequestMapping("admin/collect/list")
    public BaseRespVo getAdminCollectList(UserPage userPage){
        UsersCollectInfo usersCollectInfo = userService.selectUsersCollect(userPage);
        BaseRespVo<UsersCollectInfo> usersCollectInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = usersCollectInfoBaseRespVo.success(usersCollectInfo);
        return success;
    }

    @RequestMapping("admin/footprint/list")
    public BaseRespVo getAdminFootList(UserPage userPage){
        UsersFootInfo usersFootInfo = userService.selectUsersFoot(userPage);
        BaseRespVo<UsersFootInfo> usersFootInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = usersFootInfoBaseRespVo.success(usersFootInfo);
        return success;
    }

    @RequestMapping("admin/history/list")
    public BaseRespVo getAdminHistoryList(UserPage userPage){
        UserHistoryInfo userHistoryInfo = userService.selectUsersHistory(userPage);
        BaseRespVo<UserHistoryInfo> userHistoryInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = userHistoryInfoBaseRespVo.success(userHistoryInfo);
        return success;
    }

    @RequestMapping("admin/feedback/list")
    public BaseRespVo getAdminFeedbackList(UserPage userPage){
        UserFeedbackInfo userFeedbackInfo = userService.selectUsersFeedback(userPage);
        BaseRespVo<UserFeedbackInfo> userFeedbackInfoBaseRespVo = new BaseRespVo<>();
        BaseRespVo success = userFeedbackInfoBaseRespVo.success(userFeedbackInfo);
        return success;
    }

}
