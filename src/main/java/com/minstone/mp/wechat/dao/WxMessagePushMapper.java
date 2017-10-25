package com.minstone.mp.wechat.dao;

import com.minstone.mp.wechat.domain.WxMessagePush;

public interface WxMessagePushMapper {

    int deleteByPrimaryKey(String pushCode);

    int insert(WxMessagePush record);

    int insertSelective(WxMessagePush record);

    WxMessagePush selectByPrimaryKey(String pushCode);

    int updateByPrimaryKeySelective(WxMessagePush record);

    int updateByPrimaryKey(WxMessagePush record);
}