package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageListInfo;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.util.IpUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
        storage.setUrl(myprefix + storage.getKey());
        storage.setName(file.getOriginalFilename());
        storage.setSize((int) file.getSize());
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setType(file.getContentType());
        storage.setId(null);
        storageMapper.insertSelective(storage);
        return storage;
    }

    @Override
    public StorageListInfo selectAllStorage(int page, int limit, String key, String name, String sort, String order) {
        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Storage> storages;

        if(name != null) {
            name = "%" + name + "%";
        }
        if(key != null) {
            key = "%" + key + "%";
        }

        storages = storageMapper.selectAllStorages(key, name);

        for (Storage storage : storages) {
            storage.setUrl(myprefix + storage.getKey());
        }
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        long total = storagePageInfo.getTotal();
        StorageListInfo storageListInfo = new StorageListInfo();
        storageListInfo.setItems(storages);
        storageListInfo.setTotal(total);

        return storageListInfo;
    }

    @Override
    public void storageUpdate(Storage storage) {
        storageMapper.updateByPrimaryKeySelective(storage);
    }

    @Override
    public void storageDelete(Storage storage) {
        Integer id = storage.getId();
        storageMapper.storageDelete(id);
    }
}
