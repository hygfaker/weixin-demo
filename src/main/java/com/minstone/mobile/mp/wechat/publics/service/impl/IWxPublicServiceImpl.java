package com.minstone.mobile.mp.wechat.publics.service.impl;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.DaoEnum;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.ResultEnum;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicDao;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicImgDao;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicImg;
import com.sun.org.apache.xpath.internal.operations.Bool;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/9/20.
 * <p>
 * 公众号管理操作 dao 层的 service
 */
@Service
@Transactional
public class IWxPublicServiceImpl implements IWxPublicService {
    //todo 切换公众号
    @Autowired
    private WxPublicDao wxPublicDao;

    @Autowired
    private WxPublicImgDao wxPublicImgDao;

    private static Logger logger = LoggerFactory.getLogger(IWxPublicServiceImpl.class);

    /**
     * 添加公众号信息
     *
     * @param reqMap        公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return com.minstone.mobile.mp.common.CommonResult
     * @author huangyg
     */
    @Override
    public WxPublic add(Map<String, Object> reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException {

        String imgCode = IdGen.uuid();
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());
        // 保存公众号图片
        if (wxPublicImgDao.insert(wxPublicImg) > 0) {
            // 保存公众号信息
            WxPublic wxPublic = new WxPublic(reqMap);
            wxPublic.setImgCode(imgCode);
            if (wxPublicDao.insert(wxPublic) > 0) {
                logger.info("添加公众号信息成功");
                return wxPublic;
            } else {
                logger.error("保存公众号出错");
                throw new CommonException(ResultEnum.SERVER_ERROR, "保存公众号出错");
            }
        } else {
            logger.error("保存公众号图片出错");
            throw new CommonException(ResultEnum.SERVER_ERROR, "保存公众号图片出错");
        }
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
        // 删除公众号图片
        String publicCode = wxPublic.getPublicCode();
        String checkPublicCode = wxPublicDao.selectPublicCode(publicCode);
        // 公众号存在于数据库的情况下
        if (checkPublicCode != null) {
            String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(publicCode);
            // 公众号图片存在于数据库的情况下
            if (imgCode != null) {
                if (wxPublicImgDao.deleteByPrimaryKey(imgCode) > 0) {
                    // 删除公众号信息
                    return wxPublicDao.deleteByPrimaryKey(publicCode) > 0 ? true : false;
                } else {
                    logger.error(DaoEnum.FORCE_DELETE_IMG.getMessage());
                    throw new CommonException(DaoEnum.FORCE_DELETE_IMG);
                }
            } else {
                logger.error(DaoEnum.PUBLIC_IMG_NOTFOUND.getMessage());
                throw new CommonException(DaoEnum.PUBLIC_IMG_NOTFOUND);
            }
        } else {
            logger.error(DaoEnum.PUBLIC_NOTFOUND.getMessage());
            throw new CommonException(DaoEnum.PUBLIC_NOTFOUND);
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
        // 删除公众号图片
        String publicCode = wxPublic.getPublicCode();
        String checkPublicCode = wxPublicDao.selectPublicCode(publicCode);
        // 公众号存在于数据库的情况下
        if (checkPublicCode != null) {
            String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(publicCode);
            // 公众号图片存在于数据库的情况下
            if (imgCode != null) {
                if (wxPublicImgDao.forceDeleteByPrimaryKey(imgCode) > 0) {
                    // 删除公众号信息
                    return wxPublicDao.forceDeleteByPrimaryKey(publicCode) > 0 ? true : false;
                } else {
                    logger.error(DaoEnum.FORCE_DELETE_IMG.getMessage());
                    throw new CommonException(DaoEnum.FORCE_DELETE_IMG);
                }
            } else {
                logger.error(DaoEnum.PUBLIC_IMG_NOTFOUND.getMessage());
                throw new CommonException(DaoEnum.PUBLIC_IMG_NOTFOUND);
            }
        } else {
            logger.error(DaoEnum.PUBLIC_NOTFOUND.getMessage());
            throw new CommonException(DaoEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 批量逻辑删除某个公众号
     *
     * @param wxPublics 公众号实体数组
     * @return int
     * @author huangyg
     */
    @Override
    public boolean deleteBatch(List<WxPublic> wxPublics) throws WxErrorException, IOException {
        return true;
    }

    /**
     * 批量物理删除某个公众号
     *
     * @param wxPublics 公众号实体数组
     * @return int
     * @author huangyg
     */
    @Override
    public boolean forceDeleteBatch(List<WxPublic> wxPublics) throws WxErrorException, IOException {
        return false;
    }


    /**
     * @param publicCode    公众号主键
     * @param reqMap        公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return
     * @throws WxErrorException
     * @throws IOException      更新公众号信息
     */
    public CommonResult updatePublicAccount(String publicCode, Map<String, Object> reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException {
        // 更新公众号图片
        String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(publicCode);
        if (imgCode == null) {
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());
        if (wxPublicImgDao.updateByPrimaryKey(wxPublicImg) > 0) {
            // 更新公众号信息
            WxPublic newPublic = new WxPublic(reqMap);
            newPublic.setPublicCode(publicCode);
            wxPublicDao.updateByPrimaryKey(newPublic);
        }
        return ResultUtil.success();
    }


    //todo 下载图片文件
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




    /**
     * 更新公众号信息
     *
     * @param publicCode    公众号主键
     * @param reqMap        公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return int
     * @author huangyg
     */
    @Override
    public int update(String publicCode, Map<String, Object> reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException {
        return 0;
    }

    /**
     * 获取某个公众号信息
     *
     * @param wxPublic 公众号实体
     * @author huangyg
     */
    @Override
    public WxPublic get(WxPublic wxPublic) throws WxErrorException, IOException {
        WxPublic selectResult = wxPublicDao.selectByPrimaryKey(wxPublic.getPublicCode());
        if (selectResult != null) {
            return selectResult;
        } else {
            logger.error(DaoEnum.PUBLIC_NOTFOUND.getMessage());
            throw new CommonException(DaoEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 分页获取公众号信息
     *
     * @return com.github.pagehelper.PageInfo<com.minstone.mobile.mp.wechat.publics.domain.WxPublic> 分页内容
     * @author huangyg
     */
    @Override
    public PageInfo<WxPublic> getPage() throws WxErrorException, IOException {
        return null;
    }


    public static void main(String[] args) {

    }

}