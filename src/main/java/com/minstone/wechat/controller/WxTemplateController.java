package com.minstone.wechat.controller;

import com.minstone.wechat.bean.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by huangyg on 2017/8/9.
 */
@RestController
@RequestMapping("/template")
public class WxTemplateController {

    @Autowired
    private WxMpService wxService;


    // 设置所属行业
    @PostMapping("/setIndustry")
    public Result setIndustry(@RequestParam String primary,@RequestParam String second) throws WxErrorException{
        WxMpTemplateIndustry industry = new WxMpTemplateIndustry(new WxMpTemplateIndustry.Industry(primary),
                new WxMpTemplateIndustry.Industry(second));
        return ResultUtil.success(this.wxService.getTemplateMsgService().setIndustry(industry));
    }

    // 获取设置的行业信息
    @GetMapping("/industry")
    public Result setIndustry() throws WxErrorException{
           return ResultUtil.success(this.wxService.getTemplateMsgService().getIndustry());
    }
    // 获得模板列表
    @GetMapping("/list")
    public Result getAllPrivateTemplate() throws WxErrorException {
        return ResultUtil.success(this.wxService.getTemplateMsgService().getAllPrivateTemplate());
    }

    // 删除模板
    @GetMapping("/delete")
    public Result delPrivateTemplate(@RequestParam ("templateId") String templateId) throws WxErrorException{
        return ResultUtil.success(this.wxService.getTemplateMsgService().delPrivateTemplate(templateId));
    }

    // 发送模板消息
    @PostMapping("/send")
    public Result sendTemplateMsg(@RequestBody WxMpTemplateMessage templateMessage) throws WxErrorException{
        String msgid = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        HashMap<String,String> res = new HashMap<>();
        res.put("msgid",msgid);
        return ResultUtil.success(res);
    }
}
