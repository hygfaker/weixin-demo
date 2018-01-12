package com.minstone.mobile.mp.common.builder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.*;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public class ResponseBuilder extends AbstractBuilder {
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
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        WxMpXmlOutMessage m = null;
        if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE)){
            m = WxMpXmlOutMessage
                    .TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
        }else if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.TEXT)){
            m = WxMpXmlOutMessage
                    .TEXT()
                    .content(content)
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();

        } else if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.IMAGE)){
            m = WxMpXmlOutMessage.IMAGE().mediaId(content)
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
        }else if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.NEWS)){
            m = WxMpXmlOutMessage
                    .NEWS()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();

        }else if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.VIDEO)){
            m = WxMpXmlOutMessage
                    .VIDEO()
                    .mediaId(content)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();

        }else if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.VOICE)){
            m = WxMpXmlOutMessage
                    .VOICE()
                    .mediaId(content)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
        }

        return m;
    }

}
