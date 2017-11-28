package com.minstone.mobile.mp.wechat.reply.service;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyKeyword;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 消息回复对外基础 API
 * Created by huangyg on 2017/9/21.
 */
public interface IWxReplyService {

//    0. 初始化数据

//    ===== 关注时回复 =====
//    1-1. 获取关注时回复
//    1-2. 开启、关闭关注时回复开关
//    1-3. 添加、修改关注时回复内容

//    ===== 非关键词回复 =====
//    2-1. 获取非关键词回复
//    2-2. 开启、关闭非关键词回复开关
//    2-3. 添加、修改非关键词回复内容

//    ===== 关键词回复 =====
//    3-0. 获取关键词规则
//    3-1. 获取关键词规则列表（分页）
//    3-1-1. 获取单个关键词规则
//    3-1-2. 获取关键词规则下的关键词列表（分页）
//    3-1-3. 根据公众号获取所有关键词（分页）
//    3-1-4. 查看关键词回复是否开启

//    3-2. 开启、关闭所有关键词回复开关

//    3-3. 添加关键词规则（添加跟修改在接口看下能不能合并）
//    3-3-1. 添加关键词
//    3-3-2. 批量添加关键词


//    3-4. 删除关键词规则（物理逻辑）
//    3-4-1. 批量删除关键词规则（物理逻辑）
//    3-4-2. 删除关键词（物理逻辑）
//    3-4-3. 批量删除关键词（物理逻辑）

//    3-6. 修改关键词规则
//    3-6-1. 修改关键词
//    3-6-2. 批量修改关键词

//    3-7. 开启、关闭一个关键词回复开关

    // TODO: 2017/11/8 mapper 还没完善
//    4-1. 根据公众号、关键词获取回复消息

    // TODO: 2017/11/8
//    4-2.根据 touserid 获取回复内容

    /**
     * 添加公众号的时候，初始化【消息回复】数据
     * @author huangyg
     */
    public void initData() throws WxErrorException;

    /************ 封装的方法 ************/

    /**
     *  1.根据公众号主键和回复类型获取回复内容
     * @param  reply 公众号实体
     * publicCode 公众号主键
     * replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @author huangyg
     */
    public List<WxReply> getInfo(WxReply reply) throws WxErrorException ;

    /**
     *  2.添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     * @param  reply 公众号实体
     * publicCode 公众号主键
     * content   回复的内容
     * replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @author huangyg
     */
    public WxReply addContent(WxReply reply) throws WxErrorException;

    /**
     *
     * 3.修改开关。
     * @param  reply 公众号实体
     * publicCode 公众号主键
     * replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @author huangyg
     */
    public boolean updateFlag(WxReply reply) throws WxErrorException;


    /************ 关注时回复 ************/

    /**
     * 1-1. 获取关注时回复
     * @param reply 公众号实体
     * publicCode 公众号主键
     * @return
     * @author huangyg
     */
    public WxReply getFollow(WxReply reply) throws WxErrorException ;

    /**
     *
     * 1-2. 开启、关闭关注时回复开关
     * @param reply 公众号实体
     * publicCode 公众号主键
     * useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @author huangyg
     */
    public boolean followFlag(WxReply reply) throws WxErrorException;

    /**
     * 1-3. 添加、修改关注时回复内容
     * @param reply 公众号实体
     * reply 公众号主键
     * content   回复的内容
     * @return
     * @author huangyg
     */
    public WxReply addFollow(WxReply reply) throws WxErrorException;

    /************ 非关键词回复 ************/

    /**
     *  2-1. 获取非关键词回复
     * @param reply 公众号实体
     * publicCode 公众号主键
     * @return
     * @author huangyg
     */
    public WxReply getNormal(WxReply reply) throws WxErrorException ;

    /**
     *
     * 2-2. 开启、关闭非关键词回复开关
     * @param reply 公众号实体
     *
     * publicCode 公众号主键
     * useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @author huangyg
     */
    public boolean normalFlag(WxReply reply) throws WxErrorException;

    /**
     *  2-3. 添加/修改非关键词回复内容
     * @param reply 公众号实体
     *
     * publicCode 公众号主键
     * content   回复的内容
     * @return
     * @author huangyg
     */
    public WxReply addNormal(WxReply reply) throws WxErrorException;

    /************ 关键词规则 ************/

    /**
     * 3-0. 获取关键词规则
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public WxReply getReplyRule(WxReply reply) throws WxErrorException;

    /**
     *  3-1. 获取关键词规则列表（分页）
     *
     * @param
     * @param rule 关键词规则实体
     * currentPage 当前页
     * pageSize 页数
     * @return
     * @author huangyg
     */
    public PageInfo<WxReplyRule> getRulePage(WxReplyRule rule, int currentPage, int pageSize) throws WxErrorException ;

