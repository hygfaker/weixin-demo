package com.minstone.mobile.mp.wechat.config.service.impl;

import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.config.dao.WxPublicConfigDao;
import com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.config.service.IWxPublicConfigService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangyg
 * @description
 * @since 2017/11/24
 */
@Transactional
@Service
@Slf4j
public class WxPublicConfigServiceImpl implements IWxPublicConfigService {
    /**
     * @param publicConfig 公众号配置信息
     * @return com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig
     * @author huangyg
     */
    @Autowired
    WxPublicConfigDao publicConfigDao;

    @Override
    public WxPublicConfig addOrModifyFlag(WxPublicConfig publicConfig) throws WxErrorException {
        // 如果有主键，则为修改，否则为保存
        if (null != publicConfig.getConfigCode()){
            return publicConfigDao.updateByPrimaryKeySelective(publicConfig) > 0 ? publicConfig : null;
        }else {
            publicConfig.setConfigCode(IdGen.uuid());
            return publicConfigDao.insertSelective(publicConfig) > 0 ? publicConfig : null;
        }
    }
}
