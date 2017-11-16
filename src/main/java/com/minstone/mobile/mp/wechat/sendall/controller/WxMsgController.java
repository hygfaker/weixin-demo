package com.minstone.mobile.mp.wechat.sendall.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassPreviewMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangyg on 2017/8/10.
 */
@RestController
@RequestMapping("sendAll")
public class WxMsgController {

    @Autowired
    private WxMpService wxMpService;

    private static String MASS_DELTE = "https://imp.weixin.qq.com/cgi-bin/message/mass/delete?access_token=";

    // todo
    // 按 openid 列表群发消息
    // 按分组群发消息
    // 群发消息预览接口
    // 删除群发

    // 按 openId 列表群发消息
    @PostMapping("/openid")
    public CommonResult massOpenIdsMessageSend(@ModelAttribute WxMpMassOpenIdsMessage wxMpMassOpenIdsMessage) throws WxErrorException {
        WxMpMassSendResult result = this.wxMpService.massOpenIdsMessageSend(wxMpMassOpenIdsMessage);
        return ResultUtil.success(result);
    }

    // 按分组群发消息
    @PostMapping("/group")
    public CommonResult massGroupMessageSend(@ModelAttribute WxMpMassTagMessage wxMpMassTagMessage) throws WxErrorException {
        WxMpMassSendResult result = this.wxMpService.massGroupMessageSend(wxMpMassTagMessage);
        return ResultUtil.success(result);
    }

    // 群发消息预览接口
    @PostMapping("/preview")
    public CommonResult massMessagePreview(@ModelAttribute WxMpMassPreviewMessage wxMpMassPreviewMessage) throws Exception {
        WxMpMassSendResult result = this.wxMpService.massMessagePreview(wxMpMassPreviewMessage);
        return ResultUtil.success();
    }

    /** sdk 没有该接口
     * <pre>
     * 删除已经成功发送的消息（massMessageDelete）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param msgid 开始时间
     * @param articleidx   最大时间跨度1天，endDate不能早于begingDate
     */
    @PostMapping("/delete")
    public CommonResult massMessageDelete(@RequestParam String msgid, @RequestParam(value = "articleidx",required = false) String articleidx) throws WxErrorException {

        JsonObject json = new JsonObject();
        json.addProperty("msg_id", msgid);
        json.addProperty("article_idx", articleidx);
        return ResultUtil.success(this.wxMpService.post(MASS_DELTE + this.wxMpService.getAccessToken(), json.toString()));
    }

}
