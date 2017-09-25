package com.minstone.wechat.api;
import com.minstone.wechat.model.Result;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huangyg on 2017/9/21.
 */
public interface WxReplyApi {
    //    todo

    public Result getReplyDetail(String publicCode, Integer replyType) throws WxErrorException ;

    public Result getReplyDetailByKey(String replyCode) throws WxErrorException;

    public Result updateReplyContent(String publicCode, String content , Integer replyType) throws WxErrorException;

    public Result updateReplyContent(String publicCode, String content) throws WxErrorException;

    public Result updateReplyFlagByKey(String publicCode, Integer replyFlag) throws WxErrorException;

    public Result updateReplyFlag(String publicCode, Integer replyFlag, Integer replyType) throws WxErrorException;

    public Result addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException;

}
