package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxPublicConfig;

public interface WxPublicConfigMapper {
    int deleteByPrimaryKey(String configCode);

    int insert(WxPublicConfig record);

    int insertSelective(WxPublicConfig record);

    WxPublicConfig selectByPrimaryKey(String configCode);

    int updateByPrimaryKeySelective(WxPublicConfig record);

    int updateByPrimaryKey(WxPublicConfig record);
}