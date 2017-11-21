package com.minstone.mobile.mp.wechat.reply;

import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huangyg
 * @description
 * @since 2017/11/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestIWxReplyService{
    @Autowired
    private IWxReplyService replyService;

    @Test
    public void testInitDate() throws WxErrorException {
        replyService.initData();
    }
}
