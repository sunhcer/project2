package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.OrderPage;
import com.cskaoyan.mall.vo.WxGrouponRuleData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> selectGrouponRulesByCondition(OrderPage orderPage);

    List<GrouponRules> selectByAddTimeWithLimit(@Param("limit") Integer limit, @Param("offset") Integer offset);

    int queryGrouponRulesAmount();

    List<WxGrouponRuleData> queryGrouponRulesDataList(@Param("pagesize") int size,@Param("offsetNum") int offsetNum);

    List<GrouponRules> findGrouponRuleListByGoodsId(int goodsId);

}
