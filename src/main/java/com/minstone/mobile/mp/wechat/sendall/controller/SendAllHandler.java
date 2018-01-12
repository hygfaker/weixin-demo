package com.minstone.mobile.mp.wechat.sendall.controller;

import com.minstone.mobile.mp.common.handler.AbstractHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.Constants;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * @author huangyg
 * @description
 * @since 2017/11/13
 */
public class SendAllHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 判断消息是否为群发图文消息
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.MassMsgType.MPNEWS)){

        }
        return null;
    }
}
