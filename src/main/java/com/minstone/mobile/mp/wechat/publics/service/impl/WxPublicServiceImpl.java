package com.minstone.mobile.mp.wechat.publics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.*;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.DateUtil;
import com.minstone.mobile.mp.utils.FileUtil;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicFileInfoDao;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicFileInfo;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicDao;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicImgDao;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.utils.IdGen;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicImg;
import lombok.extern.log4j.Log4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangyg
 * @description 公众号管理操作 dao 层的 service
 * @since 2017/8/13
 */
@Service("publicService")
@Transactional
@Log4j
public class WxPublicServiceImpl implements IWxPublicService {
    @Autowired
    private WxPublicDao wxPublicDao;

    @Autowired
    private WxPublicImgDao wxPublicImgDao;

    @Value("${public_upload_path}")
    private String path;

    @Autowired
    private WxPublicFileInfoDao wxPublicFileInfoDao;

    @Autowired
    private Validator validator;

    /**
     * 添加公众号信息
     *
     * @param wxPublic      公众号实体
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return com.minstone.mobile.mp.common.CommonResult
     * @author huangyg
     */
    @Override
    public WxPublic add(WxPublic wxPublic, MultipartFile publicHeadImg, MultipartFile publicQrcode,HttpServletRequest request) throws IOException, CommonException {

        ValidatorUtil.mustParam(wxPublic, validator, "publicName", "publicNickname", "appId", "appSecret", "token", "aeskey", "url");

        // 记录图片到数据库
        String imgCode = IdGen.uuid();
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());

        if (wxPublicImgDao.insert(wxPublicImg) < 1) {
            throw new CommonException(CommonResultEnum.UPDATE_IMG_ERROR);
        }

        // 记录公众号信息
        String publicCode = IdGen.uuid();
        wxPublic.setImgCode(imgCode);
        wxPublic.setDelFlag(0);
        wxPublic.setPublicCode(publicCode);

        // 记录图片信息
        String qrcodeUrl = upload(publicCode, publicQrcode, 0);

        String headUrl = upload(publicCode, publicHeadImg, 1);


        if (qrcodeUrl == null) {
            throw new CommonException(CommonResultEnum.QRCODE_ERROR);
        }
        if (headUrl == null) {
            throw new CommonException(CommonResultEnum.HEAD_ERROR);
        }

