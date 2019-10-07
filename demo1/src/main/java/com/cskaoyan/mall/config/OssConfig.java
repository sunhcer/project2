package com.cskaoyan.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 21:00
 */
@ConfigurationProperties("aliyun-oss")
@Component
public class OssConfig {
    String endpoint;
    String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
