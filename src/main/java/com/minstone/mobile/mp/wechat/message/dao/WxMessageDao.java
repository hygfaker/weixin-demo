package com.minstone.mobile.mp.wechat.message.dao;

import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxMessageDao {
    int deleteByPrimaryKey(String msgCode);

    int insert(WxMessage record);

    int insertSelective(WxMessage record);

    WxMessage selectByPrimaryKey(String msgCode);

    List<WxMessage> selectAll(WxMessage record);

    int updateByPrimaryKeySelective(WxMessage record);

    int updateByPrimaryKey(WxMessage record);
}