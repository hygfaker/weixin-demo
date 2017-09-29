package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxReplyKeyword;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxReplyKeywordDao {

    int FDeleteByRuleCode(String ruleCode);

    int FDeleteByPrimaryKey(String keywordCode);

    int deleteByRuleCode(String ruleCode);

    int deleteByPrimaryKey(String keywordCode);

    int insert(WxReplyKeyword record);

    int insertSelective(WxReplyKeyword record);

    int insertPatch(List<WxReplyKeyword> keywords);

    List<WxReplyKeyword> selectByPrimaryKey(String keywordCode);

    List<WxReplyKeyword> selectByRuleCode(String publicCode);

    int updatePatch(List<WxReplyKeyword> keywords);

    int updateByPrimaryKeySelective(WxReplyKeyword record);

    int updateByPrimaryKey(WxReplyKeyword record);
}