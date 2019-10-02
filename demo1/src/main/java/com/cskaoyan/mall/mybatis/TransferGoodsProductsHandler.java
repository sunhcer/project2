package com.cskaoyan.mall.mybatis;

import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-02 16:04
 *
 * @author EGGE
 */
//@MappedTypes(GoodsProduct.class)
public class TransferGoodsProductsHandler implements TypeHandler<GoodsProduct> {
    ObjectMapper objectMapper=new ObjectMapper();


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, GoodsProduct goodsProduct, JdbcType jdbcType) throws SQLException {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(goodsProduct);
            preparedStatement.setString(i,s);//i（index）代表了数据中的第几个元素。
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodsProduct getResult(ResultSet resultSet, String parameterName) throws SQLException {
        String value = resultSet.getString(parameterName);//parameterName代表列名

        return parseGoodsProduct2String(value);
    }

    private GoodsProduct parseGoodsProduct2String(String value) {
        GoodsProduct goodsProduct = null;
        try {
            goodsProduct = objectMapper.readValue(value, GoodsProduct.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodsProduct;
    }

    @Override
    public GoodsProduct getResult(ResultSet resultSet, int index) throws SQLException {
        String value = resultSet.getString(index);
        return parseGoodsProduct2String(value);
    }

    @Override
    public GoodsProduct getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseGoodsProduct2String(value);
    }
}
