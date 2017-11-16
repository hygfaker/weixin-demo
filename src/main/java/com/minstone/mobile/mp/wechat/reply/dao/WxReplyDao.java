package com.minstone.mobile.mp.wechat.reply.dao;

import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxReplyDao {

    int insert(WxReply record);

    int insertSelective(WxReply record);

    int deleteByPrimaryKey(WxReply record);


//    String selectPublicCode(@Param("publicCode") String publicCode,@Param("replyType") Integer replyType);

    WxReply selectByPrimaryKey(WxReply record);

    List<WxReply> selectByPublicCodeAndReplyType(WxReply record);

    List<WxReply> selectByPulCode(WxReply record);

    int updateContentByKey(WxReply record);

    int updateContent(WxReply record);

    int updateReplyFlagByKey(WxReply record);

    int updateReplyFlag(WxReply record);

    int updateByPrimaryKeySelective(WxReply record);

    int updateByPrimaryKey(WxReply record);

}