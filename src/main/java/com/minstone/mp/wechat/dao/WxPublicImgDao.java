package com.minstone.mp.wechat.dao;

import com.minstone.mp.wechat.domain.WxPublicImg;
import org.springframework.stereotype.Component;

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