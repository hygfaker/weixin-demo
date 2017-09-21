package com.minstone.wechat.api.imp;

import com.minstone.wechat.api.WxPublicApi;
import com.minstone.wechat.api.WxPublicService;
import com.minstone.wechat.domain.WxPublic;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/13.
 *
 * WxMpConfigStorage 这个类主要维护微信公众号相关信息。有以下信息：appid、appsecret、token、aesencodekey、accesstoken
 * wechat.mp.appId = wxfab20b95598bfb32
 * wechat.mp.secret = 6ef63eee61e331fcd3cd12c5330c211b
 * wechat.mp.token = minstoneweixin
 * wechat.mp.aesKey = Gpavu82pzlG5fNyGB6gDC2hkR9ZRdiO6ADrMLwpPuN2
 *
 */
@RestController
@RequestMapping("public")
public class WxPublicImp implements WxPublicApi{

    //todo
    // 添加公众号
    // 获取某个公众号
    // 获取所有公众号
    // 编辑公众号
    // 解除绑定公众号

    @Autowired
    private WxMpService service;

    @Autowired
    private WxPublicService publicService;

    private static Logger logger = LoggerFactory.getLogger(WxPublicImp.class);

    // 添加公众号
    @Override
    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {
        return  publicService.addPublicAccount(reqMap,publicHeadImg,publicQrcode);
    }

    // 获取某个公众号并切换到当前公众号
    @Override
    @GetMapping("/get")
    public Result getPublicAccount(@RequestParam String publicCode) throws WxErrorException, IOException{

        WxPublic wxPublic = publicService.getPublicAccount(publicCode);
        if (wxPublic == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            // 切换公众号
            WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
            wxConfigProvider.setAppId(wxPublic.getAppId());
            wxConfigProvider.setSecret(wxPublic.getAppSerct());
            wxConfigProvider.setToken(wxPublic.getToken());
            wxConfigProvider.setAesKey(wxPublic.getAeskey());
            service.setWxMpConfigStorage(wxConfigProvider);
            return ResultUtil.success(wxPublic);
        }

    }

    // 获取所有公众号
    @Override
    @GetMapping("/getAll")
    public Result getAllPublicAccount() throws WxErrorException, IOException{
         return publicService.getAllPublicAccount();
    }

    // 编辑公众号
    @Override
    @PostMapping("/update")
    public Result updatePublicAccount(@RequestParam String publicCode, @RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {
        return publicService.updatePublicAccount(publicCode,reqMap,publicHeadImg,publicQrcode);
    }

    // 解除绑定公众号
    @Override
    @GetMapping("/delete")
    public Result deletePublicAccount(@RequestParam String  publicCode) throws WxErrorException, IOException{
       return publicService.deletePublicAccount(publicCode);
    }


}
