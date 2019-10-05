package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxCartController {

    @RequestMapping("/wx/cart/index")
    public BaseRespVo cartIndex() {

        return BaseRespVo.success(null);
    }
}
