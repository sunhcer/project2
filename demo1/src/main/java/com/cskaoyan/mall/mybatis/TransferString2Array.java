package com.cskaoyan.mall.mybatis;

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
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/2
 * @Time 12:45
 */
@MappedTypes(String[].class)
public class TransferString2Array implements TypeHandler<String[]> {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        String string = null;
        try {
            string = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(i, string);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        return pareseString(s);
    }

    private String[] pareseString(String s) {
        String[] strings = new String[0];
        if (s == null){
            return strings;
        }
        try {
            strings = objectMapper.readValue(s, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return pareseString(string);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return pareseString(string);
    }
}
