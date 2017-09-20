package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxPublicMapper {

    int deleteByPrimaryKey(String publicCode);

    int insert(WxPublic record);

    int insertSelective(WxPublic record);

    String selectImgCodeByPrimaryKey(String publicCode);

    WxPublic selectByPrimaryKey(String publicCode);

    int updateByPrimaryKeySelective(WxPublic record);

    List<WxPublic> selectAll();

    int updateByPrimaryKey(WxPublic record);
}