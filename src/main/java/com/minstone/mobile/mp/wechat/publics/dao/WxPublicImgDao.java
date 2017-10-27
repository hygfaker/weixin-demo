package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxPublicImgDao {
    int deleteByPrimaryKey(String imgCode);

    int forceDeleteByPrimaryKey(String imgCode);
    int insert(WxPublicImg record);

    int insertSelective(WxPublicImg record);

    WxPublicImg selectByPrimaryKey(String imgCode);

    int updateByPrimaryKeySelective(WxPublicImg record);

    int updateByPrimaryKey(WxPublicImg record);

    byte[] selectHeadimgByImgCode(String imgCode);

    byte[] selectQrcodeByImgCode(String imgCode);
}