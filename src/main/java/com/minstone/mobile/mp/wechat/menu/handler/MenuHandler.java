package com.minstone.mobile.mp.wechat.menu.handler;

import com.minstone.mobile.mp.common.builder.ResponseBuilder;
import com.minstone.mobile.mp.common.handler.AbstractHandler;
import com.minstone.mobile.mp.wechat.kefu.constant.KefuConsts;
import com.minstone.mobile.mp.wechat.kefu.service.IWxKfSessionService;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfInfo;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Autowired
    private IWxPublicService publicService;

    @Autowired
    private IWxPublicConfigService publicConfigService;

    @Autowired
    private IWxKfSessionService wxKfSessionService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        // 根据 toUser 获取 publicCode
        String publicCode = publicService.getPublicCodeByOpenId(wxMessage.getToUser());
        // 获取公众号配置
        WxPublicConfig publicConfig = new WxPublicConfig(publicCode);

        // todo 如果是 click 类型的客服按钮，则判断是否开启客服，有在线客服，有的话获取第一个并且创建会话
        if (KefuConsts.KEFU_KEY.equals(wxMessage.getEventKey())) {
            logger.info("=====  点击菜单触发客服 =====");
            WxMpKefuMessage kefuMessage = wxKfSessionService.createSession(wxMessage, publicConfig, wxMpService);
            // 发送客服在线/不在线信息
            return new ResponseBuilder().textBuild(kefuMessage.getContent(), wxMessage);
        }

        logger.error("fromUser: {}", wxMessage.getFromUser());

        return WxMpXmlOutMessage.TEXT().content(wxMessage.getMsg())
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }
}
