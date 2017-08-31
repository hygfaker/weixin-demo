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
public class WxPublicController {

    //todo
    // 添加公众号
    // 获取某个公众号
    // 获取所有公众号

    @Autowired
    private WxMpService service;
    @Autowired
    private WxPublicMapper wxPublicMapper;
    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);

    // 添加公众号
    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile wxPublicHeadImg, @RequestParam MultipartFile wxPublicQrcode) throws WxErrorException, IOException {

        WxPublic wxPublic = this.createPublicAccount(reqMap,wxPublicHeadImg,wxPublicQrcode);
        // 保存公众号信息到数据库
        wxPublicMapper.insert(wxPublic);
        return ResultUtil.success();
    }

    // 获取某个公众号
    @GetMapping("/get")
    public Result getPublicAccount(@RequestParam int publicCode){
        WxPublic wxPublic = wxPublicMapper.getByCode(publicCode);
        return ResultUtil.success(wxPublic);
    }

    // 获取所有公众号
    @GetMapping("/getAll")
    public Result getAllPublicAccount(){
        return ResultUtil.success(wxPublicMapper.getAll());
    }

    // 编辑公众号
    @PostMapping("/update")
    public Result updatePublicAccount(@RequestParam int publicCode,@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile wxPublicHeadImg, @RequestParam MultipartFile wxPublicQrcode) throws WxErrorException, IOException {

        WxPublic wxPublic = this.createPublicAccount(reqMap,wxPublicHeadImg,wxPublicQrcode);
        // 保存 publicCode
        wxPublic.setWxPublicCode(publicCode);
        // 保存公众号信息到数据库
        wxPublicMapper.updateById(wxPublic);
        return ResultUtil.success();
    }

    // 解除绑定公众号
    @PostMapping("/delete")
    public Result deletePublicAccount(@RequestParam int publicCode){
        wxPublicMapper.deleteById(publicCode);
        return ResultUtil.success();
    }

    public WxPublic createPublicAccount(Map<String,Object>reqMap, @RequestParam MultipartFile wxPublicHeadImg, @RequestParam MultipartFile wxPublicQrcode) throws IOException {
        WxPublic wxPublic = new WxPublic(reqMap);

        // 将 multipartfile 转化成 byte[]
        byte[] imgByte = wxPublicHeadImg.getBytes();
        byte[] qrcodeByte = wxPublicQrcode.getBytes();
        wxPublic.setWxPublicQrcodeName(wxPublicQrcode.getOriginalFilename());
        wxPublic.setWxPublicHeadImgName(wxPublicHeadImg.getOriginalFilename());
        wxPublic.setWxPublicQrcode(qrcodeByte);
        wxPublic.setWxPublicHeadImg(imgByte);

        // 保存公众号信息
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(wxPublic.getWxPublicAppid());
        wxConfigProvider.setSecret(wxPublic.getWxPublicAppSerct());
        wxConfigProvider.setToken(wxPublic.getWxPublicToken());
        wxConfigProvider.setAesKey(wxPublic.getWxPublicAeskey());
        service.setWxMpConfigStorage(wxConfigProvider);

        return wxPublic;
    }

}
