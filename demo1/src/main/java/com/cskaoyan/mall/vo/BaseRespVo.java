package com.cskaoyan.mall.vo;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-09-30 10:57
 *
 * @author EGGE
 */
public class BaseRespVo<T> {
    T data;
    String errmsg;
    int errno;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
    public static BaseRespVo success(Object data){
        BaseRespVo<Object> respVo=new BaseRespVo<>();
        respVo.setData(data);
        respVo.setErrmsg("成功");
        respVo.setErrno(0);
        return respVo;

    }
}
