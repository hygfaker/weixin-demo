package com.minstone.wechat.api;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.model.Result;

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

//    1-1.获取消息回复内容列表（全部）
//    1-2.获取关注时回复内容消息
//    1-3.获取非关键词消息默认回复
//    1-4.获取关键词回复内容列表

//    2-1.添加、修改关注时消息回复内容
//    2-2.添加、修改非关键词消息默认回复内容
//    2-3.关键词回复 - 添加规则

//    3-1.开启、关闭关注时回复
//    3-2.开启、关闭非关键词消息默认回复
//    3-3.开启、关闭关键词回复

//    4-1.获取关键词回复内容列表（同1-4）
//    4-2.添加某个规则 (同2-3)
//    4-3.逻辑删除某个规则
//    4-4.物理删除某个规则
//    4-5.编辑某个规则
//    4-6.开启、关闭某个规则

//    拓展
//    5-1.添加关注是消息回复内容
//    5-2.添加非关键词消息回复内容


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
     *  1-4.获取关键词回复内容列表
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public List<WxReplyRule> getKeyWordInfo(String publicCode) throws WxErrorException ;


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
    public int editRule(WxReplyRule replyRule) throws WxErrorException;

    /**
     * 4-6.开启、关闭某个规则
     *
     * @param ruleCode 关键词主键
     * @return
    * @throws WxErrorException
     */
    public int updateRuleFlag(String ruleCode, int ruleFlag) throws WxErrorException;



}
