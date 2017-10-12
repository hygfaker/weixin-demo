package com.minstone.wechat.api;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.model.Result;

import me.chanjar.weixin.common.exception.WxErrorException;


/**
 * 消息回复对外基础 API
 * Created by huangyg on 2017/9/21.
 */
public interface WxReplyService {
//    todo
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


    /**
     * 1.根据主键获取回复内容
     *
     * @param replyCode
     * @return
     * @throws WxErrorException
     */
    public Result getInfoByReplyCode(String replyCode) throws WxErrorException;

    /**
     *  1.根据公众号主键和回复类型获取回复内容
     *
     * @param publicCode 公众号主键
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public Result getInfo(String publicCode, Integer replyType) throws WxErrorException ;


    /**
     *  1-1.获取消息回复内容列表（全部）
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public Result getList(String publicCode) throws WxErrorException ;

    /**
     *  1-2.获取关注时回复内容消息
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public Result getFollowInfo(String publicCode) throws WxErrorException ;

    /**
     *  1-3.获取非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public Result getNormalInfo(String publicCode) throws WxErrorException ;

    /**
     *  1-4.获取关键词回复内容列表
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    public Result getKeyWordInfo(String publicCode) throws WxErrorException ;


    /**
     *  2.添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public Result addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException;

    /**
     *  2-1.添加、修改关注时回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public Result addFollowReply(String publicCode, String content) throws WxErrorException;

    /**
     *  2-2.添加、修改非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public Result addNormalReply(String publicCode, String content) throws WxErrorException;


    /**
     *  2-3.关键词回复 - 添加规则
     *
     * @param wxReplyRule 关键词回复规则
     * @return
     * @throws WxErrorException
     */
    public Result addReplyRule(WxReplyRule wxReplyRule) throws WxErrorException;

//    3-1.开启、关闭关注时回复
//    3-2.开启、关闭非关键词消息默认回复
//    3-3.开启、关闭关键词回复

    /**
     *
     * 3.修改开关。
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @param replyFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public Result updateReplyFlag(String publicCode, Integer replyFlag, Integer replyType) throws WxErrorException;

    /**
     *
     * 3-1.开启/关闭，关注时回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public Result followReplyFlag(String publicCode, Integer replyType) throws WxErrorException;

    /**
     *
     * 3-2.开启/关闭，非关键词回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public Result normalReplyFlag(String publicCode, Integer replyType) throws WxErrorException;

    /**
     *
     * 3-3.开启、关闭关键词回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public Result keywordReplyFlag(String publicCode, Integer replyType) throws WxErrorException;





    // 【关注时回复】、【非关键词回复】修改回复内容接口合并到添加接口中
    /**
     * 修改回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public Result updateReplyContent(String publicCode, String content , Integer replyType) throws WxErrorException;

    /**
     *
     * @param publicCode 公众号主键
     * @param content 回复内容
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    public Result updateReplyContent(String publicCode, String content) throws WxErrorException;

}
