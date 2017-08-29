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
            int insertCount = sqlSession.insert(namespace + ".insert", wxPublic);
            assert insertCount==1;

            //查询
            sqlSession.selectOne(namespace + ".selectById", 1);
            assert wxPublic!=null;
            System.out.println(wxPublic);


            //更新
            wxPublic.setWxPublicName("yans67");
            wxPublic.setWxPublicNickName("haha");
            int updateCount = sqlSession.update(namespace + ".updateById", wxPublic);
            assert updateCount==1;

            //删除
            int deleteCount = sqlSession.delete(namespace + ".deleteById", 1);
            assert deleteCount==1;
        } finally {
            sqlSession.close();
        }
    }
}