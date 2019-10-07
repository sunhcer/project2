package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.TopicArray;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    int queryTopicAmount();

    List<TopicArray> queryAllTopic(@Param("pagesize") int pagesize,@Param("offsetNum") int offsetNum);

    int queryLikeTopicAmount(@Param("title") String title,@Param("subtitle") String subtitle);

    List<TopicArray> queryLikeTopicPage(@Param("title") String title,@Param("subtitle") String subtitle,@Param("pagesize") int pagesize,@Param("offsetNum") int offsetNum);

    int AddTopic(@Param("topicArray") TopicArray topicArray);

    void topicUpdate(@Param("topicArray") TopicArray topicArray);

    void topicDelete(@Param("id") Integer id);

    List<TopicArray> findTopicLastAdd(Integer i);

    List<Topic> queryWxAllTopic();

    TopicArray queryTopicArrayById(@Param("id") int id);

    List<TopicArray> queryWxRelatedTopic(@Param("min")int min,@Param("max")int max);
}
