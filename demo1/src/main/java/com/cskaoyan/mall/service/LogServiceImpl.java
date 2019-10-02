package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.LogListInfo;
import com.cskaoyan.mall.mapper.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public LogListInfo selectAllLog(int page, int limit, String name, String sort, String order) {

        PageHelper.startPage(page,limit);
        PageHelper.orderBy(sort + " " + order);
        List<Log> logs;
        if(name == null) {
            logs =logMapper.selectAllLogs();
        } else {
            name = "%" + name + "%";
            logs = logMapper.selectLogsByName(name);
        }

        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        long total = logPageInfo.getTotal();
        LogListInfo logListInfo = new LogListInfo();

        logListInfo.setItems(logs);
        logListInfo.setTotal(total);

        return logListInfo;
    }
}
