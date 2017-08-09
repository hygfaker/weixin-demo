package com.demo.wechat.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("list")
    public List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException {
        return this.wxService.getTemplateMsgService().getAllPrivateTemplate();
    }


}
