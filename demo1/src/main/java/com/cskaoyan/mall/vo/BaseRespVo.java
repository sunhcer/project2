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

    /**
     * 返回给前端数据 成功时
     * @param data 封装的数据
     * @return 返回给前端的数据
     */
    public static BaseRespVo success(Object data){
        BaseRespVo<Object> respVo=new BaseRespVo<>();

        respVo.setData(data);
        respVo.setErrmsg("成功");
        respVo.setErrno(0);
        return respVo;
    }


    public static BaseRespVo fail(int errno, String errmsg) {
        BaseRespVo<Object> respVo = new BaseRespVo<>();

        respVo.setErrno(errno);
        respVo.setErrmsg(errmsg);
        return respVo;
    }
    /**
     * 返回给前端数据 失败时
     * @param data 封装的数据
     * @param errno 错误代码
     * @param errmsg 错误信息
     * @return 返回给前端的数据
     */
    public static BaseRespVo error(Object data,int errno,String errmsg){
        BaseRespVo<Object> respVo=new BaseRespVo<>();

        respVo.setData(data);
        respVo.setErrmsg(errmsg);
        respVo.setErrno(errno);

        return respVo;
    }

}
