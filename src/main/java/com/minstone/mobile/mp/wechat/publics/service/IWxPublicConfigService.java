package com.minstone.mobile.mp.wechat.publics.service;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 该服务主要提供公众号的配置，主要是：是否开启客服、
 * @author huangyg
 * @description
 * @since 2017/11/24
 */
public interface IWxPublicConfigService {

    /**
     * 添加公众号配置信息
     * @param publicConfig 公众号配置实体
     * @return com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig
     * @author huangyg
     */
    public String add(WxPublicConfig publicConfig) throws WxErrorException;

    /**
     * 修改公众号配置信息
     * @param publicConfig 公众号配置实体
     * @return boolean
     * @author huangyg
     */
    public boolean update(WxPublicConfig publicConfig) throws WxErrorException;

    /**
     * 获取公众号配置信息
     * @param publicConfig 公众号配置实体
     * @return com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig
     * @author huangyg
     */
    public WxPublicConfig get(WxPublicConfig publicConfig) throws WxErrorException;


    /** todo
     * 获取公众号某些功能的开启、关闭状态
     * @param publicConfig 公众号配置实体
     * @return java.lang.Integer
     * @author huangyg
     */
//    public Integer getFlag(WxPublicConfig publicConfig) throws WxErrorException;
}
