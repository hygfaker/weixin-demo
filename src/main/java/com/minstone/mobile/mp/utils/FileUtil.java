package com.minstone.mobile.mp.utils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public static String uploadPath()  {
        String uploadDir = "";
        try {
            uploadDir= ResourceUtils.getURL("classpath:").getPath() ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return uploadDir;
    }

    /**
     * 获取上传的 url
     *
     * @return java.lang.String
     * @author huangyg
     */
    public static String getUrl(String dir,String path,String name )  {
        return  dir + path + File.separator + name;
    }


    /**
     * 获取文件 md5加密 + suffix名字
     *
     * @return java.lang.String
     * @author huangyg
     */
    public static String MD5Name(MultipartFile file) throws IOException {
        // 获取 uuid+文件格式名字
        String suffix = getFileType(file.getOriginalFilename());
        String md5name = getMD5(file) + suffix;
        logger.info("md5加密后的名字：" + md5name);
        return md5name;
    }

    /**
     * 获取文件类型
     */
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }


    public static byte[] getByte(File file){
        // 得到文件长度
        byte[] b = new byte[(int) file.length()];
        try {
            InputStream in = new FileInputStream(file);
            try {
                in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return b;
    }

    public static String getMD5(byte[] bytes) {
        // 16进制字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

            byte[] strTemp = bytes;
        MessageDigest mdTemp = null;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            // 移位 输出字符串
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);

    }

    public static String getMD5(MultipartFile file) throws IOException {
        return getMD5(file.getBytes());
    }

}
