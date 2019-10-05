package com.cskaoyan.mall.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/5
 * @Time 15:57
 */
public class JacksonUtil {
    static ObjectMapper objectMapper = new ObjectMapper();
    public static String parseString(String body, String keyword) {
        Map map = null;
        try {
            map = objectMapper.readValue(body, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = map.get(keyword);
        if (obj != null){
            return obj.toString();
        }
        return null;
    }
}
