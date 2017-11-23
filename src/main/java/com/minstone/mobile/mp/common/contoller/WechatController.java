package com.minstone.mobile.mp.common.contoller;

import com.minstone.mobile.mp.wechat.message.handler.MsgHandler;
import com.minstone.mobile.mp.common.handler.SubscribeHandler;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import com.minstone.mobile.mp.wechat.sendall.controller.SendAllHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.*;
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

    @Autowired
    private IWxPublicService publicService;

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

        this.logger.info("\n-------------------接收到来自微信服务器的认证消息-------------------\n" +
                "[signature=[{}], timestamp=[{}],nonce=[{}], echostr=[{}]]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            this.logger.info("\n----------------------------认证成功-----------------------------\n");
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


        this.logger.info("\n-------------------接收微信请求------------------- \n" +
                        "[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], \nrequestBody=[{}]" +
                        "---------------------------------------------",
                signature, encType, msgSignature, timestamp, nonce, requestBody);
//        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
//            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
//        }
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
            // aes 加密过的消息进行解密。
            /*
            * 解密过程：根据用户消息中的 ToUserName 从数据库中 MP_YY_PUBLIC表找到对应的 publicCode，然后获取对应的 app_id、app_serct、token、aeskey
            * */
            WxPublic wxPublic = new WxPublic();
            String openId = WxMpXmlMessage.fromXml(requestBody).getToUser();
            wxPublic = publicService.selectByOpenId(openId);
            WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
            wxConfigProvider.setAppId(wxPublic.getAppId());
            wxConfigProvider.setSecret(wxPublic.getAppSerct());
            wxConfigProvider.setToken(wxPublic.getToken());
            wxConfigProvider.setAesKey(wxPublic.getAeskey());
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxConfigProvider, timestamp,nonce, msgSignature);

            // 路由在 WechatMpConfiguration 类里面配置
            WxMpXmlOutMessage outMessage = this.router.route(inMessage);
//            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            // 加密过程，利用上面的config 对象中的公众号配置进行加密
            out = outMessage.toEncryptedXml(wxConfigProvider);
        }

        this.logger.info("\n-------------------组装回复信息-------------------" +
                "\n{}" +
                "\n-----------------------------------------------", out);

        return out;
    }


    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {// 路由规则
             this.router.rule()
                    .msgType(WxConsts.EVT_SUBSCRIBE)
                    .handler(new SubscribeHandler()) // 关注时回复（已完成）
                    .end()
                    .rule()
                    .msgType(WxConsts.MASS_MSG_NEWS)
                    .handler(new SendAllHandler())  // 群发图文消息
                    .end()
                    .rule()
                    .handler(new MsgHandler())  // 兜底路由规则，一般放到最后
                    .end();
            // 将消息交给路由器
            return this.router.route(message);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

}
