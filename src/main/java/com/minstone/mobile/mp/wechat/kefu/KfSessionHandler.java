package com.minstone.mobile.mp.wechat.kefu;

import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.common.handler.AbstractHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
@Component
public class KfSessionHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        //TODO 对会话做处理，是否有开启该 handler 的功能，是通过数据库拿数据判断
        // todo 当用户开启会话的时候，微信会接收到 kf_create_session 事件并转发给我们。
        /** 接收到的 XML 格式：
         *<xml>
         *   <ToUserName><![CDATA[gh_b33b8b40631c]]></ToUserName>
         *   <FromUserName><![CDATA[oWGSawXsClb5YDNhBrOQG5WXo3ew]]></FromUserName>
         *   <CreateTime>1511401556</CreateTime>
         *   <MsgType><![CDATA[event]]></MsgType>
         *   <Event><![CDATA[kf_create_session]]></Event>
         *   <KfAccount><![CDATA[kf2017@md_minstone]]></KfAccount>
         *   <Encrypt><![CDATA[5coE4lJBIBKqJ6a6vsg0QnlDhQeat7hb1d4OnOrWrdcUiEeMCHvPl5TGeIlw7RlzEkohssMmvppvA6XPi+f9625ud158CE5FQQuHx4MbhCCrRVavAmq0ooLVsYlXK3pTQWPImGiG0cdwMkcOL0qLgLzQQN+nypPYUUV7X9gHJbs24yUiPSoIkBDbkxf48+pDOEAeCKKCr+za7OtTzrPZo1Exmohb9QkyELLJTmQ3Gts9d7vqOJ+RWvk8c8A8rZiI5gjqDexcp50EFlzYhfFkXOjkH1Z1nGekOLXZP1mh/NU9SrL+ZxtgCelRGT8ctMuK8MZJM9hPJc8MXjIbmn318363I9eW9DAsdz3IZmtRGZZz06cZptLJVIhc85W6xAGAI7RgaG+c3/dV+QlNvBbPPzl5x4lALwEW9nLTLfqC61dWohAWzIUU6LPvSks62IQGK6Whbw9MVqKj8X5avSn1+w==]]></Encrypt>
         *</xml>
         */
        if (WxMpEventConstants.CustomerService.KF_CREATE_SESSION.equals(wxMessage.getEvent())){
            WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
            kefuMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
            kefuMessage.setToUser(wxMessage.getFromUser());
            kefuMessage.setContent(wxMessage.getKfAccount() + " 为您服务");
            try {
                logger.info("-- " + wxMessage.getKfAccount() + " 为您服务 --");
                wxMpService.getKefuService().sendKefuMessage(kefuMessage);
            } catch (WxErrorException e) {
                logger.error(e.toString());
            } finally {
                return null;
            }
        }
        if (WxMpEventConstants.CustomerService.KF_CLOSE_SESSION.equals(wxMessage.getEvent())){
            WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
            kefuMessage.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
            kefuMessage.setToUser(wxMessage.getFromUser());
            kefuMessage.setContent(wxMessage.getKfAccount() + " 服务结束");
            try {
                logger.info("-- " + wxMessage.getKfAccount() + " 服务结束 --");
                wxMpService.getKefuService().sendKefuMessage(kefuMessage);
            } catch (WxErrorException e) {
                logger.error(e.toString());
            } finally {
                return null;
            }
        }


        return null;
    }

}
