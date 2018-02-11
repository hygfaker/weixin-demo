package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicFileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WxPublicFileInfoDao {
    /**
     * 根据主键删除信息
     */
    int deleteByPrimaryKey(String pubFileCode);

    /**
     * 根据主键物理删除信息
     */
    int forceDeleteByPrimaryKey(String pubFileCode);

    /**
     * 插入信息
     */
    int insert(WxPublicFileInfo record);

    /**
     * 根据信息选择性插入信息
     */
    int insertSelective(WxPublicFileInfo record);

    /**
     * 根据主键获取信息
     */
    WxPublicFileInfo selectByPrimaryKey(String pubFileCode);

    /**
     * 根据公众号获取信息
     */
    List<WxPublicFileInfo> selectByPublicCode(@Param("publicCode")String publicCode,@Param("type") Integer type);

    /**
     * 根据信息选择性更新
     */
    int updateByPrimaryKeySelective(WxPublicFileInfo record);

    /**
     * 更新
     */
    int updateByPrimaryKey(WxPublicFileInfo record);
}