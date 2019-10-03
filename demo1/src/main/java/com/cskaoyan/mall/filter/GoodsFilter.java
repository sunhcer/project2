package com.cskaoyan.mall.filter;

import com.fasterxml.classmate.Filter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-02 20:00
 *
 * @author EGGE
 */
@WebFilter("GoodsFilter")
@Component
public class GoodsFilter implements Filter {
    @Override
    public boolean include(Object o) {
        return false;
    }
}
