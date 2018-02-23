package com.minstone.mobile.mp.wechat.publics.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicFileInfo;
import com.minstone.mobile.mp.wechat.publics.service.impl.WxPublicServiceImpl;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author huangyg
 * @description
 * @since 2017/8/13
 * <p>
 * WxMpConfigStorage 这个类主要维护微信公众号相关信息。有以下信息：appid、appsecret、token、aesencodekey、accesstoken
 * wechat.mp.appId = wxfab20b95598bfb32
 * wechat.mp.secret = 6ef63eee61e331fcd3cd12c5330c211b
 * wechat.mp.token = minstoneweixin
 * wechat.mp.aesKey = Gpavu82pzlG5fNyGB6gDC2hkR9ZRdiO6ADrMLwpPuN2
 */
@RestController
@RequestMapping("public")
public class WxPublicController {

    // TODO: 2017/10/26
    // 添加公众号

    // 逻辑删除公众号
    // 物理删除公众号
    // 批量逻辑删除公众号
    // 批量物理删除公众号

    // 修改公众号

    // 获取公众号
    // 分页获取公众号列表


    @Autowired
    private WxMpService service;

    @Autowired
    private WxPublicServiceImpl wxPublicService;

    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);

    // 添加公众号
    @PostMapping("/add")
    public CommonResult add(WxPublic wxPublic, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode,HttpServletRequest request) throws WxErrorException, IOException {
        return ResultUtil.success(wxPublicService.add(wxPublic, publicHeadImg, publicQrcode,request));
    }

    // 逻辑删除公众号
    @GetMapping("/delete")
    public CommonResult delete(WxPublic wxPublic) throws WxErrorException, IOException {
        if (wxPublicService.delete(wxPublic)) {
            return ResultUtil.success();
        } else {


            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 物理删除公众号
    @GetMapping("/forceDelete")
    public CommonResult forceDelete(WxPublic wxPublic) throws WxErrorException, IOException {

        if (wxPublicService.forceDelete(wxPublic)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 批量逻辑删除公众号
    @GetMapping("/deleteBatch")
    public CommonResult deleteBatch(WxPublic wxPublic) throws WxErrorException, IOException {
        // TODO: 2017/11/3 判断公众号是否存在 
        if (wxPublicService.deleteBatch(wxPublic)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 批量物理删除公众号
    @GetMapping("/forceDeleteBatch")
    public CommonResult forceDeleteBatch(WxPublic wxPublic) throws WxErrorException, IOException {
        if (wxPublicService.forceDeleteBatch(wxPublic)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 编辑公众号
    @PostMapping("/update")
    public CommonResult update(WxPublic wxPublic, @RequestParam MultipartFile publicHeadImg, @RequestParam MultipartFile publicQrcode) throws WxErrorException, IOException {
        if (wxPublicService.update(wxPublic,publicHeadImg,publicQrcode)){
            return ResultUtil.success();
        }else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }


    // 显示图片
    @RequestMapping("/file")
    public String file(){

        return"/file";

    }

    // 获取公众号的时候会切换公众号，此时相当于改变 accessToken
    @GetMapping("/get")
    public CommonResult get(WxPublic wxPublic, HttpServletRequest request) throws WxErrorException, IOException {

        // 切换公众号
        WxPublic selectWxPublic = wxPublicService.get(wxPublic.getPublicCode(),request);

        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(selectWxPublic.getAppId());
        wxConfigProvider.setSecret(selectWxPublic.getAppSecret());
        wxConfigProvider.setToken(selectWxPublic.getToken());
        wxConfigProvider.setAesKey(selectWxPublic.getAeskey());
        service.setWxMpConfigStorage(wxConfigProvider);

        return ResultUtil.success(selectWxPublic);
    }

    // 分页获取公众号列表
    @GetMapping("/getPage")
    public CommonResult getPage(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) throws WxErrorException, IOException {
        PageInfo page = wxPublicService.getPage(currentPage,pageSize);
        return ResultUtil.pageFormat(page);
    }


    // 测试
    @GetMapping("/test")
    public CommonResult test(WxPublic wxPublic) throws WxErrorException,IOException{
        List<String> result = wxPublicService.test(wxPublic);
        return ResultUtil.success(result);
    }

    // 下载公众号图标
    @GetMapping("/icon")
    public void downloadIcon(String imgCode, Integer imgType, HttpServletResponse response) throws WxErrorException, IOException {

        if (imgType != 0 && imgType != 1) {
            throw new CommonException(CommonResultEnum.IMG_NOT_NULL);
        }

        byte[] bs = wxPublicService.icon(imgCode, imgType);
        String fileName = "test.png";
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        response.setContentLength(bs.length);
        response.setContentType("application/octet-stream;charset=UTF-8");

        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        os.write(bs, 0, bs.length);
        os.flush();
        os.close();
    }

}
