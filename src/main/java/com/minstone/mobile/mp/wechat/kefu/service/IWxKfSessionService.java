package com.minstone.mobile.mp.wechat.kefu.service;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author huangyg
 * @description
 * @since 2017/11/27
 */
public interface IWxKfSessionService {
    /**
     * 创建会话并且返回即将发送给用户的内容
     *
     * @param wxMessage 用户发送给微信的消息实体
     * @param publicConfig 公众号配置
     * @return WxMpKefuMessage 返回需要发送给用户的客服消息实体
     * @author huangyg
     */

    public WxMpKefuMessage createSession(WxMpXmlMessage wxMessage, WxPublicConfig publicConfig,WxMpService mpService) throws WxErrorException;
}
