package com.minstone.wechat.api;
import com.github.pagehelper.PageInfo;
import com.minstone.wechat.common.CommonException;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 消息回复对外基础 API
 * Created by huangyg on 2017/9/21.
 */
@Service
public interface WxReplyService {

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
//    3-1. 获取关键词规则列表（分页）
//    3-1-1. 获取单个关键词规则
//    3-1-2. 获取关键词规则下的关键词列表（分页）

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

    /**
     * 添加公众号的时候，初始化【消息回复】数据
     * @throws WxErrorException
     */
    public void initData() throws WxErrorException;


    /************ 封装的方法 ************/

    /**
     *  1.根据公众号主键和回复类型获取回复内容
     *
     * @param publicCode 公众号主键
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getInfo(String publicCode, Integer replyType) throws WxErrorException ;

    /**
     *  2.添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public String addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException;

    /**
     *
     * 3.修改开关。
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public boolean updateReplyFlag(String publicCode, Integer useFlag, Integer replyType) throws WxErrorException;


    /************ 关注时回复 ************/

    /**
     * 1-1. 获取关注时回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getFollowInfo(String publicCode) throws WxErrorException ;
    /**
     *
     * 1-2. 开启、关闭关注时回复开关
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public boolean followReplyFlag(String publicCode, Integer useFlag) throws WxErrorException;
    /**
     *   1-3. 添加、修改关注时回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public String addFollowReply(String publicCode, String content) throws WxErrorException;

    /************ 非关键词回复 ************/

    /**
     *  2-1. 获取非关键词回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getNormalInfo(String publicCode) throws WxErrorException ;
    /**
     *
     * 2-2. 开启、关闭非关键词回复开关
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public boolean normalReplyFlag(String publicCode, Integer useFlag) throws WxErrorException;
    /**
     *  2-3. 添加/修改非关键词回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public String addNormalReply(String publicCode, String content) throws WxErrorException;

    /************ 关键词规则 ************/

    /**
     *  3-1. 获取关键词规则列表（分页）
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public PageInfo<WxReplyRule> getKeywordPage(String publicCode, int currentPage, int pageSize) throws WxErrorException ;

    /**
     * 3-1-1. 获取单个关键词规则
     * @param ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     */
    public WxReplyRule getReplyRule(String ruleCode) throws WxErrorException;

    /**
     * 3-1-2. 获取关键词规则下的关键词
     * @param ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    public List<WxReplyKeyword> selectByRuleCode(String ruleCode) throws WxErrorException,CommonException;

    /**
     *
     * 3-2. 开启、关闭所有关键词回复开关
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public boolean replyRuleFlag(String publicCode, Integer useFlag) throws WxErrorException,CommonException;

    /**
     *  3-3. 添加关键词规则
     *
     * @param wxReplyRule 关键词回复规则
     * @return
     * @throws WxErrorException
     */
    public String addReplyRule(WxReplyRule wxReplyRule) throws WxErrorException,CommonException;

    /**
     * 3-4. 删除关键词规则（逻辑）
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public boolean deleteRule(String ruleCode) throws WxErrorException,CommonException;

    /**
     * 3-4.删除关键词规则（物理)
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public boolean fDeleteRule(String ruleCode) throws WxErrorException,CommonException;

    /**
     * 3-4-1. 批量删除关键词规则（逻辑）
     * @param ruleCodes 关键词规则主键们
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    public boolean deleteRuleBatch(String[] ruleCodes) throws WxErrorException,CommonException;

    /**
     *  3-4-1. 批量删除关键词规则（物理）
     * @param ruleCodes 关键词规则主键们
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    public boolean fDeleteRuleBatch(String[] ruleCodes) throws WxErrorException,CommonException;

    /**
     *  3-4-2. 删除关键词（逻辑）
     * @param keywordCode 关键词主键
     * @return
     * @throws CommonException
     */
    public boolean deleteKeyword(String keywordCode) throws WxErrorException,CommonException;

    /**
     * 3-6. 修改关键词规则
     *
     * @param replyRule 规则内容
     * @return
     * @throws WxErrorException
     */
    public boolean updateRule(WxReplyRule replyRule) throws WxErrorException,CommonException;

    /**
     *  3-6-2. 批量修改关键词
     * @param lists 关键词列表
     * @return
     * @throws WxErrorException
     */
    public boolean updateKeywordBatch(List<WxReplyKeyword> lists) throws WxErrorException;

    /**
     *  3-7. 开启、关闭一个关键词回复开关
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public boolean updateRuleFlag(String ruleCode, int uesFlag) throws WxErrorException,CommonException;

    /************ 目前没啥用的方法 ************/

    /**
     * 1.根据主键获取回复内容
     *
     * @param replyCode
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public WxReply getInfoByReplyCode(String replyCode) throws WxErrorException;

    /**
     *  1-1.获取消息回复内容列表（全部）
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public List<WxReply> getList(String publicCode) throws WxErrorException ;

    /**
     *  4-8.物理删除关键词
     * @param keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Deprecated
    public boolean fDeleteKeyword(String keywordCode) throws WxErrorException,CommonException;


}