        String requestUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getServletPath().length());
        qrcodeUrl = requestUrl + qrcodeUrl;
        headUrl = requestUrl + headUrl;

        wxPublic.setQrcodeUrl(qrcodeUrl);
        wxPublic.setHeadUrl(headUrl);
        if (wxPublicDao.insert(wxPublic) < 1) {
            throw new CommonException(CommonResultEnum.SAVE_PUBLIC_ERROR);
        }

        return wxPublic;
    }

    /**
     * 逻辑删除某个公众号
     *
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     */
    @Override
    public boolean delete(WxPublic wxPublic) throws WxErrorException, IOException {
        ValidatorUtil.mustParam(wxPublic, validator, "publicCode");
        // 校验公众号是否存在
        WxPublic checkPublic = wxPublicDao.selectPublicCode(wxPublic.getPublicCode());
        // 公众号存在于数据库的情况下
        if (checkPublic != null) {
            return wxPublicDao.deleteByPrimaryKey(wxPublic.getPublicCode()) > 0 ? true : false;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 批量逻辑删除某个公众号
     *
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     */
    @Override
    public boolean deleteBatch(WxPublic wxPublic) throws WxErrorException, IOException {
        ValidatorUtil.mustParam(wxPublic, validator, "publicCodes");
        // 校验公众号是否存在
        List<String> correctPublicCodes = wxPublicDao.selectPublicCodes(wxPublic.getPublicCodes());
        List<String> resultList = checkContains(correctPublicCodes.toArray(new String[correctPublicCodes.size()]), wxPublic.getPublicCodes());
        if (resultList.size() == 0) {
            return wxPublicDao.deleteBatch(wxPublic.getPublicCodes()) > 0 ? true : false;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 物理删除某个公众号
     *
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     */
    @Override
    public boolean forceDelete(WxPublic wxPublic) throws WxErrorException, IOException {
        ValidatorUtil.mustParam(wxPublic, validator, "publicCode");

        // 删除公众号图片
        WxPublic checkPublic = wxPublicDao.selectPublicCode(wxPublic.getPublicCode());
        // 公众号存在于数据库的情况下
        if (checkPublic != null) {
            return wxPublicDao.forceDeleteByPrimaryKey(wxPublic.getPublicCode()) > 0 ? true : false;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 批量物理删除某个公众号
     *
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     */
    @Override
    public boolean forceDeleteBatch(WxPublic wxPublic) throws WxErrorException, IOException {
        ValidatorUtil.mustParam(wxPublic, validator, "publicCodes");
        // 校验公众号是否存在
        List<String> correctPublicCodes = wxPublicDao.selectPublicCodes(wxPublic.getPublicCodes());
        List<String> resultList = checkContains(correctPublicCodes.toArray(new String[correctPublicCodes.size()]), wxPublic.getPublicCodes());

        if (resultList.size() == 0) {
            return wxPublicDao.forceDeleteBatch(wxPublic.getPublicCodes()) > 0 ? true : false;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 更新公众号信息
     *
     * @param wxPublic      公众号实体
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return int
     * @author huangyg
     */
    @Override
    public boolean update(WxPublic wxPublic, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException {
        ValidatorUtil.mustParam(wxPublic, validator, "publicCode");
        // 校验公众号是否存在
        WxPublic checkPublic = wxPublicDao.selectPublicCode(wxPublic.getPublicCode());
        // 公众号存在于数据库的情况下
        if (checkPublic != null) {
            String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(wxPublic.getPublicCode());
            if (imgCode != null) {
                WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());
                if (wxPublicImgDao.updateByPrimaryKeySelective(wxPublicImg) > 0) {
                    // 更新公众号信息
                    return wxPublicDao.updateByPrimaryKeySelective(wxPublic) > 0 ? true : false;
                } else {
                    throw new CommonException(CommonResultEnum.UPDATE_IMG_ERROR);
                }
            } else {
                throw new CommonException(CommonResultEnum.PUBLIC_IMG_NOTFOUND);
            }
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 获取某个公众号信息
     *
     * @param publicCode 公众号实体
     * @param request    请求实体
     * @author huangyg
     */
    @Override
    public WxPublic get(String publicCode, HttpServletRequest request) throws WxErrorException, IOException {

        WxPublic selectResult = wxPublicDao.selectPublicCode(publicCode);

        if (selectResult != null) {
            return selectResult;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }

    }

    /**
     * 获取某个公众号信息
     *
     * @param publicCode 公众号
     * @author huangyg
     */
    @Override
    public WxPublic get(String publicCode) throws WxErrorException, IOException {
        WxPublic selectResult = wxPublicDao.selectPublicCode(publicCode);
        if (selectResult != null) {
            return selectResult;
        } else {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
    }

    // 获取
    public byte[] icon(String imgCode, Integer imgType) throws WxErrorException, IOException {
        byte[] bs = null;
        if (imgType == 0) {
            bs = wxPublicImgDao.selectHeadimgByImgCode(imgCode);
        }
        if (imgType == 1) {
            bs = wxPublicImgDao.selectQrcodeByImgCode(imgCode);
        }
        return bs;
    }

//    // 下载图片文件
//    public byte[] icon(String imgCode, Integer imgType) throws WxErrorException, IOException {
//        byte[] bs = null;
//        if (imgType == 0) {
//            bs = wxPublicImgDao.selectHeadimgByImgCode(imgCode);
//        }
//        if (imgType == 1) {
//            bs = wxPublicImgDao.selectQrcodeByImgCode(imgCode);
//        }
//        return bs;
//    }


    /**
     * 分页获取公众号信息
     *
     * @param currentPage 当前页
     * @param pageSize    每页显示的数量
     * @return com.github.pagehelper.PageInfo<com.minstone.mobile.mp.wechat.publics.reply.WxPublic> 分页内容
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<WxPublic> getPage(int currentPage, int pageSize) throws WxErrorException, IOException {

        if (currentPage < 0) {
            throw new CommonException((CommonResultEnum.PARAME_LIMITE_POSITIVE));
        }
        if (pageSize < 0) {
            throw new CommonException((CommonResultEnum.PARAME_LIMITE_POSITIVE));
        }
        PageHelper.startPage(currentPage, pageSize);
        List<WxPublic> list = wxPublicDao.selectAll();
        PageInfo<WxPublic> page = new PageInfo<>(list);
        return page;
    }

//    /**
//     * 获取图片 url
//     *
//     * @param publicCode 公众号主键
//     * @param type       类型
//     * @return com.github.pagehelper.PageInfo<com.minstone.mobile.mp.wechat.publics.reply.WxPublic> 分页内容
//     * @author huangyg
//     */
//    @Deprecated
//    @Override
//    public List<WxPublicFileInfo> getUrl(String publicCode, int type) throws WxErrorException, IOException {
//        // 判断 publicCode 是否存在
//        WxPublic selectResult = wxPublicDao.selectPublicCode(publicCode);
//        if (selectResult == null) {
//            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
//        }
//        return wxPublicFileInfoDao.selectByPublicCode(publicCode,type);
//    }


    /**
     * 当文件上传
     *
     * @param publicCode 公众号
     * @param file       文件
     * @author huangyg
     */
//    @Override
    private String upload(String publicCode, MultipartFile file, int type) throws IOException {

        if (file == null) {
            throw new CommonException(CommonResultEnum.IMG_NOT_NULL);
        }
//        String uploadDir = FileUtil.uploadPath() + path;
        String uploadDir = this.path + File.separator;
        return executeUpload(uploadDir, file, type, publicCode);
    }

    private String executeUpload(String uploadDir, MultipartFile file, int type, String publicCode) throws IOException {

        // 获取文件的 md5name
        String md5Name = FileUtil.MD5Name(file);

        // 存在数据，判断是否重复
        List<WxPublicFileInfo> publicFileInfos = wxPublicFileInfoDao.selectByPublicCode(publicCode, type);
        if (publicFileInfos.size() > 0) {
            for (WxPublicFileInfo temp : publicFileInfos) {
                if (temp.getSignature().equals(md5Name)) {
                    throw new CommonException(CommonResultEnum.FILE_EXIST);
                }
            }
        }

        WxPublicFileInfo insertInfo = new WxPublicFileInfo();
        insertInfo.setPubFileCode(IdGen.uuid());
        insertInfo.setPublicCode(publicCode);
        insertInfo.setSuffix(FileUtil.getFileType(file.getOriginalFilename()));
        insertInfo.setType(type);
        insertInfo.setPath(path);
        insertInfo.setName(file.getOriginalFilename());
        insertInfo.setSignature(md5Name);
        insertInfo.setSize((double) file.getSize());
        insertInfo.setDelFlag(0);
        insertInfo.setCreateDate(DateUtil.getStringDate());

        log.info("upload url: " + uploadDir + md5Name);

        File serverFile = new File(uploadDir + md5Name);
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        file.transferTo(serverFile);


        // 保存信息
        if (wxPublicFileInfoDao.insert(insertInfo) > 0) {
            return File.separator + md5Name;
        } else {
            return "";
        }
    }


    public static <T> List<T> checkContains(T[] t1, T[] t2) {
        List<T> checkList = Arrays.asList(t1);
        List<T> resultList = new ArrayList<T>();
        for (T t : t2) {
            if (!checkList.contains(t)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    @Override
    public List<String> test(WxPublic wxPublic) throws WxErrorException, IOException {
        return wxPublicDao.test(wxPublic);
    }

    @Override
    public String getPublicCodeByOpenId(String openId) throws WxErrorException {
        List<String> publicCodes = wxPublicDao.selectPublicCodeByOpenId(openId);
        return publicCodes.size() > 0 ? publicCodes.get(0).toString() : null;
    }

    @Override
    public WxMpInMemoryConfigStorage switchPublic(WxPublic wxPublic) throws WxErrorException {
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(wxPublic.getAppId());
        wxConfigProvider.setSecret(wxPublic.getAppSecret());
        wxConfigProvider.setToken(wxPublic.getToken());
        wxConfigProvider.setAesKey(wxPublic.getAeskey());
        return wxConfigProvider;
    }

    @Override
    public WxPublic selectByOpenId(String openId) {
        return wxPublicDao.selectByOpenId(openId);
    }
}