package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxPublicMapper {
    int deleteByPrimaryKey(String publicCode);

    int insert(WxPublic record);

    // 需要给 WxPublic 一个默认的构造函数
    List<WxPublic> selectAll();

    int insertSelective(WxPublic record);

    WxPublic selectByPrimaryKey(String publicCode);

    int updateByPrimaryKeySelective(WxPublic record);

    int updateByPrimaryKey(WxPublic record);
}