package com.minstone.mobile.mp.wechat.config.dao;

import com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WxPublicConfigDao {
    int deleteByPrimaryKey(String configCode);

    int insert(WxPublicConfig record);

    int insertSelective(WxPublicConfig record);

    WxPublicConfig selectByPrimaryKey(String configCode);

    int updateByPrimaryKeySelective(WxPublicConfig record);

    int updateByPrimaryKey(WxPublicConfig record);
}