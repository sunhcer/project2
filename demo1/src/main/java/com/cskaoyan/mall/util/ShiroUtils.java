package com.cskaoyan.mall.util;

import org.apache.shiro.SecurityUtils;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 18:55
 */
public class ShiroUtils {
    //获取当前用户姓名
    public static String getCurrentUserName(){
        Object principal =  SecurityUtils.getSubject().getPrincipal();
        if (principal != null){
            return principal.toString();
        }
        return null;
    }


}
