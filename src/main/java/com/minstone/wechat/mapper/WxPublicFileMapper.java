package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublicFile;
import org.springframework.stereotype.Component;
@Component
public interface WxPublicFileMapper {
    int deleteByPrimaryKey(String fileCode);

    int insert(WxPublicFile record);

    int insertSelective(WxPublicFile record);

    WxPublicFile selectByPrimaryKey(String fileCode);

    int updateByPrimaryKeySelective(WxPublicFile record);

    int updateByPrimaryKeyWithBLOBs(WxPublicFile record);

    int updateByPrimaryKey(WxPublicFile record);
}