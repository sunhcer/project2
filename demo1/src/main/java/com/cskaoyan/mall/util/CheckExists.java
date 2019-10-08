package com.cskaoyan.mall.util;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/4
 * @Time 21:45
 */
public class CheckExists {
    public static boolean check1InArray(Integer[] roleIds){
        boolean flag = false;
        for (Integer roleId : roleIds) {
            if (roleId == 1){
                flag = true;
            }
        }
        return flag;
    }
}
