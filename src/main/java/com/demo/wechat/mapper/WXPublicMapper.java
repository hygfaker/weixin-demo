package com.demo.wechat.mapper;

import com.demo.wechat.dao.WXPublic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by huangyg on 2017/8/29.
 */
@Component
public interface WXPublicMapper {
    @Insert("INSERT INTO wxpublic (WXPUBLIC_OPENID,WXPUBLIC_NAME,WXPUBLIC_NICKNAME,WXPUBLIC_HEADIMG,WXPUBLIC_QRCODE,WXPUBLIC_APPID,WXPUBLIC_APPSERCT,WXPUBLIC_TOKEN,WXPUBLIC_AESKEY,WXPUBLIC_URL)\n" +
            " VALUES (#{wxPublicOpenid},#{wxPublicName},#{wxPublicNickName},#{wxPublicHeadImg},#{wxPublicQrcode},#{wxPublicAppid},#{wxPublicAppSerct},#{wxPublicToken},#{wxPublicAeskey},#{wxPublicUrl})")
    public int insert(WXPublic wxPublic);

    @Select("SELECT WXPUBLIC_CODE,WXPUBLIC_OPENID,WXPUBLIC_NAME FROM wxpublic WHERE WXPUBLIC_CODE= #{wxPublicCode}")
    public WXPublic selectById(int id);

    @Update("UPDATE wxpublic SET WXPUBLIC_OPENID=#{wxPublicOpenid},\n" +
            "                           WXPUBLIC_NAME=#{wxPublicName},\n" +
            "                           WXPUBLIC_NICKNAME=#{wxPublicNickName},\n" +
            "                           WXPUBLIC_HEADIMG=#{wxPublicHeadImg},\n" +
            "                           WXPUBLIC_QRCODE=#{wxPublicQrcode},\n" +
            "                           WXPUBLIC_APPID=#{wxPublicAppid},\n" +
            "                           WXPUBLIC_APPSERCT=#{wxPublicAppSerct},\n" +
            "                           WXPUBLIC_TOKEN=#{wxPublicToken},\n" +
            "                           WXPUBLIC_AESKEY=#{wxPublicAeskey},\n" +
            "                           WXPUBLIC_URL=#{wxPublicUrl}")
    public int updateById(WXPublic wxPublic);

    @Delete("DELETE FROM wxpublic WHERE WXPUBLIC_CODE=#{wxPublicCode}")
    public int deleteById(int id);
}
