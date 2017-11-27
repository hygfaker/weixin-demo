package com.minstone.mobile.mp.wechat.config.service.impl;

import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.config.dao.WxPublicConfigDao;
import com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.config.service.IWxPublicConfigService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;


/**
 * @author huangyg
 * @description
 * @since 2017/11/24
 */
@Transactional
@Service
@Slf4j
public class WxPublicConfigServiceImpl implements IWxPublicConfigService {

    @Autowired
    private WxPublicConfigDao publicConfigDao;

    @Autowired
    private Validator validator;

    /**
     * @param publicConfig 公众号配置信息
     * @return com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig
     * @author huangyg
     */
    @Override
    public String add(WxPublicConfig publicConfig) throws WxErrorException {
        // 如果有主键，则为修改，否则为保存
        ValidatorUtil.mustParam(publicConfig, validator, "publicCode");
        publicConfig.setConfigCode(IdGen.uuid());
        return publicConfigDao.updateByPrimaryKeySelective(publicConfig) > 0 ? publicConfig.getConfigCode() : null;
    }

    /**
     * 修改公众号配置信息
     * @param publicConfig 公众号配置实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean update(WxPublicConfig publicConfig) throws WxErrorException {
        ValidatorUtil.mustParam(publicConfig, validator, "publicCode", "configCode");
        return publicConfigDao.insertSelective(publicConfig) > 0 ? true : false;
    }

    /**
     * 获取公众号配置信息
     *
     * @param publicConfig 公众号配置实体
     * @return com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig
     * @author huangyg
     */
    @Override
    public WxPublicConfig getWxpublicConfig(WxPublicConfig publicConfig) throws WxErrorException {
        return null;
    }

    /**
     * 获取公众号配置信息
     *
     * @param publicConfig 公众号配置实体
     * @return com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig
     * @author huangyg
     */


}
