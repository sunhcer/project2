package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Stat;
import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/admin/stat/")
public class StatController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("user")
    public BaseRespVo statUser() {

        Stat stat = new Stat();
        String columns[] = {"day","users"};
        stat.setColumns(columns);
        StatUser statUser = new StatUser();
        //user模块提供给我user人数
        statUser.setUsers(20);
        //user模块提供给我user创建时间
        String format = simpleDateFormat.format(new Date());
        statUser.setDay(format);
        //user模块把所有数据封装成statUser数组
        StatUser rows[] = {statUser};
        stat.setRows(rows);

        return BaseRespVo.success(stat);
    }

    @RequestMapping("order")
    public BaseRespVo statOrder() {

        Stat stat = new Stat();
        //"columns":["day","orders","customers","amount","pcr"]
        String columns[] = {"day","orders","customers","amount","pcr"};
        stat.setColumns(columns);

        StatOrder statOrder = new StatOrder();
        statOrder.setAmount(8462.00);
        statOrder.setCustomers(12);
        String format = simpleDateFormat.format(new Date());
        statOrder.setDay(format);
        statOrder.setOrders(21);
        statOrder.setPcr(5469.00);

        StatOrder rows[] = {statOrder};
        stat.setRows(rows);

        return BaseRespVo.success(stat);
    }

    @RequestMapping("goods")
    public BaseRespVo statGood() {

        Stat stat = new Stat();
        //"columns":["day","orders","products","amount"]
        String columns[] = {"day","orders","products","amount"};
        stat.setColumns(columns);

        StatGood statGood = new StatGood();
        statGood.setAmount(1234.00);
        String format = simpleDateFormat.format(new Date());
        statGood.setDay(format);
        statGood.setOrders(13);
        statGood.setProducts(20);

        StatGood rows[] = {statGood};
        stat.setRows(rows);

        return BaseRespVo.success(stat);
    }
}
