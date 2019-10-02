package com.cskaoyan.mall.bean;
//运费配置 bean
public class ExpressConfigBean {
    String cskaoyan_mall_express_freight_min;
    String cskaoyan_mall_express_freight_value;
    public ExpressConfigBean(){

    }

    public ExpressConfigBean(String cskaoyan_mall_express_freight_min, String cskaoyan_mall_express_freight_value) {
        this.cskaoyan_mall_express_freight_min = cskaoyan_mall_express_freight_min;
        this.cskaoyan_mall_express_freight_value = cskaoyan_mall_express_freight_value;
    }

    public String getCskaoyan_mall_express_freight_min() {
        return cskaoyan_mall_express_freight_min;
    }

    public void setCskaoyan_mall_express_freight_min(String cskaoyan_mall_express_freight_min) {
        this.cskaoyan_mall_express_freight_min = cskaoyan_mall_express_freight_min;
    }

    public String getCskaoyan_mall_express_freight_value() {
        return cskaoyan_mall_express_freight_value;
    }

    public void setCskaoyan_mall_express_freight_value(String cskaoyan_mall_express_freight_value) {
        this.cskaoyan_mall_express_freight_value = cskaoyan_mall_express_freight_value;
    }
}
