package com.cskaoyan.mall.service.wx;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.vo.CartListInfo;
import com.cskaoyan.mall.vo.CheckedStatus;

public interface WxCartService {
    void addCart(Cart cart);

    CartListInfo selectCartList();

    void checkCart(CheckedStatus checkedStatus);

    void updateCart(Cart cart);

    void deleteCart(CheckedStatus checkedStatus);
}
