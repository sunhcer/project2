package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageListInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 23:44
 */
public interface StorageService {
    Storage insertStorage(MultipartFile file) throws IOException;

    StorageListInfo selectAllStorage(int page, int limit, String key, String name, String sort, String order);

    void storageUpdate(Storage storage);

    void storageDelete(Storage storage);
}
