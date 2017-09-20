package com.minstone.wechat.api.imp;

import com.minstone.wechat.api.WxPublicService;
import com.minstone.wechat.domain.WxPublic;
import com.minstone.wechat.domain.WxPublicImg;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.mapper.WxPublicImgMapper;
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
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@RequestMapping("public")
public class WxPublicController implements WxPublicService{

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
    private WxPublicImgMapper wxPublicImgMapper;

    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);

    // 添加公众号
    @PostMapping("/add")
    public Result addPublicAccount(@RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {


        // todo 开启事务
        // 保存公众号图片
        String imgCode = IdGen.uuid(); // 保存图片imgCode
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode,publicHeadImg.getBytes(),publicQrcode.getBytes());
        wxPublicImgMapper.insert(wxPublicImg);

        // 保存公众号信息
        WxPublic wxPublic = new WxPublic(reqMap);
        wxPublic.setImgCode(imgCode);
        wxPublicMapper.insert(wxPublic);
        // 切换公众号
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(wxPublic.getAppId());
        wxConfigProvider.setSecret(wxPublic.getAppSerct());
        wxConfigProvider.setToken(wxPublic.getToken());
        wxConfigProvider.setAesKey(wxPublic.getAeskey());
        service.setWxMpConfigStorage(wxConfigProvider);

        return ResultUtil.success();
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
    public Result updatePublicAccount(@RequestParam String publicCode, @RequestParam Map<String,Object>reqMap, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {

        // 更新公众号图片
        String imgCode = wxPublicMapper.selectImgCodeByPrimaryKey(publicCode);
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode,publicHeadImg.getBytes(),publicQrcode.getBytes());
        wxPublicImgMapper.updateByPrimaryKey(wxPublicImg);

        // 更新公众号信息
        WxPublic newPublic = new WxPublic(reqMap);
        newPublic.setPublicCode(publicCode);
        wxPublicMapper.updateByPrimaryKey(newPublic);

        return ResultUtil.success();
    }

    // 解除绑定公众号
    @GetMapping("/delete")
    public Result deletePublicAccount(@RequestParam String  publicCode) throws WxErrorException, IOException{

        // 删除公众号图片
        String imgCode = wxPublicMapper.selectImgCodeByPrimaryKey(publicCode);
        wxPublicImgMapper.deleteByPrimaryKey(imgCode);

        // 删除公众号信息
        wxPublicMapper.deleteByPrimaryKey(publicCode);
        return ResultUtil.success();
    }


}
