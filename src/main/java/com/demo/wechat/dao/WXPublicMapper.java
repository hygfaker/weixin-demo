package com.demo.wechat.dao;

/**
 * Created by huangyg on 2017/8/29.
 */
public interface WXPublicMapper {
    public int insert(WXPublic wxPublic);
    public WXPublic selectById(int id);
    public int updateById(WXPublic wxPublic);
    public int deleteById(int id);
}
