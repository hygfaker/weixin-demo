package com.minstone.mobile.mp.wechat.message.dao;

import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxMessageDao {

    int deleteByPrimaryKey(WxMessage message);

    int insert(WxMessage message);

    int insertSelective(WxMessage record);

    WxMessage selectByPrimaryKey(WxMessage message);

    int updateByPrimaryKeySelective(WxMessage record);

    int updateByPrimaryKeyWithBLOBs(WxMessage record);

    int updateByPrimaryKey(WxMessage record);
}