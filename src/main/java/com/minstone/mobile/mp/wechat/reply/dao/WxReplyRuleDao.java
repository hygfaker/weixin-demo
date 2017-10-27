package com.minstone.mobile.mp.wechat.reply.dao;

import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxReplyRuleDao {
    /**
     * 插入关键词规则回复
     * @param record 待插入关键词规则回复实体
     * @return
     */
    int insertSelective(WxReplyRule record);
    // todo
     /**
     * 批量插入关键词规则回复
     * (由于关键词规则回复完整内容需要另一张表的关键词信息，所以不做批处理接口，只实现 dao 操作)
     * @param list 待插入关键词规则回复实体数组
     * @return
     */
    int insertBatch(List<WxReplyRule> list);
    /**
     * 物理删除关键词规则回复
     * @param ruleCode 关键词规则回复主键
     * @return
     */
    int forceDeleteByPrimaryKey(String ruleCode);
    /**
     * 批量物理删除关键词规则回复
     * @param list 关键词规则 FD回复主键们
     * @return
     */
    int forceDeleteBatch(String[] list);
    /**
     * 逻辑删除关键词规则回复
     * @param ruleCode 关键词规则回复主键
     * @return
     */
    int deleteByPrimaryKey(String ruleCode);
    /**
     * 批量逻辑删除关键词规则回复
     * @param list 关键词规则回复主键们
     * @return
     */
    int deleteBatch(String[] list);
    /**
     * 更新关键词规则回复
     * @param record 待更新关键词规则回复主体
     * @return
     */
    int updateByPrimaryKeySelective(WxReplyRule record);
    // todo（mapper 里面不知道怎么实现，先放着吧 = =）
    /**
     * 批量更新关键词规则回复
     * @param list 待更新关键词规则回复主体们
     * @return
     */
    int updateByPrimaryKeyBatch(List<WxReplyRule> list);
    /**
     * 开启、关闭关键词规则回复
     * @param ruleCode 关键词规则回复主键
     * @param useFlag 开关标志，0表示关，1表示开
     * @return
     */
    int updateRuleFlag(@Param("ruleCode") String ruleCode,@Param("useFlag") int useFlag);
    /**
     * 查找公众号下的关键词规则列表
     * @param publicCode 公众号主键
     * @return
     */
    List<WxReplyRule> selectAll(String publicCode);
    /**
     * 根据主键查找关键词规则列表
     * @param ruleCode 关键词规则主键
     * @return
     */
    List<WxReplyRule> selectByPrimaryKey(String ruleCode);
    /**
     * 查找公众号下的关键词规则列表（测试）
     * @param publicCode 公众号主键
     * @return
     */
    List<WxReplyRule> selectTest(String publicCode);
}