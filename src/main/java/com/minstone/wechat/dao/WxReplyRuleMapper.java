package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxReplyRule;

public interface WxReplyRuleMapper {
    int deleteByPrimaryKey(String ruleCode);

    int insert(WxReplyRule record);

    int insertSelective(WxReplyRule record);

    WxReplyRule selectByPrimaryKey(String ruleCode);

    int updateByPrimaryKeySelective(WxReplyRule record);

    int updateByPrimaryKey(WxReplyRule record);
}