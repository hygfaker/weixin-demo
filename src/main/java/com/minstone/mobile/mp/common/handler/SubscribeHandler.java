package com.minstone.mobile.mp.common.handler;

import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.impl.WxPublicServiceImpl;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.service.impl.WxReplyServiceImpl;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private WxReplyServiceImpl wxReplyService;

    @Autowired
    private WxPublicServiceImpl wxPublicService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {


        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            /**
             *  获取关注时回复消息:
             *  1. 根据 touserid 获取 publicCode
             *  2. 根据 public 获取 content
             */
            String publicCode = wxPublicService.getPublicCodeByOpenId(wxMessage.getFromUser());
            String content = wxReplyService.getFollow(new WxReply(publicCode)).get(0).getContent();
            return new TextBuilder().build(content, wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
