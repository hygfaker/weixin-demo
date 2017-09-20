package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublicImg;
import org.springframework.stereotype.Component;

@Component
public interface WxPublicImgMapper {
    int deleteByPrimaryKey(String imgCode);

    int insert(WxPublicImg record);

    int insertSelective(WxPublicImg record);

    WxPublicImg selectByPrimaryKey(String imgCode);

    int updateByPrimaryKeySelective(WxPublicImg record);

    int updateByPrimaryKey(WxPublicImg record);
}