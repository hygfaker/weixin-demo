package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.ResultUtil;
import com.sun.tracing.dtrace.ModuleAttributes;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassPreviewMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangyg on 2017/8/10.
 */
@RestController
@RequestMapping("mass")
public class WxMsgController {
    private static Logger logger = LoggerFactory.getLogger(WxMsgController.class);

    @Autowired
    private WxMpService wxMpService;

    // 按 openid 列表群发消息
    // 按分组群发消息
    // 群发消息预览接口

    // 按 openId 列表群发消息
    @PostMapping("/openids")
    public Result massOpenIdsMessageSend(@ModelAttribute WxMpMassOpenIdsMessage wxMpMassOpenIdsMessage) throws WxErrorException {
        WxMpMassSendResult result = this.wxMpService.massOpenIdsMessageSend(wxMpMassOpenIdsMessage);
        return ResultUtil.success(result);
    }

    // 按分组群发消息
    @PostMapping("/group")
    public Result massGroupMessageSend(@ModelAttribute WxMpMassTagMessage wxMpMassTagMessage) throws WxErrorException {
        WxMpMassSendResult result = this.wxMpService.massGroupMessageSend(wxMpMassTagMessage);
        return ResultUtil.success(result);
    }

    // 群发消息预览接口
    @PostMapping("/preview")
    public Result massMessagePreview(@ModelAttribute WxMpMassPreviewMessage wxMpMassPreviewMessage) throws Exception {
        WxMpMassSendResult result = this.wxMpService.massMessagePreview(wxMpMassPreviewMessage);
        return ResultUtil.success();
    }

}
