package com.cskaoyan.mall.mybatis;

import com.cskaoyan.mall.bean.GoodsAttribute;
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
//@MappedTypes(GoodsAttribute.class)
public class TransferGoodsAttributesHandler implements TypeHandler<GoodsAttribute> {
    ObjectMapper objectMapper=new ObjectMapper();


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, GoodsAttribute goodsAttribute, JdbcType jdbcType) throws SQLException {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(goodsAttribute);
            preparedStatement.setString(i,s);//i（index）代表了数据中的第几个元素。
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodsAttribute getResult(ResultSet resultSet, String parameterName) throws SQLException {
        String value = resultSet.getString(parameterName);//parameterName代表列名

        return parseGoodsAttribute2String(value);
    }

    private GoodsAttribute parseGoodsAttribute2String(String value) {
        GoodsAttribute goodsAttribute = null;
        try {
            goodsAttribute = objectMapper.readValue(value, GoodsAttribute.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodsAttribute;
    }

    @Override
    public GoodsAttribute getResult(ResultSet resultSet, int index) throws SQLException {
        String value = resultSet.getString(index);
        return parseGoodsAttribute2String(value);
    }

    @Override
    public GoodsAttribute getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseGoodsAttribute2String(value);
    }
}
