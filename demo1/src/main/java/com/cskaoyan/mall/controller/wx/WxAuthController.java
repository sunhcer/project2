package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.WxUserInfo;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.WxLoginBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:07
 */
@RestController
public class WxAuthController {
    ///wx/auth/login
    @RequestMapping("/wx/auth/login")
    public BaseRespVo login(){
        WxUserInfo wxUserInfo = new WxUserInfo();
        wxUserInfo.setNickName("wx");
        wxUserInfo.setAvatarUrl("");
        WxLoginBean wxLoginBean = new WxLoginBean();
        wxLoginBean.setUserInfo(wxUserInfo);
        wxLoginBean.setTokenExpire("2019-10-06T03:09:25.020");
        wxLoginBean.setToken("1j1mz4pef0lurpawsbjj6pc6cmdcge79");
        return BaseRespVo.success(wxLoginBean);
    }

}
