package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.ResultUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangyg on 2017/8/9.
 */
@RestController
@RequestMapping("/template")
public class WxTemplateController {

    @Autowired
    private WxMpService wxService;

    // 获得模板列表
    @GetMapping("/list")
    public Result getAllPrivateTemplate() throws WxErrorException {
        return ResultUtils.success(this.wxService.getTemplateMsgService().getAllPrivateTemplate());
    }

    // 删除模板
    @GetMapping("/delete")
    public Result delPrivateTemplate(@RequestParam ("templateId") String templateId) throws WxErrorException{
        return ResultUtils.success(this.wxService.getTemplateMsgService().delPrivateTemplate(templateId));
    }

    // 发送模板消息
    @PostMapping("/send")
    public Result sendTemplateMsg(@RequestBody WxMpTemplateMessage templateMessage) throws WxErrorException{
        return ResultUtils.success(this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage));
    }
}
