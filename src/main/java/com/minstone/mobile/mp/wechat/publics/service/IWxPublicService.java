package com.minstone.mobile.mp.wechat.publics.service;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/9/20.
 *
 * 对外提供的公众号管理的接口
 */

public interface IWxPublicService {

    // TODO: 2017/10/26
    // 添加公众号

    // 逻辑删除公众号
    // 物理删除公众号
    // 批量逻辑删除公众号
    // 批量物理删除公众号

    // 修改公众号

    // 获取公众号
    // 获取公众号分页


    // TODO: 2017/10/27
    /**
     * 添加公众号信息
     * @param wxPublic 公众号信息
     * @param publicHeadImg 公众号头像
     * @param publicQrcode 公众号二维码
     * @return com.minstone.mobile.mp.common.CommonResult
     * @author huangyg
     */
//    public void add(Map<String, Object> reqMap, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException;

    public WxPublic add(WxPublic wxPublic, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException;
    /**
     * 逻辑删除某个公众号
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     *
     */
    public boolean delete(WxPublic wxPublic) throws WxErrorException, IOException;

    /**
     * 物理删除某个公众号
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     *
     */
    public boolean forceDelete(WxPublic wxPublic) throws WxErrorException, IOException;

    /**
     * 逻辑删除某个公众号
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     *
     */
    public boolean deleteBatch(WxPublic wxPublic) throws WxErrorException, IOException;

    /**
     * 物理删除某个公众号
     * @param wxPublic 公众号实体
     * @return int
     * @author huangyg
     *
     */
    public boolean forceDeleteBatch(WxPublic wxPublic) throws WxErrorException, IOException;

    /**
     * 更新公众号信息
     * @param wxPublic 公众号实体
     * @param publicHeadImg 公众号头像
     * @param publicQrcode 公众号二维码
     * @return int
     * @author huangyg
     */
    public boolean update(WxPublic wxPublic, MultipartFile publicHeadImg, MultipartFile publicQrcode) throws WxErrorException, IOException;

    /**
     * 获取某个公众号信息
     * @param wxPublic 公众号实体
     *
     * @author huangyg
     */
    public WxPublic get(WxPublic wxPublic) throws WxErrorException, IOException;

    /**
     * 获取某个公众号信息
     * @param publicCode 公众号
     *
     * @author huangyg
     */
    public WxPublic get(String publicCode) throws WxErrorException, IOException;

    /**
     * 分页获取公众号信息
     * @param 
     * @return com.github.pagehelper.PageInfo<com.minstone.mobile.mp.wechat.publics.reply.WxPublic> 分页内容
     * @author huangyg
     */
    public PageInfo<WxPublic> getPage(int currentPage,int pageSize) throws WxErrorException, IOException;

    /**
     * 上传单个文件
     * @param publicCode 公众号
     * @param file 头像
     *
     * @author huangyg
     */
    public boolean upload(String publicCode,MultipartFile file) throws WxErrorException, IOException;

    /**
     * 批量上传文件
     *
     * @param publicCode 公众号
     * @param files      文件
     * @author huangyg
     */
    public boolean uploads(String publicCode, MultipartFile[] files) throws WxErrorException, IOException ;


    public List<String> test(WxPublic wxPublic) throws WxErrorException,IOException ;

    /**
     * 根据公众号原始 id 获取公众号 publicCode
     * @param openId 公众号原始 id
     * @return java.lang.String
     * @author huangyg
     */
    public String getPublicCodeByOpenId(String openId) throws WxErrorException;

    /**
     * 切换公众号
     * @param wxPublic 公众号实体
     * @return void
     * @author huangyg
     */
    public WxMpInMemoryConfigStorage switchPublic(WxPublic wxPublic) throws WxErrorException;

    public WxPublic selectByOpenId(String openId);

}
