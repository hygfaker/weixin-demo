package com.minstone.mp.wechat.dao;

import com.minstone.mp.wechat.domain.WxReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxReplyDao {

    int insert(WxReply record);

    int insertSelective(WxReply record);

    int deleteByPrimaryKey(String replyCode);


//    String selectPublicCode(@Param("publicCode") String publicCode,@Param("replyType") Integer replyType);

    WxReply selectByPrimaryKey(String replyCode);

    List<WxReply> selectByPulCodeAndReplyType(@Param("publicCode") String publicCode , @Param("replyType") Integer replyType);

    List<WxReply> selectByPulCode(@Param("publicCode") String publicCode);

    int updateContentByKey(@Param("replyCode") String replyCode, @Param("content") String content);

    int updateContent(@Param("publicCode") String publicCode , @Param("content") String content , @Param("replyType") Integer replyType);

    int updateReplyFlagByKey(@Param("replyCode") String replyCode, @Param("replyFlag") Integer replyFlag);

    int updateReplyFlag(@Param("publicCode") String publicCode, @Param("replyType") Integer replyType, @Param("replyFlag") Integer replyFlag );

    int updateByPrimaryKeySelective(WxReply record);

    int updateByPrimaryKey(WxReply record);

}