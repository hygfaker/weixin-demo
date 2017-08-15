package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangyg on 2017/8/13.
 * 公众号管理：添加微信公众号、解除绑定、编辑公众号
 */
@RestController
@RequestMapping("public")
public class WxPublicController {
    /**
     * wechat.mp.appId = wxfab20b95598bfb32
     wechat.mp.secret = 6ef63eee61e331fcd3cd12c5330c211b
     wechat.mp.token = minstoneweixin
     wechat.mp.aesKey = Gpavu82pzlG5fNyGB6gDC2hkR9ZRdiO6ADrMLwpPuN2
     * */
    @Autowired
    private WxMpService service;


    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam(value = "appid") String appid,
                                   @RequestParam(value = "secret") String secret,
                                   @RequestParam(value = "token") String token,
                                   @RequestParam(value = "aesKey") String aesKey) throws WxErrorException{
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(appid);
        wxConfigProvider.setSecret(secret);
        wxConfigProvider.setToken(token);
        wxConfigProvider.setAesKey(aesKey);
        service.setWxMpConfigStorage(wxConfigProvider);
        System.out.println(service.getWxMpConfigStorage());
        return ResultUtil.success();
    }
}
