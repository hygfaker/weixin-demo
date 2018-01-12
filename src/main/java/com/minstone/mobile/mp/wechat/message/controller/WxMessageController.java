package com.minstone.mobile.mp.wechat.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangyg
 * @description
 * @since 2017/11/17
 */

@RestController
@RequestMapping("message")
public class WxMessageController {
    @Autowired
    private IWxMessageService messageService;
    @Autowired
    private WxMpService mpService;

    @GetMapping("getPage")
    public CommonResult getPage(WxMessage message,
                                @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) throws WxErrorException {

        PageHelper.startPage(currentPage, pageSize);
        PageInfo<WxMessage> pageInfo = messageService.getPage(message, currentPage, pageSize);
        List<String> userCodeList = new ArrayList<String>();
        for (WxMessage temp : pageInfo.getList()) {
            if (!userCodeList.contains(temp.getUserCode())) {
                userCodeList.add(temp.getUserCode());
            }
        }
        if (userCodeList.size() <= 0) {
            return ResultUtil.success();
        }
        List<WxMpUser> userList = mpService.getUserService().userInfoList(userCodeList);
        List<WxMessage> messageList = pageInfo.getList();
        for (WxMessage temp : messageList) {
            for (WxMpUser user : userList) {
                if (temp.getUserCode().equals(user.getOpenId())) {
                    temp.setImgUrl(user.getHeadImgUrl());
                    temp.setNickName(user.getNickname());
                }
            }
        }
        pageInfo.setList(messageList);
        return ResultUtil.pageFormat(pageInfo);
    }

    /*
    https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547
    当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），微信将会把消息数据推送给开发者，开发者可以在一段时间内（目前修改为48小时）
    调用客服接口，通过POST一个JSON数据包来发送消息给普通用户。此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务
    * */
    @PostMapping("/replyMessage")
    public CommonResult replyMessage(WxMessage message) throws WxErrorException {


        WxMessage targetMessage = messageService.getDetail(message);

        if (targetMessage.getMsgFlag() == 1){
            throw new CommonException(CommonResultEnum.MESSAGE_NO_REPEAT);
        }

        WxMpKefuMessage kefuMessage = new WxMpKefuMessage();
        kefuMessage.setMsgType(WxConsts.KefuMsgType.TEXT);
        kefuMessage.setToUser(message.getUserCode());
        kefuMessage.setContent(message.getReplyContent());
        if (mpService.getKefuService().sendKefuMessage(kefuMessage)){
            return ResultUtil.success(messageService.updateMessage(message));
        }else{
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }
}
