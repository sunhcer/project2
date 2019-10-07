package com.cskaoyan.mall.util;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/7
 * @Time 20:50
 */
public class TransferBig2Double {
    public static double big2Double(BigDecimal bigDecimal){
        if (bigDecimal != null){
            return bigDecimal.doubleValue();
        }
        return 0;
    }

    public static BigDecimal double2Big(Double num){
        if (num != null) {
            return BigDecimal.valueOf(num);
        }
        return BigDecimal.valueOf(0);
    }

    public static BigDecimal double2Int(Integer num){
        if (num != null) {
            return BigDecimal.valueOf(num);
        }
        return BigDecimal.valueOf(0);
    }
}
