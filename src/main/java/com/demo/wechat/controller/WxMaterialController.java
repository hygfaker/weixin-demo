package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by huangyg on 2017/8/17.
 */
@RestController
@RequestMapping("/material")
public class WxMaterialController {

    /*todo
    * 新增永久，永久素材分为：图文素材、图片素材、视频素材、音频素材等等。其中图文素材较为复杂。
    * 获取永久
    * 删除永久
    * 修改永久
    * 获取素材总数
    * 获取素材列表
    * */

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxMaterialController.class);

    @Autowired
    private WxMpService wxService;

    // 新增新增永久图文素材
    @PostMapping("/uploadNews")
    public Result uploadMaterialNews(@RequestParam WxMpMaterialNews news) throws WxErrorException {
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialNewsUpload(news);
        return ResultUtil.success();
    }

    // 获取永久素材
    @GetMapping("/getNewsInfo")
    public Result getMaterialNewsInfo(@RequestParam String mediaid) throws WxErrorException{
        WxMpMaterialNews news = this.wxService.getMaterialService().materialNewsInfo(mediaid);
        return ResultUtil.success();
    }

    // 分页获取图文素材列表
    @GetMapping("/getList")
    public Result getList(@RequestParam int offest , @RequestParam int count) throws WxErrorException{
        WxMpMaterialNewsBatchGetResult result = this.wxService.getMaterialService().materialNewsBatchGet(offest,count);
        return ResultUtil.success(result);
    }

    // 上传图文消息内的图片获取URL
    @PostMapping("/uploadImg")
    public Result mediaImgUpload(@RequestParam MultipartFile img) throws WxErrorException, IOException {
        // spring 上传文件采用 MultipartFile 对象，而微信 sdk 是使用 File 对象，所以必须先把 MultipartFile -> File 对象，

        // 获取上传文件后缀名
        String originFileName = img.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));

        // 新建临时文件
        File file = null;
        file = File.createTempFile("tmp", suffix);
        logger.info("上传的临时文件的路径：" + file.getAbsolutePath());

        // MultipartFile -> File
        img.transferTo(file);
        WxMediaImgUploadResult result = this.wxService.getMaterialService().mediaImgUpload(file);

        // 上传成功后删除临时文件
        file.delete();
        return ResultUtil.success(result);
    }

    // 新增非图文永久素材
    @PostMapping("/mediaUpload")
    public Result materialFileUpload(@RequestParam String mediaType, @RequestParam WxMpMaterial material)throws  WxErrorException{
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialFileUpload(mediaType,material);
        return ResultUtil.success();
    }

}
