package com.cskaoyan.mall.util;

public class IpUtils {
    public static String appendIp(String s){
         s="http://localhost"+s;
         return  s;
    }
    public static String SplicePreIp(String url){
        String urlWithoutPre=url.substring(url.indexOf("w")-1,url.length());
        return urlWithoutPre;
    }

    public static String getRandmPath(String fileName){
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        StringBuilder sb = new StringBuilder();
        char[] chars = hexString.toCharArray();
        for (char aChar : chars) {
            sb.append("/" + aChar);
        }
        sb.append("/" + fileName);
        return sb.toString();
    }
}
