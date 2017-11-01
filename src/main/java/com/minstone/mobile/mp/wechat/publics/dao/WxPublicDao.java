package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxPublicDao {

    String selectPublicCode(@Param("publicCode") String publicCode);

    int deleteByPrimaryKey(String publicCode);

    int forceDeleteByPrimaryKey(String publicCode);

    int deleteBatch(String[] list);

    int forceDeleteBatch(String[] list);

    int insert(WxPublic record);

    int insertSelective(WxPublic record);

    String selectImgCodeByPrimaryKey(String publicCode);

    WxPublic selectByPrimaryKey(String publicCode);

    int updateByPrimaryKeySelective(WxPublic record);

    List<WxPublic> selectAll();

    int updateByPrimaryKey(WxPublic record);
}