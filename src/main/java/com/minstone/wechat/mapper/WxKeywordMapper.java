package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxKeyword;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxKeywordMapper {
    int deleteByPrimaryKey(String wxruleKeyword);

    int insert(WxKeyword record);

    int batchInsert(List<WxKeyword>keywords);


    int insertSelective(WxKeyword record);

    WxKeyword selectByPrimaryKey(String wxruleKeyword);

    int updateByPrimaryKeySelective(WxKeyword record);

    int updateByPrimaryKey(WxKeyword record);
}