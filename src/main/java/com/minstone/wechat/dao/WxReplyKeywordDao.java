package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxReplyKeyword;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxReplyKeywordDao {

    /**
     * 插入关键词信息
     * @param record 关键词信息实体
     * @return
     */
    int insertSelective(WxReplyKeyword record);

    /**
     * 批量插入关键词信息
     * @param keywords 关键词信息实体列表
     * @return
     */
    int insertBatch(List<WxReplyKeyword> keywords);

    /**
     * 物理删除关键词规则下的所有关键词信息
     * @param ruleCode 关键词规则主键
     * @return
     */
    int fDeleteByRuleCode(String ruleCode);

    /**
     * 物理删除关键词信息
     * @param keywordCode 关键词主键
     * @return
     */
    int fDeleteByPrimaryKey(String keywordCode);

    /**
     *  批量物理删除关键词信息
     * @param array 关键词主键们
     * @return
     */
    int fDeleteByPrimaryKeyBatch(String[] array);

    /**
     * 逻辑删除关键词回复下的所有关键词信息
     * @param ruleCode 关键词规则主键
     * @return
     */
    int deleteByRuleCode(String ruleCode);

    /**
     * 逻辑删除关键词信息
     * @param keywordCode 关键词主键
     * @return
     */
    int deleteByPrimaryKey(String keywordCode);

//    todo
    int fDeleteByRuleCodeBatch(String[] array);
    /**
     * 逻辑删除关键词信息
     * @param array 关键词主键们
     * @return
     */
    int deleteByPrimaryKeyBatch(String[] array);

    /**
     * 更新关键词信息
     * @param record 关键词信息实体
     * @return
     */
    int updateByPrimaryKeySelective(WxReplyKeyword record);

    /**
     * 批量更新关键词信息
     * @param list 关键词信息实体列表
     * @return
     */
    int updateBatch(List<WxReplyKeyword> list);

    /**
     * 获取关键词信息
     * @param keywordCode 关键词主键
     * @return
     */
    List<WxReplyKeyword> selectByPrimaryKey(String keywordCode);

    /**
     * 获取关键词规则下的关键词信息
     * @param ruleCode 公众号主键
     * @return
     */
    List<WxReplyKeyword> selectByRuleCode(String ruleCode);

}