package com.minstone.mobile.mp.wechat.publics.dao;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface WxPublicDao<T> {

    int deleteByPrimaryKey(String publicCode);

    int forceDeleteByPrimaryKey(String publicCode);

    int deleteBatch(String[] list);

    int forceDeleteBatch(String[] list);

    int insert(WxPublic record);

    int insertSelective(WxPublic record);

    String selectImgCodeByPrimaryKey(String publicCode);

    WxPublic selectByPrimaryKey(WxPublic wxPublic);

    List<String> test(WxPublic wxPublic);

    int updateByPrimaryKeySelective(WxPublic record);

    List<WxPublic> selectAll();

    // 查询参数为单个的时候可以使用实体
    WxPublic selectPublicCode(String publicCode);

    List<String> selectPublicCodeByOpenId(String openId);

    // 查询参数为数组的时候，不能用实体，mapper 里面读取不到属性
    List<String> selectPublicCodes(String[] publicCodes);

    // 根据 openid 获取公众号实体
    WxPublic selectByOpenId(String openId);
}