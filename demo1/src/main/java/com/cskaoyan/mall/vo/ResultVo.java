package com.cskaoyan.mall.vo;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/9/30
 * @Time 10:30
 */
public class ResultVo<T> {
    T data;
    int errno;
    String errmsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
