package com.cskaoyan.mall.util;

/**
 * 类简介：处理简单的String需求
 * 创建时间: 2019-10-07 19:47
 *
 * @author EGGE
 */
public class StringUtils {
    public static String removeEmpty(String words){
        if(words.isEmpty()||words.trim().isEmpty()){
            return null;
        }
        return words;
    }
}
