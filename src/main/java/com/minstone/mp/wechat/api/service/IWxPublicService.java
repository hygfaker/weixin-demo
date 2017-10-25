package com.minstone.mp.wechat.api.service;

import com.minstone.mp.wechat.common.CommonResult;
import com.minstone.mp.wechat.common.ResultEnum;
import com.minstone.mp.wechat.dao.WxPublicDao;
import com.minstone.mp.wechat.dao.WxPublicImgDao;
import com.minstone.mp.wechat.domain.WxPublic;
import com.minstone.mp.wechat.domain.WxPublicImg;
import com.minstone.mp.wechat.utils.ResultUtil;
import com.minstone.mp.wechat.utils.code.IdGen;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/9/20.
 *
 * 公众号管理操作 dao 层的 service
 *
 */
@Service
@Transactional
public class IWxPublicService {
    //todo 切换公众号
    @Autowired
    private WxPublicDao wxPublicDao;

    @Autowired
    private WxPublicImgDao wxPublicImgDao;

    /**
     *
     * @param reqMap 公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode  公众号二维码
     * @return
     * @throws WxErrorException
     * @throws IOException
     *
     * 添加公众号信息
     */
    public CommonResult addPublicAccount(Map<String, Object> reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException{

        // 保存公众号图片
        String imgCode = IdGen.uuid(); // 保存图片imgCode
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode,publicHeadImg.getBytes(),publicQrcode.getBytes());
        if (wxPublicImgDao.insert(wxPublicImg) > 0){
            // 保存公众号信息
            WxPublic wxPublic = new WxPublic(reqMap);
            wxPublic.setImgCode(imgCode);
            wxPublicDao.insert(wxPublic);
        }
        return ResultUtil.success();
    }


    /**
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     * @throws IOException
     *
     * 获取某个公众号信息
     */
    public WxPublic getPublicAccount(String publicCode) throws WxErrorException, IOException{
        return wxPublicDao.selectByPrimaryKey(publicCode);

    }

    /**
     *
     * @return
     * @throws WxErrorException
     * @throws IOException
     *
     * 获取所有公众号信息
     */
    public CommonResult getAllPublicAccount() throws WxErrorException, IOException{
        List<WxPublic> list = wxPublicDao.selectAll();
        if (list.size() > 0){
            return ResultUtil.success(list);
        }else{
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }
    }

    /**
     *
     * @param publicCode 公众号主键
     * @param reqMap 公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode 公众号二维码
     * @return
     * @throws WxErrorException
     * @throws IOException
     *
     * 更新公众号信息
     */
    public CommonResult updatePublicAccount(String publicCode, Map<String,Object>reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException{

        // 更新公众号图片
        String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(publicCode);
        if (imgCode == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }
        WxPublicImg wxPublicImg = new WxPublicImg(imgCode,publicHeadImg.getBytes(),publicQrcode.getBytes());
        if (wxPublicImgDao.updateByPrimaryKey(wxPublicImg) > 0){
            // 更新公众号信息
            WxPublic newPublic = new WxPublic(reqMap);
            newPublic.setPublicCode(publicCode);
            wxPublicDao.updateByPrimaryKey(newPublic);
        }
        return ResultUtil.success();
    }

    /**
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     * @throws IOException
     *
     * 删除某个公众号
     */
    public CommonResult deletePublicAccount(String  publicCode) throws WxErrorException, IOException{
        // 删除公众号图片
        String imgCode = wxPublicDao.selectImgCodeByPrimaryKey(publicCode);
        if (imgCode == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }
        if (wxPublicImgDao.deleteByPrimaryKey(imgCode) > 0){
            // 删除公众号信息
            wxPublicDao.deleteByPrimaryKey(publicCode);
        }
        return ResultUtil.success();
    }


    //todo 下载图片文件
    public byte[] icon(String imgCode,Integer imgType) throws WxErrorException, IOException{
        byte[] bs = null;
        if (imgType == 0){
            bs = wxPublicImgDao.selectHeadimgByImgCode(imgCode);
        }
        if (imgType == 1){
            bs = wxPublicImgDao.selectQrcodeByImgCode(imgCode);
        }
        return bs;
    }
}