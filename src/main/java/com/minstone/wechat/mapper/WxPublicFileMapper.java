package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublicFile;
import com.minstone.wechat.domain.WxPublicFileWithBLOBs;
import org.springframework.stereotype.Component;
@Component
public interface WxPublicFileMapper {
    int deleteByPrimaryKey(String fileCode);

    int insert(WxPublicFileWithBLOBs record);

    int insertSelective(WxPublicFileWithBLOBs record);

    WxPublicFileWithBLOBs selectByPrimaryKey(String fileCode);

    int updateByPrimaryKeySelective(WxPublicFileWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WxPublicFileWithBLOBs record);

    int updateByPrimaryKey(WxPublicFile record);
}