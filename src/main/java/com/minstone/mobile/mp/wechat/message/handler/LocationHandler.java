package com.minstone.mobile.mp.wechat.message.handler;

import com.minstone.mobile.mp.common.builder.ResponseBuilder;
import com.minstone.mobile.mp.common.handler.AbstractHandler;
import com.minstone.mobile.mp.utils.LocationUtil;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord;
import com.minstone.mobile.mp.wechat.message.service.IWxMessagePushService;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Transactional
public class LocationHandler extends AbstractHandler {

    @Autowired
    private IWxMessagePushService messagePushService;
    @Autowired
    private IWxPublicService publicService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        // 处理地理位置推送，获取可以推送的内容列表
        List<WxMessagePush> messagePushes = messagePushService.selectMessageByOpenIdAndUserCode(wxMessage.getToUser(),wxMessage.getFromUser());

        WxMessagePush toPush = null;

        for (WxMessagePush wxMessagePush : messagePushes) {
            double distance = LocationUtil.getDistance(wxMessage.getLongitude(), wxMessage.getLatitude(), wxMessagePush.getLongitude(), wxMessagePush.getLatitude());
            // 5. 满足范围并且，满足定点消息推送表中没有记录
            if (distance < wxMessagePush.getRadius() * 1000){
                toPush = wxMessagePush;
            }
        }

        if (toPush != null){
            // 将用户和数据保存到数据记录表中
            messagePushService.addRecord(new WxMessagePushRecord(wxMessage.getFromUser(), toPush.getPushCode()));
            try {
                return new ResponseBuilder().build(toPush.getContent(), wxMessage, null);
            } catch (Exception e) {
                this.logger.error("位置消息接收处理失败", e);
                return null;
            }
        }else {
            return null;
        }
        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用
    }

}
