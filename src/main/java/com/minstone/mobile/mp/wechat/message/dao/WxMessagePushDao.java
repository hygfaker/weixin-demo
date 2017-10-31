package com.minstone.mobile.mp.wechat.message.dao;

import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WxMessagePushDao {

    int deleteByPrimaryKey(String pushCode);

    int insert(WxMessagePush record);

    int insertSelective(WxMessagePush record);

    WxMessagePush selectByPrimaryKey(String pushCode);

    int updateByPrimaryKeySelective(WxMessagePush record);

    int updateByPrimaryKey(WxMessagePush record);
}