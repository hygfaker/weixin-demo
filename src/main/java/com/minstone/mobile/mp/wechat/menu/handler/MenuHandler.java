package com.minstone.mobile.mp.wechat.menu.handler;

import com.minstone.mobile.mp.common.handler.AbstractHandler;
import com.minstone.mobile.mp.wechat.kefu.constant.KefuConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
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


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {


        String msg = String.format("type:%s, event:%s, key:%s", wxMessage.getMsgType(), wxMessage.getEvent(), wxMessage.getEventKey());

        // todo 如果是 click 类型的客服按钮，则判断是否有在线客服，有的话获取第一个并且创建会话
        if (KefuConsts.KEFU_KEY.equals(wxMessage.getEventKey())) {
            try {
                List<WxMpKfInfo> kfList = weixinService.getKefuService().kfOnlineList().getKfOnlineList();
                if (kfList.size() > 0) {
                    WxMpKfInfo kfInfo = kfList.get(0);
                    return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
                }
            } catch (WxErrorException e) {
                logger.error(String.valueOf(e));
            } finally {
                return null;
            }
        }

        logger.error("fromUser: {}", wxMessage.getFromUser());

        return WxMpXmlOutMessage.TEXT().content(msg)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }
}
