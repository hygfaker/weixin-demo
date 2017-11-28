package com.minstone.mobile.mp.wechat.kefu.impl;

import com.minstone.mobile.mp.wechat.kefu.IWxKfSessionService;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfInfo;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangyg
 * @description
 * @since 2017/11/27
 */

@Service
@Transactional
@Slf4j
public class WxKfSessionService implements IWxKfSessionService {



    @Autowired
    private IWxPublicConfigService publicConfigService;

    /**
     * 创建会话并且返回即将发送给用户的内容
     *
     * @param wxMessage 用户发送给微信的消息实体
     * @param publicConfig 公众号配置
     * @return WxMpKefuMessage 返回需要发送给用户的客服消息实体
     * @author huangyg
     */

    @Transactional(readOnly = true)
    @Override
    public WxMpKefuMessage createSession(WxMpXmlMessage wxMessage, WxPublicConfig publicConfig,WxMpService mpService) throws WxErrorException {
        // 判断是否有在线客服，有的话创建会话
        List<WxMpKfInfo> kfInfoList = mpService.getKefuService().kfOnlineList().getKfOnlineList();

        WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
        kefuMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
        kefuMessage.setToUser(wxMessage.getFromUser());

        String content = null;
        // 获取在线客服列表，否-》回复客服不在线内容
        if (kfInfoList.size() > 0) {
            WxMpKfInfo kfInfo = kfInfoList.get(0);
            log.info("微信用户的 openid = {}, 在线客服的 account = {}", wxMessage.getFromUser(), kfInfo.getAccount());
            // 创建会话成功，是-》回复客服接入欢迎语，否-》回复客服不在线内容
            if (mpService.getKefuService().kfSessionCreate(wxMessage.getFromUser(), kfInfo.getAccount())) {
                log.info("创建会话成功，回复的内容为 ： {}", content);
                content = publicConfigService.get(publicConfig).getKefuOnlineMessage();
                return kefuMessage;
            }
        }

        content = publicConfigService.get(publicConfig).getKefuOfflineMessage();
        kefuMessage.setContent(content);
        log.info("没有客服在线，回复的内容为 ： {}", content);
        return kefuMessage;
//        mpService.getKefuService().sendKefuMessage(kefuMessage);
    }
}
