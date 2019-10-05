package com.cskaoyan.mall.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 17:04
 */
public class TransferCodeToText {
    static Map<Object,String> statusMap = new HashMap<Object, String>();
    static {
        statusMap.put("101", "未付款");
        statusMap.put("102", "用户取消");
        statusMap.put("103", "系统取消");
        statusMap.put("201", "已付款");
        statusMap.put("202", "申请退款");
        statusMap.put("203", "已退款");
        statusMap.put("301", "已发货");
        statusMap.put("401", "用户收货");
        statusMap.put("402", "系统收货");
    }

    //101 未付款    102 用户取消    103  系统取消
    //201  已付款   202  申请退款   203  已退款
    //301  已发货   401  用户收货   402  系统收货
    public static String transferStatusCodeToString(String code){
        return statusMap.get(code);
    }

}
