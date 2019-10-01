package com.cskaoyan.mall.util;

public class IpUtils {
    public static String appendIp(String s){
         s="http://localhost/"+s;
         return  s;
    }
    public static String SplicePreIp(String url){
        String urlWithoutPre=url.substring(url.indexOf("w")-1,url.length());
        return urlWithoutPre;
    }
}
