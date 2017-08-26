package com.demo.wechat.utils;

import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * spring 上传文件采用 MultipartFile 对象，而微信 sdk 是使用 File 对象，所以必须先把 MultipartFile -> File 对象，
 * Created by huangyg on 2017/8/26.
 */
public class FileUtil {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File transfer(MultipartFile multipartFile) throws IOException{
        // 获取上传文件后缀名
        String originFileName = multipartFile.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));

        // 新建临时文件
        File file = null;
        file = File.createTempFile("tmp", suffix);
        logger.info("上传的临时文件的路径：" + file.getAbsolutePath());

        // MultipartFile -> File
        multipartFile.transferTo(file);

        return file;
    }

}