    /**
     * 3-1-1. 获取单个关键词规则
     * @param rule 关键词规则实体
     * ruleCode 关键词规则主键
     * @return
     * @author huangyg
     */
    public WxReplyRule getRule(WxReplyRule rule) throws WxErrorException;

    /**
     * 3-1-2. 获取关键词规则下的关键词列表（分页）
     * @param rule 关键词规则实体
     * ruleCode 关键词规则主键
     * @return
     * @author huangyg
     */
    public List<WxReplyKeyword> getKeywordByRule(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     * 3-1-3. 根据公众号获取所有关键词（分页）
     * @param keyword 关键词实体
     * @return
     * @author huangyg
     */
    public List<WxReplyKeyword> getKeywords(WxReplyRule keyword) throws WxErrorException,CommonException;

    /**
     * 3-1-4. 查看关键词回复是否开启(内部使用)
     * @param publicCode 公众号主键
     * @returnk
     * @author huangyg
     */
    public boolean keywordsUseFlag(String publicCode,Integer replyType) throws WxErrorException,CommonException;

    /**
     * 3-2. 开启、关闭所有关键词规则开关
     *
     * @param reply
     * publicCode 公众号主键
     * useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @author huangyg
     */
    public boolean allRuleFlag(WxReply reply) throws WxErrorException,CommonException;

    /**
     *  3-3. 添加关键词规则
     *
     * @param rule 关键词回复规则
     * @return
     * @author huangyg
     */
    public String addRule(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     * 3-4. 删除关键词规则（逻辑）
     *
     * @param rule 关键词规则实体
     * ruleCode 关键词主键
     * @return
     * @author huangyg
     */
    public boolean deleteRule(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     * 3-4.删除关键词规则（物理)
     *
     * @param rule 关键词规则实体
     * ruleCode 关键词主键
     * @return
     * @author huangyg
     */
    public boolean forceDeleteRule(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     * 3-4-1. 批量删除关键词规则（逻辑）
     * @param rule 关键词规则实体
     * @return
     * @author huangyg
     */
    public boolean deleteRuleBatch(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     *  3-4-1. 批量删除关键词规则（物理）
     * @param rule 关键词规则实体
     * @return
     * @author huangyg
     */
    public boolean forceDeleteRuleBatch(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     *  3-4-2. 删除关键词（逻辑）
     * @param keyword 关键词实体
     * keywordCode 关键词主键
     * @return
     * @author huangyg
     */
    public boolean deleteKeyword(WxReplyKeyword keyword) throws WxErrorException,CommonException;

    /**
     *  3-4-3.删除关键词（物理）
     * @param keyword 关键词实体
     * keywordCode 关键词主键
     * @return
     * @author huangyg
     */
    public boolean forceDeleteKeyword(WxReplyKeyword keyword) throws WxErrorException,CommonException;

    /**
     * 3-6. 修改关键词规则
     *
     * @param replyRule 规则内容
     * @return
     * @author huangyg
     */
    public boolean updateRule(WxReplyRule replyRule) throws WxErrorException,CommonException;

    /**
     *  3-6-2. 批量修改关键词
     * @param rule 关键词实体
     * @return
     * @author huangyg
     */
    public boolean updateKeywordBatch(WxReplyRule rule) throws WxErrorException;

    /**
     *  3-7. 开启、关闭一个关键词规则回复开关
     *
     * @param rule 关键词实体
     * ruleCode 关键词主键
     * @return
     * @author huangyg
     */
    public boolean singleRuleFlag(WxReplyRule rule) throws WxErrorException,CommonException;

    /**
     * 4-1. 根据公众号、关键词获取回复消息
     *
     * @param rule 关键词实体
     * @return com.minstone.mobile.mp.wechat.reply.reply.WxReplyRule
     * @author huangyg
     */
    public WxReplyRule getMatchContent(WxReplyRule rule) throws WxErrorException;

    public List<WxReplyRule> selectTest(WxReplyRule rule) throws WxErrorException;






    /************ 目前没啥用的方法 ************/

    /**
     * 1.根据主键获取回复内容
     *
     * @param reply 消息回复实体
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public WxReply getInfoByReplyCode(WxReply reply) throws WxErrorException;

    /**
     *  2.获取消息回复内容列表（全部）
     *
     * @param reply 消息回复实体
     * publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public List<WxReply> getList(WxReply reply) throws WxErrorException ;



}
