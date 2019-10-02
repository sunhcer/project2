package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 23:44
 */
public interface StorageService {
    Storage insertStorage(MultipartFile file) throws IOException;
}
