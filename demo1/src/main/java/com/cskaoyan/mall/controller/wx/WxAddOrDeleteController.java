package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.WxAddOrDeletePage;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.wx.WxAddOrDeleteService;
import com.cskaoyan.mall.util.ShiroUtils;
import com.cskaoyan.mall.vo.AddOrDeleteVo;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxAddOrDeleteController {
    @Autowired
    WxAddOrDeleteService wxAddOrDeleteService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("wx/collect/addordelete")
    public BaseRespVo addDelete(@RequestBody WxAddOrDeletePage page) {
        int type = page.getType();
        int valueId = page.getValueId();
        int userId = userMapper.queryUserIdByUsername(ShiroUtils.getCurrentUserName());
        AddOrDeleteVo addOrDeleteVo = wxAddOrDeleteService.addOrDelete(valueId,userId);

        BaseRespVo success = BaseRespVo.success(addOrDeleteVo);
        return success;
    }
}
