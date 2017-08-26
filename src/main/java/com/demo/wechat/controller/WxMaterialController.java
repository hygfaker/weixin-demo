package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.FileUtil;
import com.demo.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    // 上传图文消息内的图片获取URL
    @PostMapping("/uploadImg")
    public Result mediaImgUpload(@RequestParam MultipartFile img) throws WxErrorException, IOException {

        File file = FileUtil.transfer(img);
        WxMediaImgUploadResult result = this.wxService.getMaterialService().mediaImgUpload(file);
        // 上传成功后删除临时文件
        file.delete();
        return ResultUtil.success(result);
    }

    // 新增其他类型永久素材
    @PostMapping("/uploadMedia")
    public Result uploadMedia(@RequestParam String mediaType,
                              @RequestParam String name,
                              @RequestParam(value = "file") MultipartFile multipartFile,
                              @RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "introduction",required = false) String introduction) throws WxErrorException, IOException {

        File file = FileUtil.transfer(multipartFile);
        WxMpMaterial mpMaterial = new WxMpMaterial(name,file,title,introduction);
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialFileUpload(mediaType,mpMaterial);
        return ResultUtil.success(result);
    }

    // 新增永久图文素材
    @PostMapping("/uploadNews")
    public Result uploadNews(@RequestBody WxMpMaterialNews news) throws WxErrorException {
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialNewsUpload(news);
        return ResultUtil.success(result);
    }

    // 获取图文永久素材的信息
    @GetMapping("/newsDetail")
    public Result materialNewsInfo(@RequestParam String mediaid) throws WxErrorException {
         return ResultUtil.success(this.wxService.getMaterialService().materialNewsInfo(mediaid));
    }

    // 下载声音或者图片
    @GetMapping("/news")
    public ResponseEntity<InputStreamResource> newsOrVoiceDownload(@RequestParam String mediaid) throws WxErrorException {
        InputStream inputStream = this.wxService.getMaterialService().materialImageOrVoiceDownload(mediaid);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=x"));
        headers.add("Content-Length",inputStream.);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(inputStream));

    }

    // 下载视频永久素材的信息
    @GetMapping("/video")
    public Result videoInfo(@RequestParam String mediaid) throws WxErrorException {
        return ResultUtil.success(this.wxService.getMaterialService().materialVideoInfo(mediaid));
    }

    // 获取图文永久素材的信息
    @GetMapping("/newsDetail")
    public Result getMaterialNewsInfo(@RequestParam String mediaid) throws WxErrorException{
        WxMpMaterialNews news = this.wxService.getMaterialService().materialNewsInfo(mediaid);
        return ResultUtil.success(news);
    }

    /** 分页获取图文素材列表
     * @param offest 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     */
    @GetMapping("/newsList")
    public Result getLigetNewsListst(@RequestParam int offest , @RequestParam int count) throws WxErrorException{
        WxMpMaterialNewsBatchGetResult result = this.wxService.getMaterialService().materialNewsBatchGet(offest,count);
        return ResultUtil.success(result);
    }

    /** 分页获取其他媒体素材列表
     * @param type   媒体素材的类型：news、voice、image、video
     * @param offest 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     */
    @GetMapping("/mediaList")
    public Result getMediaList(@RequestParam String type,@RequestParam int offest , @RequestParam int count) throws WxErrorException{
        WxMpMaterialFileBatchGetResult result = this.wxService.getMaterialService().materialFileBatchGet(type,offest,count);
        return ResultUtil.success(result);
    }

    // 获取各类素材总数，包括公众平台上素材管理的素材
    @GetMapping("/allCount")
    public Result getMaterial() throws WxErrorException {
        return ResultUtil.success(this.wxService.getMaterialService().materialCount());
    }
}
