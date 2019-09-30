package com.cskaoyan.mall.bean;

/**
 * 类简介：用于接收回复留言模块的参数
 * 创建时间: 2019-10-01 0:06
 *
 * @author EGGE
 */
public class CommitReplyRequest {
    Integer commentId;
    String content;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommitReplyRequest{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
