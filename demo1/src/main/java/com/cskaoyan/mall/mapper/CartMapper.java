package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> selectCartAll(int userId);

    List<Cart> selectCartAllChecked(int userId);

    void updateCheckedByProductId(@Param("productId") Integer productId,@Param("checked") int isChecked);

    void deleteByProductId(Integer productId);

    List<Cart> selectUserAllCheckedCart(@Param("userId") int userId);

    int selectLastAddCartId();

    void deleteUserAllCheckedCart(int userId);

}
