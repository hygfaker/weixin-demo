package com.minstone.wechat.contoller;

import com.minstone.wechat.common.CommonResult;
import com.minstone.wechat.utils.FileUtil;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxMaterialController.class);

    @Autowired
    private WxMpService wxService;

    // 上传图文消息内的图片获取URL
    @PostMapping("/uploadImg")
    public CommonResult mediaImgUpload(@RequestParam MultipartFile img) throws WxErrorException, IOException {

        File file = FileUtil.transfer(img);
        WxMediaImgUploadResult result = this.wxService.getMaterialService().mediaImgUpload(file);
        // 上传成功后删除临时文件
        file.delete();
        return ResultUtil.success(result);
    }

    // 新增其他类型永久素材
    @PostMapping("/uploadMedia")
    public CommonResult uploadMedia(@RequestParam String mediaType,
                                    @RequestParam String name,
                                    @RequestParam(value = "file") MultipartFile multipartFile,
                                    @RequestParam(value = "title",required = false) String title,
                                    @RequestParam(value = "url",required = false) String url,
                                    @RequestParam(value = "introduction",required = false) String introduction) throws WxErrorException, IOException {

        File file = FileUtil.transfer(multipartFile);
        WxMpMaterial mpMaterial = new WxMpMaterial(name,file,title,introduction);
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialFileUpload(mediaType,mpMaterial);
        return ResultUtil.success(result);
    }

    // 新增永久图文素材
    @PostMapping("/uploadNews")
    public CommonResult uploadNews(@RequestBody WxMpMaterialNews news) throws WxErrorException {
        WxMpMaterialUploadResult result = this.wxService.getMaterialService().materialNewsUpload(news);
        return ResultUtil.success(result);
    }

    // 获取图文永久素材的信息
    @GetMapping("/news")
    public CommonResult materialNewsInfo(@RequestParam String mediaid) throws WxErrorException {
         return ResultUtil.success(this.wxService.getMaterialService().materialNewsInfo(mediaid));
    }

    // 获取声音或者图片素材的信息
    @GetMapping("/mediainfo")
    public CommonResult newsOrVoiceInfo(@RequestParam String mediaid) throws Exception{
        /* todo 应该建立数据库，将 mediaid、filename、mediatype、length 记录起来，防止每次都重新获取微信数据。并且，由于下载声音图片素材
           todo 的接口返回的是数据流，不包含文件名称等信息。所以需要额外调用获取素材列表的接口，然后匹配 mediaid 获取相应的信息。这样操作太
           todo 麻烦。
         */
        return ResultUtil.success();
    }

    // 下载声音或者图片素材
    @GetMapping("/media")
    public ResponseEntity<byte[]> newsOrVoiceDownload(@RequestParam String mediaid) throws Exception {
        InputStream inputStream = this.wxService.getMaterialService().materialImageOrVoiceDownload(mediaid);

        byte[] outputStream = FileUtil.readStream(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Length", String.valueOf(outputStream.length));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=x.png"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(outputStream,headers, HttpStatus.OK);

        return response;
    }

    // 下载视频永久素材的信息
    @GetMapping("/video")
    public CommonResult videoInfo(@RequestParam String mediaid) throws WxErrorException {
        return ResultUtil.success(this.wxService.getMaterialService().materialVideoInfo(mediaid));
    }

    /** 分页获取图文素材列表
     * @param offest 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     */
    @GetMapping("/newsList")
    public CommonResult getLigetNewsListst(@RequestParam int offest , @RequestParam int count) throws WxErrorException{
        WxMpMaterialNewsBatchGetResult result = this.wxService.getMaterialService().materialNewsBatchGet(offest,count);
        return ResultUtil.success(result);
    }

    /** 分页获取其他媒体素材列表
     * @param type   媒体素材的类型：news、voice、image、video
     * @param offest 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     */
    @GetMapping("/mediaList")
    public CommonResult getMediaList(@RequestParam String type, @RequestParam int offest , @RequestParam int count) throws WxErrorException{
        WxMpMaterialFileBatchGetResult result = this.wxService.getMaterialService().materialFileBatchGet(type,offest,count);
        return ResultUtil.success(result);
    }

    // 获取各类素材总数，包括公众平台上素材管理的素材
    @GetMapping("/allCount")
    public CommonResult getMaterial() throws WxErrorException {
        return ResultUtil.success(this.wxService.getMaterialService().materialCount());
    }
}
