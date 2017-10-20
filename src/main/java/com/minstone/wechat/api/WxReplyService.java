package com.minstone.wechat.api;
import com.github.pagehelper.PageInfo;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 消息回复对外基础 API
 * Created by huangyg on 2017/9/21.
 */
@Service
public interface WxReplyService {
//    todo




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
//    3-1. 获取关键词回复内容列表（分页）
//    3-1-1. 获取单个关键词规则
//    3-1-2. 获取关键词规则下的关键词列表（分页）
//    3-1-2-test. 获取关键词规则下的关键词列表（分页）

//    3-2. 开启、关闭关键词回复开关

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


//    1.根据主键获取回复内容
//    1.根据公众号主键和回复类型获取回复内容
//    1-1.获取消息回复内容列表（全部）
//    1-2.获取关注时回复   xx
//    1-3.获取非关键词回复  xx
//    1-4.分页获取关键词回复内容列表  xx

//    2.添加回复内容
//    2-1.添加、修改关注时回复内容  xx
//    2-2.添加、修改非关键词回复内容 xx
//    2-3.关键词回复 - 添加规则
//    2-4.批量更新关键词

//    3.修改开关。
//    3-1.开启、关闭关注时回复开关  xx
//    3-2.开启、关闭非关键词回复开关  xx
//    3-3.开启、关闭关键词回复开关

//    ----- 关键词规则 -----
//    4-1.获取关键词回复内容列表（同1-4）
//    4-2.添加某个规则 (同2-3)
//    4-3.逻辑删除某个规则
//    4-4.物理删除某个规则
//    4-5.编辑某个规则
//    4-6.开启、关闭某个规则
//    4-7.逻辑删除关键词
//    4-8.物理删除关键词


//    拓展
//    5-1.添加关注时消息回复（包括开关和回复内容）
//    5-2.添加非关键词消息回复（包括开关和回复内容）
//    5-3.获取某个关键词回复
//    5-4.批量逻辑删除关键词规则回复
//    5-5.批量物理删除关键词规则回复


    /**
     * 获取单个关键词规则
     * @param ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     */
    public WxReplyRule getReplyRule(String ruleCode) throws WxErrorException;

    /**
     * 添加公众号的时候，初始化【消息回复】数据
     * @throws WxErrorException
     */
    public void initData() throws WxErrorException;

    /**
     * 1.根据主键获取回复内容
     *
     * @param replyCode
     * @return
     * @throws WxErrorException
     */
    public WxReply getInfoByReplyCode(String replyCode) throws WxErrorException;

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
     *  1-1.获取消息回复内容列表（全部）
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getList(String publicCode) throws WxErrorException ;

    /**
     *  1-2.获取关注时回复内容消息
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getFollowInfo(String publicCode) throws WxErrorException ;

    /**
     *  1-3.获取非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReply> getNormalInfo(String publicCode) throws WxErrorException ;

    /**
     *  1-4.分页获取关键词回复内容列表
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public PageInfo<WxReplyRule> getKeywordPage(String publicCode, int currentPage, int pageSize) throws WxErrorException ;


    /**
     *  2.添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public int addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException;

    /**
     *  2-1.添加、修改关注时回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public int addFollowReply(String publicCode, String content) throws WxErrorException;

    /**
     *  2-2.添加、修改非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public int addNormalReply(String publicCode, String content) throws WxErrorException;

    /**
     *  2-3.关键词回复 - 添加规则
     *
     * @param wxReplyRule 关键词回复规则
     * @return
     * @throws WxErrorException
     */
    public int addReplyRule(WxReplyRule wxReplyRule) throws WxErrorException;

    /**
     *  2-4.批量更新关键词
     * @param lists 关键词列表
     * @return
     * @throws WxErrorException
     */
    public int updateKeywordBatch(List<WxReplyKeyword> lists) throws WxErrorException;

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
    public int updateReplyFlag(String publicCode, Integer useFlag, Integer replyType) throws WxErrorException;

    /**
     *
     * 3-1.开启/关闭，关注时回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public int followReplyFlag(String publicCode, Integer useFlag) throws WxErrorException;

    /**
     *
     * 3-2.开启/关闭，非关键词回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public int normalReplyFlag(String publicCode, Integer useFlag) throws WxErrorException;

    /**
     *
     * 3-3.开启、关闭关键词回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public int keywordReplyFlag(String publicCode, Integer useFlag) throws WxErrorException;

    /**
     * 4-3.逻辑删除某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public int deleteRule(String ruleCode) throws WxErrorException;

    /**
     * 4-4.物理删除某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public int FDeleteRule(String ruleCode) throws WxErrorException;

    /**
     * 4-5.编辑某个规则
     *
     * @param replyRule 规则内容
     * @return
     * @throws WxErrorException
     */
    public int updateRule(WxReplyRule replyRule) throws WxErrorException;

    /**
     * 4-6.开启、关闭某个规则
     *
     * @param ruleCode 关键词主键
     * @return
    * @throws WxErrorException
     */
    public int updateRuleFlag(String ruleCode, int ruleFlag) throws WxErrorException;

    /**
     *  4-7.逻辑删除关键词
     * @param keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public int deleteKeyword(String keywordCode) throws WxErrorException;

    /**
     *  4-8.物理删除关键词
     * @param keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    public int FDeleteKeyword(String keywordCode) throws WxErrorException;


    /**
     * 5-4.批量逻辑删除关键词规则
     * @param ruleCodes 关键词规则主键们
     * @return
     * @throws WxErrorException
     */
    public int deleteRuleBatch(String[] ruleCodes) throws WxErrorException;

    /**
     * 5-5.批量物理删除关键词规则
     * @param ruleCodes 关键词规则主键们
     * @return
     * @throws WxErrorException
     */
    public int FDeleteRuleBatch(String[] ruleCodes) throws WxErrorException;

    public List<WxReplyRule> test(String publicCode) throws WxErrorException;

}
