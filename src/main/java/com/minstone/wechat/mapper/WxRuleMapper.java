package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxRule;
import org.springframework.stereotype.Component;

@Component
public interface WxRuleMapper {
    int deleteByPrimaryKey(Integer wxruleCode);

    int insert(WxRule record);

    int insertSelective(WxRule record);

    WxRule selectByPrimaryKey(Integer wxruleCode);

    int updateByPrimaryKeySelective(WxRule record);

    int updateByPrimaryKey(WxRule record);
}