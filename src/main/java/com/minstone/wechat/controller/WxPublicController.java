package com.minstone.wechat.controller;

import com.minstone.wechat.domain.WxPublic;
import com.minstone.wechat.domain.WxPublicFile;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.mapper.WxPublicFileMapper;
import com.minstone.wechat.mapper.WxPublicMapper;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import com.minstone.wechat.utils.code.IdGen;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
    // 编辑公众号
    // 解除绑定公众号

    @Autowired
    private WxMpService service;
    @Autowired
    private WxPublicMapper wxPublicMapper;

    @Autowired
    private WxPublicFileMapper wxPublicFileMapper;

    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);

    // 添加公众号
    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {


        // todo 开启事务
        // 保存公众号信息到数据库
        WxPublic wxPublic = new WxPublic(reqMap);
        Integer publicCode = wxPublicMapper.insert(wxPublic);
        // 保存文件到另一张表
        Integer fileCode = this.saveImg(publicHeadImg,publicQrcode,publicCode.toString());

        // 切换公众号
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(wxPublic.getAppId());
        wxConfigProvider.setSecret(wxPublic.getAppSerct());
        wxConfigProvider.setToken(wxPublic.getToken());
        wxConfigProvider.setAesKey(wxPublic.getAeskey());
        service.setWxMpConfigStorage(wxConfigProvider);

        return ResultUtil.success();
    }


    public int saveImg(MultipartFile publicHeadImg,MultipartFile publicQrcode,String publicCode) throws IOException {
        WxPublicFile wxPublicFile = new WxPublicFile(publicHeadImg.getBytes(),publicQrcode.getBytes(),publicCode);
        wxPublicFile.setFileCode(IdGen.uuid());
        return wxPublicFileMapper.insert(wxPublicFile);
    }

    // 获取某个公众号
    @GetMapping("/get")
    public Result getPublicAccount(@RequestParam String publicCode) throws WxErrorException, IOException{
        WxPublic wxPublic = wxPublicMapper.selectByPrimaryKey(publicCode);
        if (wxPublic == null){
            return  ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(wxPublic);
        }
    }

    // 获取所有公众号
    @GetMapping("/getAll")
    public Result getAllPublicAccount() throws WxErrorException, IOException{
        List<WxPublic> list = wxPublicMapper.selectAll();
        if (list.size() > 0){
            return ResultUtil.success(list);
        }else{
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }
    }

    // 编辑公众号p
    @PostMapping("/update")
    public Result updatePublicAccount(@RequestParam String publicCode,@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {
        WxPublic wxPublic = new WxPublic(reqMap);
        // 保存 publicCode
        wxPublic.setPublicCode(publicCode);
        // 保存公众号信息到数据库
        wxPublicMapper.updateByPrimaryKey(wxPublic);

        // 保存头像
        WxPublicFile wxPublicFile = new WxPublicFile(publicHeadImg.getBytes(),publicQrcode.getBytes(),publicCode);
        wxPublicFileMapper.updateByPrimaryKey(wxPublicFile);
        return ResultUtil.success();
    }

    // 解除绑定公众号
    @GetMapping("/delete")
    public Result deletePublicAccount(@RequestParam String wxPublicCode) throws WxErrorException, IOException{
        wxPublicMapper.deleteByPrimaryKey(wxPublicCode);
        return ResultUtil.success();
    }


}
