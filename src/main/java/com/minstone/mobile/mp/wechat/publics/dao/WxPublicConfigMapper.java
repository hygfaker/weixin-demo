package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxPublicConfigMapper {
    int deleteByPrimaryKey(String configCode);

    int insert(WxPublicConfig record);

    int insertSelective(WxPublicConfig record);

    WxPublicConfig selectByPrimaryKey(String configCode);

    int updateByPrimaryKeySelective(WxPublicConfig record);

    int updateByPrimaryKey(WxPublicConfig record);
}