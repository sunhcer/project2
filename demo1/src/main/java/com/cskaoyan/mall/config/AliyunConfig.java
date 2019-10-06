package com.cskaoyan.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 20:59
 */
@ConfigurationProperties("aliyun")
@Component
public class AliyunConfig {
    String accessKeyId;
    String accessSecret;
    @Autowired
    SmsConfig smsConfig;

    @Autowired
    OssConfig ossConfig;

    public SmsConfig getSmsConfig() {
        return smsConfig;
    }

    public void setSmsConfig(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public OssConfig getOssConfig() {
        return ossConfig;
    }

    public void setOssConfig(OssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
