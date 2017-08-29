package com.demo.wechat.dao;


import com.demo.wechat.mapper.WXPublicMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WXPublicTest {

    @Autowired
    private WXPublicMapper wxPublicMapper;

    @org.junit.Test
    public void insertTest()throws Exception{
        //增加
        WXPublic wxPublic=new WXPublic();
        wxPublic.setWxPublicAeskey("aeskey2222");
        wxPublic.setWxPublicAppid("appid222");
        wxPublic.setWxPublicAppSerct("appseret222");
        wxPublic.setWxPublicHeadImg("img");
        wxPublic.setWxPublicName("name");
        wxPublic.setWxPublicNickName("nickname");
        wxPublic.setWxPublicOpenid("openid");
        wxPublic.setWxPublicQrcode("qrcode");
        wxPublic.setWxPublicToken("token");
        wxPublic.setWxPublicUrl("url");
        int insertCount = wxPublicMapper.insert(wxPublic);
        assert insertCount==1;
    }

    @org.junit.Test
    public void test() throws Exception {
        InputStream configStream = Resources.getResourceAsStream("mybatis-wxconfig.xml");

        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(configStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


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