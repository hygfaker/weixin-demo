package com.minstone.wechat.dao;

import com.minstone.wechat.domain.WxPublicImg;
import org.springframework.stereotype.Component;

import java.sql.Blob;

@Component
public interface WxPublicImgDao {
    int deleteByPrimaryKey(String imgCode);

    int insert(WxPublicImg record);

    int insertSelective(WxPublicImg record);

    WxPublicImg selectByPrimaryKey(String imgCode);

    int updateByPrimaryKeySelective(WxPublicImg record);

    int updateByPrimaryKey(WxPublicImg record);

    byte[] selectHeadimgByImgCode(String imgCode);

    byte[] selectQrcodeByImgCode(String imgCode);
}