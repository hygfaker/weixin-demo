package com.minstone.mobile.mp.common.handler;

import com.minstone.mobile.mp.common.builder.VideoBuilder;
import com.minstone.mobile.mp.common.builder.ImageBuilder;
import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.common.builder.VoiceBuilder;
import com.minstone.mobile.mp.utils.JsonUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            //TODO 可以选择将消息保存到本地
            logger.info(String.valueOf(wxMessage));
        }
        /*
        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
            return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
        }
        */
        // 关键字自动回复
        if (wxMessage.getContent().endsWith("文字")){
            return new TextBuilder().build("我是个人测试公众号", wxMessage, weixinService);
        }
        if (wxMessage.getContent().endsWith("图片")){
            return new ImageBuilder().build("ugtdCRNQEzwsBBNmNFTjSjeMy4ajXbq4I1zVoXkgYsU",wxMessage,weixinService);
        }
        if (wxMessage.getContent().endsWith("音频")){
            return new VoiceBuilder().build("ugtdCRNQEzwsBBNmNFTjSi-cDQnbZ0EloB3HiJkAeU8", wxMessage, weixinService);
        }
        if (wxMessage.getContent().endsWith("视频")){
            return new VideoBuilder().build("ugtdCRNQEzwsBBNmNFTjSqs34eNB-TLr9ePh80y-ZqA","-titile-" ,"-introduction-",wxMessage, weixinService);
        }
        if (wxMessage.getContent().endsWith("图文")){
            return new TextBuilder().build("我是个人测试公众号", wxMessage, weixinService);
        }

        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtil.toJson(wxMessage);

        return new ImageBuilder().build("ugtdCRNQEzwsBBNmNFTjSjeMy4ajXbq4I1zVoXkgYsU",wxMessage,weixinService);
    }
}
