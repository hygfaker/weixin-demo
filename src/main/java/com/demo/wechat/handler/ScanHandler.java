package com.demo.wechat.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by huangyg on 2017/8/15.
 */
public abstract class ScanHandler extends AbstractHandler {
    // todo 获取扫描带参数二维码的数据

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        if (!wxMpXmlMessage.getMsgType().equals(WxConsts.EVT_SCAN)){
            return null;
        }

        return null;
    }
}
