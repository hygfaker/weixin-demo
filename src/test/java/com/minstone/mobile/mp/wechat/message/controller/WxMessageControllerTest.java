package com.minstone.mobile.mp.wechat.message.controller;

import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author huangyg
 * @description
 * @since 2017/11/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxMessageControllerTest {
    @Test
    public void getPage() throws Exception {
        WxMessage message = new WxMessage();
        message.setPublicCode("8c6a013248e34d5790eaab1a5500335c");

    }

}