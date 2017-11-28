package com.minstone.mobile.mp.wechat.message.handler;

import com.minstone.mobile.mp.common.builder.TextBuilder;
import com.minstone.mobile.mp.wechat.kefu.IWxKfSessionService;
import com.minstone.mobile.mp.wechat.kefu.impl.WxKfSessionService;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * @author huangyg
 * @description
 * @since 2017/11/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MsgHandlerTest {

    @Autowired
    private IWxReplyService replyService;

    @Autowired
    private IWxPublicConfigService publicConfigService;

    @Test
    public void handle() throws Exception {

             /* 用户发送的消息，根据【关键词流程】走 */
        String publicCode = "8c6a013248e34d5790eaab1a5500335c";
        String keyword = "明动";

        WxReply reply = new WxReply(publicCode);
        WxPublicConfig publicConfig = new WxPublicConfig(publicCode);
        // 是否开启所有关键词回复，否-》是否开启消息转发客服
        log.info("===== 是否开启所有关键词回复 =====");
        if (replyService.getReplyRule(reply).getReplyFlag() == 1) {
            WxReplyRule replyRule = new WxReplyRule(publicCode, keyword);
            // 获取匹配到的关键词规则内容
            WxReplyRule matchReplyRule = replyService.getMatchContent(replyRule);
            // 是否开启客服,否-》回复关键词匹配内容,是-》创建会话并发送消息
            log.info("===== 是否匹配关键词、关键词是否打开和是否开启客服 =====");
            if (matchReplyRule != null && matchReplyRule.getKefuReplyFlag() != 1) {
                log.info("===== 回复关键词匹配内容 =====");
                return ;
            }
        }

        // 是否开启消息转发客服，否-》是否开启非关键词回复
        log.info("===== 是否开启消息转发客服 =====");
        if (publicConfigService.get(publicConfig).getKefuUseFlag() == 1) {
            log.info("===== 是否有在线客服、创建会话、回复客服不在线内容或者回复欢迎语 =====");
//            if ("回复欢迎语"){
                return ;
//            }

        }
        // 是否开启非关键词回复，是-》回复非关键词内容，记录到消息列表 ，否-》记录到消息列表
        WxReply normalReply = replyService.getNormal(reply);
        log.info("===== 是否开启非关键词回复 =====");
        if (normalReply.getReplyFlag() == 1) {
            log.info("===== 回复非关键词设置内容 =====");
        }
        //todo 记录到消息列表
        log.info("===== 记录到消息列表 =====");
        return ;
    }


}