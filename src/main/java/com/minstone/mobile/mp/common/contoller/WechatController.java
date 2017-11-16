package com.minstone.mobile.mp.common.contoller;

import com.minstone.mobile.mp.common.handler.MsgHandler;
import com.minstone.mobile.mp.common.handler.SubscribeHandler;
import com.minstone.mobile.mp.common.handler.UnsubscribeHandler;
import com.minstone.mobile.mp.wechat.sendall.controller.SendAllHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/portal")
public class WechatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WxMpMessageRouter router;

    /**
     * GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {

        this.logger.info("\n======================接收到来自微信服务器的认证消息======================\n[signature=[{}], timestamp=[{}],nonce=[{}], echostr=[{}]]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
//            验证成功后的操作
            return echostr;
        }

        return "非法请求";
    }

    @PostMapping
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam(name = "encrypt_type",
                               required = false) String encType,
                       @RequestParam(name = "msg_signature",
                               required = false) String msgSignature) {


        this.logger.info("\n======================接收微信请求====================== \n[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;

        if (encType == null) {

            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);

            WxMpXmlOutMessage outMessage = this.route(inMessage);

            if (outMessage == null) {
                return "";
            }
            out = outMessage.toXml();

        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody, this.wxService.getWxMpConfigStorage(), timestamp,
                    nonce, msgSignature);

            this.logger.info("\n消息解密后内容为：\n{} ", inMessage.toString());

            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(this.wxService.getWxMpConfigStorage());
        }

        this.logger.info("\n======================组装回复信息======================\n{}", out);

        return out;
    }


    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {// 路由规则
            this.router.rule()
                    .msgType(WxConsts.EVT_SUBSCRIBE)
                    .handler(new SubscribeHandler()) // 订阅时事件
                    .end()
                    .rule()
                    .msgType(WxConsts.EVT_UNSUBSCRIBE)
                    .handler(new UnsubscribeHandler())  // 取消关注事件
                    .end()
                    .rule()
                    .msgType(WxConsts.MASS_MSG_NEWS)
                    .handler(new SendAllHandler())  // 群发图文消息
                    .end()
                    .rule()
                    .msgType(WxConsts.XML_MSG_TEXT)
                    .handler(new MsgHandler())  // 用户发送消息
                    .end();
            // 将消息交给路由器
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

}
