package com.demo.wechat.dao;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class WXPublicTest {
    private static final String namespace="com.demo.wechat.dao.WXPublic";

    @org.junit.Test
    public void test() throws Exception {
        InputStream configStream = Resources.getResourceAsStream("mybatis-wxconfig.xml");
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(configStream);
        SqlSession  sqlSession = sqlSessionFactory.openSession();
        try{
            WXPublicMapper wxPublicMapper = sqlSession.getMapper(WXPublicMapper.class);

            //增加
            WXPublic wxPublic=new WXPublic();
            wxPublic.setWxPublicAeskey("aeskey");
            wxPublic.setWxPublicAppid("appid");
            wxPublic.setWxPublicAppSerct("appseret");
            wxPublic.setWxPublicHeadImg("img");
            wxPublic.setWxPublicName("name");
            wxPublic.setWxPublicNickName("nickname");
            wxPublic.setWxPublicOpenid("openid");
            wxPublic.setWxPublicQrcode("qrcode");
            wxPublic.setWxPublicToken("token");
            wxPublic.setWxPublicUrl("url");
            int insertCount = wxPublicMapper.insert(wxPublic);
            assert insertCount==1;

            //查询
            wxPublicMapper.selectById(1);
            assert wxPublic!=null;

            //更新
            wxPublic.setWxPublicName("yans67");
            wxPublic.setWxPublicNickName("haha");
            int updateCount = wxPublicMapper.updateById(wxPublic);
            assert updateCount > 0;

            //删除
            int deleteCount = wxPublicMapper.deleteById(4);
            assert deleteCount == 1;
        } finally {
            sqlSession.close();
        }
    }
}