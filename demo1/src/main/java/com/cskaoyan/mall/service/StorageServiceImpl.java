package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/1
 * @Time 23:44
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;
    @Value("${myfile.img-path}")
    String storePath;
    @Value("${myfile.img-prefix}")
    String myprefix;

    @Override
    public Storage insertStorage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        //获取路径
        String randmPath = IpUtils.getRandmPath(fileName);
        File file1 = new File(storePath, randmPath);
        if (!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        file.transferTo(file1);
        Storage storage = new Storage();
        storage.setKey(randmPath);
        storage.setUrl(myprefix + randmPath);
        storage.setName(file.getOriginalFilename());
        storage.setSize((int) file.getSize());
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setType(file.getContentType());
        storage.setId(null);
        storageMapper.insertSelective(storage);
        return storage;
    }
}
