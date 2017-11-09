package com.minstone.mobile.mp.common.handler;

import ch.qos.logback.core.pattern.FormatInfo;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.utils.LocationUtil;
import com.minstone.mobile.mp.wechat.message.dao.WxMessagePushRecordDao;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord;
import com.minstone.mobile.mp.wechat.message.service.IWxMessagePushService;
import com.minstone.mobile.mp.wechat.message.service.impl.WxMessagePushServiceImpl;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.publics.service.impl.WxPublicServiceImpl;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.aspectj.bridge.WeaveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Transactional
public class LocationHandler extends AbstractHandler {

    @Autowired
    private WxMessagePushServiceImpl messagePushService;
    @Autowired
    private WxPublicServiceImpl publicService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        // 如果不是地理位置推送，直接退出
        if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
            return null;
        }

        // 处理地理位置推送

        // 1. 获取微信原始 id 对应的公众号主键
        String publicCode = publicService.get(new WxPublic(wxMessage.getToUser())).getPublicCode();

        // 2. 根据公众号主键获取已开启发送的的定点消息列表
        List<WxMessagePush> messagePushes = messagePushService.getList(new WxMessagePush(publicCode, 1));

        // 3. 根据用户获取定点消息推送表中的记录
        List<String> pushRecords = messagePushService.getRecord(wxMessage.getFromUser());

        // 4. 获取满足半径范围内，待发送的定点消息
        List<WxMessagePush> toPushList = new ArrayList<WxMessagePush>();
        for (WxMessagePush wxMessagePush : messagePushes) {
            double distance = LocationUtil.getDistance(wxMessage.getLongitude(), wxMessage.getLatitude(), wxMessagePush.getLongitude(), wxMessagePush.getLatitude());
            // 5. 满足范围并且，满足定点消息推送表中没有记录
            if (distance < wxMessagePush.getRadius() * 1000 && pushRecords.contains(wxMessagePush.getPushCode())){
                toPushList.add(wxMessagePush);
            }
        }

        // 6. 将用户和数据保存到数据记录表中
        messagePushService.addRecord(new WxMessagePushRecord(wxMessage.getFromUser(), toPushList.get(0).getPushCode()));

        // 5. 推送
        try {
            return new TextBuilder().build(toPushList.get(0).getContent(), wxMessage, null);
        } catch (Exception e) {
            this.logger.error("位置消息接收处理失败", e);
            return null;
        }

        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用

    }

}
