package com.minstone.wechat.controller;

import com.minstone.wechat.domain.WxPublic;
import com.minstone.wechat.mapper.WxPublicMapper;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangyg on 2017/8/13.
 * 公众号管理：添加微信公众号、解除绑定、编辑公众号
 */
@RestController
@RequestMapping("public")
public class WxPublicController {
    /**
     * WxMpConfigStorage 这个类主要维护微信公众号相关信息。有以下信息：appid、appsecret、token、aesencodekey、accesstoken
     * wechat.mp.appId = wxfab20b95598bfb32
     * wechat.mp.secret = 6ef63eee61e331fcd3cd12c5330c211b
     * wechat.mp.token = minstoneweixin
     * wechat.mp.aesKey = Gpavu82pzlG5fNyGB6gDC2hkR9ZRdiO6ADrMLwpPuN2
     * */
    @Autowired
    private WxMpService service;

    @Autowired
    private WxPublicMapper wxPublicMapper;


    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);


    //TODO 等看懂 mybatics 怎么建表之后再继续写其他业务
    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam(value = "appid") String appid,
                                   @RequestParam(value = "secret") String secret,
                                   @RequestParam(value = "token") String token,
                                   @RequestParam(value = "aesKey") String aesKey) throws WxErrorException{
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        logger.error(String.valueOf(wxConfigProvider));
        wxConfigProvider.setAppId(appid);
        wxConfigProvider.setSecret(secret);
        wxConfigProvider.setToken(token);
        wxConfigProvider.setAesKey(aesKey);
        service.setWxMpConfigStorage(wxConfigProvider);
        System.out.println(service.getWxMpConfigStorage());
        return ResultUtil.success();
    }

    // 添加公众号
    @PostMapping("/addPublic")
    public Result addPublicAccount(@ModelAttribute WxPublic wxPublic) throws WxErrorException{
        // 保存到数据库
        wxPublicMapper.insert(wxPublic);

        // 保存到内存
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        logger.error(String.valueOf(wxConfigProvider));
        wxConfigProvider.setAppId(wxPublic.getWxPublicAppid());
        wxConfigProvider.setSecret(wxPublic.getWxPublicAppSerct());
        wxConfigProvider.setToken(wxPublic.getWxPublicToken());
        wxConfigProvider.setAesKey(wxPublic.getWxPublicAeskey());

        service.setWxMpConfigStorage(wxConfigProvider);
        logger.error(String.valueOf(service.getWxMpConfigStorage()));

        return ResultUtil.success();
    }

    // 选择公众号
    @GetMapping("/select")
    public Result switchPublicAccount(@RequestParam(value = "publicCode") int wxPublicCode){

        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        logger.error(String.valueOf(wxConfigProvider));

        WxPublic wxPublic = new WxPublic();
        wxPublic = wxPublicMapper.selectById(wxPublicCode);
        return ResultUtil.success(wxPublic);
    }

}
