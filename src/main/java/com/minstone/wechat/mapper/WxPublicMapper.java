package com.minstone.wechat.mapper;

import com.minstone.wechat.domain.WxPublic;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by huangyg on 2017/8/29.
 */
@Component
public interface WxPublicMapper {

    public int insert(WxPublic wxPublic);

    public WxPublic getByCode(int wxPublicCode);

    public List<WxPublic> getAll();

    public int updateById(WxPublic wxPublic);

    public int deleteById(int wxPublicCode);
}
