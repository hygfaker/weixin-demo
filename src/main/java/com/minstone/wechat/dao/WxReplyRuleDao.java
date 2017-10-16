package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxReplyRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxReplyRuleDao {

    // 物理删除
    int FDeleteByPrimaryKey(String ruleCode);
    // 逻辑删除
    int deleteByPrimaryKey(String ruleCode);

    int insert(WxReplyRule record);

    int insertSelective(WxReplyRule record);

    // 查找
    WxReplyRule selectByPrimaryKey(String ruleCode);

    List<WxReplyRule> selectAll(String publicCode);

    int updateByPrimaryKeySelective(WxReplyRule record);

    int updateByPrimaryKey(WxReplyRule record);

    // 开启、关闭规则
    int updateRuleFlag(@Param("ruleCode") String ruleCode,@Param("useFlag") int useFlag);

}