package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxPublicMapper {
    int deleteByPrimaryKey(Integer publicCode);

    int insert(WxPublic record);

    int insertSelective(WxPublic record);

    WxPublic selectByPrimaryKey(Integer publicCode);

    List<WxPublic> selectAll();

    int updateByPrimaryKeySelective(WxPublic record);

    int updateByPrimaryKey(WxPublic record);
}