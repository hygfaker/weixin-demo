package com.minstone.mp.wechat.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutVideoMessage;

/**
 * Created by huangyg on 2017/9/1.
 */
public class VideoBuilder extends AbstractBuilder {


    public WxMpXmlOutMessage build(String content, String title, String introduction, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutVideoMessage m = WxMpXmlOutMessage
                .VIDEO()
                .title(title)
                .description(introduction)
                .mediaId(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return m;
    }

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutVideoMessage m = WxMpXmlOutMessage
                .VIDEO()
                .mediaId(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return m;
    }
}
