package com.minstone.mobile.mp.wechat.publics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.*;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicDao;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicImgDao;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicImg;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangyg
 * @description 公众号管理操作 dao 层的 service
 * @since 2017/8/13
 */
@Service
@Transactional
public class WxPublicServiceImpl implements IWxPublicService {
    //todo 切换公众号
    @Autowired
    private WxPublicDao wxPublicDao;

    @Autowired
    private WxPublicImgDao wxPublicImgDao;

    @Autowired
    private Validator validator;

    private static Logger logger = LoggerFactory.getLogger(WxPublicServiceImpl.class);

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
    public WxPublic add(WxPublic wxPublic, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException {
        // TODO: 2017/11/7 对 wxpublic 实体进行参数校验
        // 生成图片的主键
        String imgCode = IdGen.uuid();
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());
        // 保存公众号图片导数据库
        if (wxPublicImgDao.insert(wxPublicImg) > 0) {
            // 保存公众号信息
            wxPublic.setImgCode(imgCode);
            // 默认设置为不删除标志
            wxPublic.setDelFlag(DeleteFlagEnum.NOT_DELETE.getDelFlag());
            // 生成公众号主键
            String publicCode = IdGen.uuid();
            wxPublic.setPublicCode(publicCode);
            if (wxPublicDao.insert(wxPublic) > 0) {
                return wxPublic;
            } else {
                throw new CommonException(ResultEnum.SAVE_PUBLIC_ERROR);
            }
        } else {
            throw new CommonException(ResultEnum.UPDATE_IMG_ERROR);
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
        ValidatorUtil.param(wxPublic, validator, "publicCode");
        // 校验公众号是否存在
        List<String> checkPublicCodes = wxPublicDao.selectPublicCode(wxPublic);
        // 公众号存在于数据库的情况下
        if (checkPublicCodes.size() > 0) {
            return wxPublicDao.deleteByPrimaryKey(wxPublic.getPublicCode()) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
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
        ValidatorUtil.param(wxPublic, validator, "publicCodes");
        // 校验公众号是否存在
        List<String> correctPublicCodes = wxPublicDao.selectPublicCodes(wxPublic.getPublicCodes());
        List<String> resultList = checkContains(correctPublicCodes.toArray(new String[correctPublicCodes.size()]),wxPublic.getPublicCodes());
        if (resultList.size() == 0){
            return wxPublicDao.deleteBatch(wxPublic.getPublicCodes()) > 0 ? true : false;
        }else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
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
        ValidatorUtil.param(wxPublic, validator, "publicCode");

        // 删除公众号图片
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(wxPublic);
        // 公众号存在于数据库的情况下
        if (checkPublicCode != null) {
            return wxPublicDao.forceDeleteByPrimaryKey(wxPublic.getPublicCode()) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
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
        ValidatorUtil.param(wxPublic, validator, "publicCodes");
        // 校验公众号是否存在
        List<String> correctPublicCodes = wxPublicDao.selectPublicCodes(wxPublic.getPublicCodes());
        List<String> resultList = checkContains(correctPublicCodes.toArray(new String[correctPublicCodes.size()]),wxPublic.getPublicCodes());

        if (resultList.size() == 0){
            return wxPublicDao.forceDeleteBatch(wxPublic.getPublicCodes()) > 0 ? true : false;
        }else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
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
        ValidatorUtil.param(wxPublic,validator,"publicCode");
        // 校验公众号是否存在
        List<String> checkPublicCodes = wxPublicDao.selectPublicCode(wxPublic);
        // 公众号存在于数据库的情况下
        if (checkPublicCodes.size() > 0) {
            String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(wxPublic.getPublicCode());
            if (imgCode != null ) {
                WxPublicImg wxPublicImg = new WxPublicImg(imgCode, publicHeadImg.getBytes(), publicQrcode.getBytes());
                if (wxPublicImgDao.updateByPrimaryKeySelective(wxPublicImg) > 0) {
                    // 更新公众号信息
                    return wxPublicDao.updateByPrimaryKeySelective(wxPublic) > 0 ? true : false;
                } else {
                    throw new CommonException(ResultEnum.UPDATE_IMG_ERROR);
                }
            } else {
                throw new CommonException(ResultEnum.PUBLIC_IMG_NOTFOUND);
            }
        }else{
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        }
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
     * 获取某个公众号信息
     *
     * @param wxPublic 公众号实体
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public WxPublic get(WxPublic wxPublic) throws WxErrorException, IOException {
        // TODO: 2017/11/2 校验 wxpublic 参数中的 publicCodes 数组
        WxPublic selectResult = wxPublicDao.selectByPrimaryKey(wxPublic);
        if (selectResult != null) {
            return selectResult;
        } else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        }
    }

    /**
     * 分页获取公众号信息
     *
     * @param wxPublic    公众号实体
     * @param currentPage 当前页
     * @param pageSize    每页显示的数量
     * @return com.github.pagehelper.PageInfo<com.minstone.mobile.mp.wechat.publics.domain.WxPublic> 分页内容
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<WxPublic> getPage(WxPublic wxPublic, int currentPage, int pageSize) throws WxErrorException, IOException {

        ValidatorUtil.param(wxPublic,validator,"publicCode");
        if (currentPage < 0) {
            throw new CommonException((ResultEnum.PARAME_LIMITE_POSITIVE));
        }
        if (pageSize < 0) {
            throw new CommonException((ResultEnum.PARAME_LIMITE_POSITIVE));
        }
        // 检查公众号是否存在
        List<String> checkPublicCodes = wxPublicDao.selectPublicCode(wxPublic);
        if (checkPublicCodes.size() == 0) {
            PageHelper.startPage(currentPage, pageSize);
            List<WxPublic> list = wxPublicDao.selectAll();
            PageInfo<WxPublic> page = new PageInfo<>(list);
            return page;

        } else {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);

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

}