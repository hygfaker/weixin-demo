package com.minstone.mobile.mp.wechat.message.handler;

import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.common.handler.AbstractHandler;
import com.minstone.mobile.mp.wechat.kefu.service.IWxKfSessionService;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private IWxPublicService publicService;

    @Autowired
    private IWxMessageService messageService;

    @Autowired
    private IWxKfSessionService wxKfSessionService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {


        // 根据 toUser 获取 publicCode
        String publicCode = publicService.getPublicCodeByOpenId(wxMessage.getToUser());
//             /* 用户发送的消息，根据【关键词流程】走 */
        if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT)) {
            // 获取公众号回复内容实体
            WxReply reply = new WxReply(publicCode);
            // 获取公众号配置
            WxPublicConfig publicConfig = new WxPublicConfig(publicCode);
            // 获取公众号非关键词回复实体
            WxReply normalReply = replyService.getNormal(reply);

            logger.info("===== 是否开启所有关键词回复 =====");
            if (replyService.getReplyRule(reply).getReplyFlag() == 1) {
                WxReplyRule replyRule = new WxReplyRule(publicCode, wxMessage.getContent());
                WxReplyRule matchReplyRule = replyService.getMatchContent(replyRule);

                logger.info("===== 是否匹配关键词、关键词是否打开和是否开启客服 =====");
                logger.info("matchReplyRule : {}",matchReplyRule);
                if (matchReplyRule != null) {

                    if (matchReplyRule.getKefuReplyFlag() != 1) {
                        logger.info("===== 回复键词匹配内容 =====");
                        return new TextBuilder().build(matchReplyRule.getContent(), wxMessage, wxMpService);
                    } else {
                        logger.info("===== 在线客服、创建会话、回复客服不在线内容或者回复欢迎语 =====");
                        WxMpKefuMessage kefuMessage = wxKfSessionService.createSession(wxMessage, publicConfig, wxMpService);
                        wxMpService.getKefuService().sendKefuMessage(kefuMessage);
                        return null;
                    }
                }
            }

            logger.info("===== 记录到消息列表 =====");
            WxMessage message = new WxMessage(publicCode, wxMessage);
            messageService.add(message);

            logger.info("===== 是否开启消息转发客服 =====");
            if (publicConfigService.get(publicConfig).getKefuUseFlag() == 1) {
                logger.info("===== 在线客服、创建会话、回复客服不在线内容或者回复欢迎语 =====");
                WxMpKefuMessage kefuMessage = wxKfSessionService.createSession(wxMessage, publicConfig, wxMpService);
                wxMpService.getKefuService().sendKefuMessage(kefuMessage);
                return null;
            } else if (normalReply.getReplyFlag() == 1) {
                // 回复非关键词设置的内容
                logger.info("===== 回复非关键词设置内容 =====");
                return new TextBuilder().build(normalReply.getContent(), wxMessage, wxMpService);
            }
        }else {

            logger.info("===== 记录到消息列表 =====");
            WxMessage message = new WxMessage(publicCode, wxMessage);
            messageService.add(message);

        }


//            // 判断是否开启非关键词回复，如果开启则直接回复内容
//            WxReply reply = new WxReply();
//            reply.setPublicCode(publicCode);
//            reply = replyService.getNormal(reply);
//            if (reply.getReplyFlag() == 1) {
//                return new TextBuilder().build(reply.getContent(), wxMessage, wxMpService);
//            }


        /**
         * 接受用户发送的消息，保存到数据库 【消息管理】中的【所有消息】

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
         **/


//        if(StringUtils.startsWithAny(wxMessage.getContent(),"客服"))
//
//    {
//        WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
//        kefuMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
//        kefuMessage.setToUser(wxMessage.getFromUser());
//        kefuMessage.setContent(wxMessage.getKfAccount() + "测试回复");
//        wxMpService.getKefuService().sendKefuMessage(kefuMessage);
//    }
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
