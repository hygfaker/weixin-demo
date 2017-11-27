package com.minstone.mobile.mp.wechat.message.handler;

import com.minstone.mobile.mp.common.builder.VideoBuilder;
import com.minstone.mobile.mp.common.builder.ImageBuilder;
import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.common.builder.VoiceBuilder;
import com.minstone.mobile.mp.common.handler.AbstractHandler;
import com.minstone.mobile.mp.utils.JsonUtil;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import com.minstone.mobile.mp.wechat.reply.service.impl.WxReplyServiceImpl;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private IWxReplyService replyService;

    @Autowired
    private IWxPublicConfigService publicConfigService;

    @Autowired
    private IWxMessageService messageService;

    @Autowired
    private IWxPublicService publicService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        // 根据 toUser 获取 publicCode
        String publicCode = publicService.getPublicCodeByOpenId(wxMessage.getToUser());

        /* 用户发送的消息，根据【关键词流程】走 */
        WxReply reply = new WxReply(publicCode);
        // 开启所有关键词回复，则匹配关键词
//        if (replyService.getReplyRule(reply).getReplyFlag() == 1){
//
//        }else {
//            WxPublicConfig publicConfig = new WxPublicConfig()
//            if (publicConfigService.get())
//        }

        /**
         * 接受用户发送的消息，保存到数据库 【消息管理】中的【所有消息】
         **/
        if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_IMAGE) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_VOICE) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_SHORTVIDEO) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_VIDEO) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_NEWS) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_LINK) ||
                wxMessage.getMsgType().equals(WxConsts.XML_MSG_LOCATION)) {

            WxMessage message = new WxMessage(wxMessage);
            message.setPublicCode(publicCode);
            // 添加到数据库
            messageService.add(message);
        }
        /**
         * 如果是文字消息，匹配关键词/非关键词，自动回复内容
         **/
        if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT)) {

            /**
             * 关键词和非关键词的判断有先后顺序，先判断关键词后，没有匹配关键词回复再来判断非关键词回复。
             */

            // 判断是否开启关键词回复，开启的话匹配关键词回复内容
            if (replyService.keywordsUseFlag(publicCode,2)) {
                WxReplyRule replyRule = new WxReplyRule();
                replyRule.setPublicCode(publicCode);
                replyRule.setKeyword(wxMessage.getContent());
                List<String> mathcContents = replyService.getMatchContent(replyRule);
                if (mathcContents.size() > 0) {
                    return new TextBuilder().build(mathcContents.get(0), wxMessage, wxMpService);
                }
            }

//            // 判断是否开启非关键词回复，如果开启则直接回复内容
//            WxReply reply = new WxReply();
//            reply.setPublicCode(publicCode);
//            reply = replyService.getNormal(reply);
//            if (reply.getReplyFlag() == 1) {
//                return new TextBuilder().build(reply.getContent(), wxMessage, wxMpService);
//            }
        }

        if (StringUtils.startsWithAny(wxMessage.getContent(),  "客服")){
            WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
            kefuMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
            kefuMessage.setToUser(wxMessage.getFromUser());
            kefuMessage.setContent(wxMessage.getKfAccount() + "测试回复");
            wxMpService.getKefuService().sendKefuMessage(kefuMessage);
        }
        /*
        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        if (StringUtils.startsWithAny(wxMessage.getContent(),  "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
            return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
        }

 */

        /**
         * 避免系统提示“该公众号暂时无法提供服务，请稍后再试”：
         * 1、直接回复success（推荐方式）
         * 2、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
         */
        return null;
    }
}
