package com.cskaoyan.mall.mybatis;

import com.cskaoyan.mall.bean.GoodsSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类简介：
 * 当前方法：
 * 创建时间: 2019-10-02 16:05
 *
 * @author EGGE
 */
//@MappedTypes(GoodsSpecification.class)
public class TransferGoodsSpecificationsHandler implements TypeHandler<GoodsSpecification> {
    ObjectMapper objectMapper=new ObjectMapper();


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, GoodsSpecification goodsSpecification, JdbcType jdbcType) throws SQLException {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(goodsSpecification);
            preparedStatement.setString(i,s);//i（index）代表了数据中的第几个元素。
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodsSpecification getResult(ResultSet resultSet, String parameterName) throws SQLException {
        String value = resultSet.getString(parameterName);//parameterName代表列名

        return parseGoodsSpecifications2String(value);
    }

    private GoodsSpecification parseGoodsSpecifications2String(String value) {
        GoodsSpecification goodsSpecification = null;
        try {
            goodsSpecification = objectMapper.readValue(value, GoodsSpecification.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodsSpecification;
    }

    @Override
    public GoodsSpecification getResult(ResultSet resultSet, int index) throws SQLException {
        String value = resultSet.getString(index);
        return parseGoodsSpecifications2String(value);
    }

    @Override
    public GoodsSpecification getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseGoodsSpecifications2String(value);
    }
}
