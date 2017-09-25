package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxReplyDao {

    int insert(WxReply record);

    int insertSelective(WxReply record);

    int deleteByPrimaryKey(String replyCode);

    WxReply selectByPrimaryKey(String replyCode);

    List<WxReply> selectByPulCodeAndReplyType(@Param("publicCode") String publicCode , @Param("replyType") Integer replyType);

    int updateContentByKey(@Param("replyCode") String replyCode, @Param("content") String content);

    int updateContent(@Param("publicCode") String publicCode , @Param("content") String content , @Param("replyType") Integer replyType);

    int updateReplyFlagByKey(@Param("replyCode") String replyCode, @Param("replyFlag") Integer replyFlag);

    int updateReplyFlag(@Param("publicCode") String publicCode , @Param("replyFlag") Integer replyFlag , @Param("replyType") Integer replyType);

    int updateByPrimaryKeySelective(WxReply record);

    int updateByPrimaryKey(WxReply record);

}