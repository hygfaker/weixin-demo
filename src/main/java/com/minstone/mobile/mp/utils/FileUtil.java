package com.minstone.mobile.mp.utils;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * spring 上传文件采用 MultipartFile 对象，而微信 sdk 是使用 File 对象，所以必须先把 MultipartFile -> File 对象，
 * Created by huangyg on 2017/8/26.
 */
public class FileUtil {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 将MultipartFile 转化成 File，该方法不会生成临时文件
     * @param multipartFile
     * @return java.io.File
     * @author huangyg
     */
    public static File convert(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * 将MultipartFile 转化成 File，该方法会生成临时文件
     * @param multipartFile
     * @return java.io.File
     * @author huangyg
     */
    public static File transfer(MultipartFile multipartFile) throws IOException{
        // 获取上传文件后缀名
        String originFileName = multipartFile.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));

        // 新建临时文件
        File file = null;
        file = File.createTempFile(multipartFile.getName(), suffix);
        logger.info("上传的临时文件的路径：" + file.getAbsolutePath());

        // MultipartFile -> File
        multipartFile.transferTo(file);

        return file;
    }

    /**
     * 读取流
     *
     * @param inputStream
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readStream(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[inputStream.available()];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            inputStream.read(buffer, 0, len);
        }
        inputStream.close();
        return buffer;
    }

    /**
     * 获取上传路径
     *
     * @return java.lang.String
     * @author huangyg
     */
    public static String uploadPath(){
        String uploadDir = "";
        try {
            uploadDir= ResourceUtils.getURL("classpath:").getPath() + "static" + File.separator + "upload"
                    + File.separator;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uploadDir;
    }

    /**
     * 获取文件 uuid + suffix名字
     *
     * @return java.lang.String
     * @author huangyg
     */
    public static String UUIDName(MultipartFile file){
        // 获取 uuid+文件格式名字
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String UUIDName = UUID.randomUUID() + suffix;
        return UUIDName;
    }






}
