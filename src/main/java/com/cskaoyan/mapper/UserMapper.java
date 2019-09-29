package com.cskaoyan.mapper;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select username from user where id = #{id}")
    String queryUserNameById(int id);
}
