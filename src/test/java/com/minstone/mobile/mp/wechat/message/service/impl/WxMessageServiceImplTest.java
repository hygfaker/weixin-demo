package com.minstone.mobile.mp.wechat.message.service.impl;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.utils.IdGen;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class WxMessageServiceImplTest {
    @Autowired
    private IWxMessageService messageService;

    @Test
    public void add() throws Exception {
        for (int i=0; i<10; i++){
            WxMessage message = new WxMessage();
            message.setMsgCode(IdGen.uuid());
            message.setPublicCode("8c6a013248e34d5790eaab1a5500335c");
            message.setUserCode("oWGSawXsClb5YDNhBrOQG5WXo3ew");
            message.setMsgId(IdGen.uuid());
            message.setCreateDate("2017-11-28 16:54:42");
            message.setMsgType("text");
            messageService.add(message);
        }
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void forceDelete() throws Exception {
    }

    @Test
    public void batchDelete() throws Exception {
    }

    @Test
    public void batchForceDelete() throws Exception {
    }

    @Test
    public void getDetail() throws Exception {
        WxMessage message = new WxMessage();
        message.setMsgCode("65f199720fbd4d2a864405ebbc50d173");
        WxMessage result = messageService.getDetail(message);
        log.info(String.valueOf(result));
    }

    @Test
    public void getPage() throws Exception {
        WxMessage message = new WxMessage("8c6a013248e34d5790eaab1a5500335c");
        message.setDayLimit(3);
        PageInfo<WxMessage>pageInfo = messageService.getPage(message,1,999);
        log.info(String.valueOf(pageInfo.getList().size()));
    }

    @Test
    public void replyMessage() throws Exception {
        WxMessage message = new WxMessage();
        message.setReplyContent("客服正忙，请稍后再咨询");
        message.setMsgCode("65f199720fbd4d2a864405ebbc50d173");
        assertNotEquals(false,messageService.replyMessage(message));
    }

}