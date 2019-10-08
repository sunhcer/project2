package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.vo.WxCommentArray;
import com.cskaoyan.mall.vo.WxUserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findCommentsByValueIdAndUserId(@Param("valueId") Integer valueId,@Param("userId")  Integer userId);

    List<Comment> findAllComments();

    int queryWxTopicCommentAmount(@Param("type") int typeId,@Param("valueId") int valueId);

    List<WxUserComment> querywxTopicCommentList(@Param("type") int typeId, @Param("valueId") int valueId, @Param("pageSize") int pagesize, @Param("offsetNum") int offsetNum);

    void insertwxCommentPost(@Param("wxCommentArray") WxCommentArray wxCommentArray,@Param("id") int id);

    List<WxCommentArray> querywxTopicPicComment(@Param("valueId") int valueId,@Param("type") int type);

    List<Comment> findCommentsByGoodsId(Integer goodsId);

}
