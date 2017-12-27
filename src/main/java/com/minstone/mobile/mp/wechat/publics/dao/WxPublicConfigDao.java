package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WxPublicConfigDao {
    int deleteByPrimaryKey(@Param("configCode") String configCode);

    int insert(WxPublicConfig record);

    int insertSelective(WxPublicConfig record);

    WxPublicConfig selectByPrimaryKey(@Param("configCode") String configCode,@Param("publicCode") String publicCode);

    int updateByPrimaryKeySelective(WxPublicConfig record);

    int updateByPrimaryKey(WxPublicConfig record);
}