package com.minstone.mobile.mp.wechat.message.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult getPage(WxMessage message, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, @RequestParam(value = "pageSize", defaultValue = "1") int pageSize) throws WxErrorException {
        PageInfo<WxMessage> pageInfo = messageService.getPage(message, currentPage, pageSize);
        List<String> userCodeList = new ArrayList<String>();
        for (WxMessage temp : pageInfo.getList()) {
            if (!userCodeList.contains(temp.getUserCode())){
                userCodeList.add(temp.getUserCode());
            }
        }
        List<WxMpUser> userList = mpService.getUserService().userInfoList(userCodeList);
        List<WxMessage> messageList = pageInfo.getList();
        for (WxMessage temp : messageList) {
            for (WxMpUser user : userList) {
                if (temp.getUserCode().equals(user.getOpenId())){
                    temp.setImgUrl(user.getHeadImgUrl());
                    temp.setNickName(user.getNickname());
                }
            }
        }
        pageInfo.setList(messageList);
        return ResultUtil.pageFormat(pageInfo);
    }

    @PostMapping("/replyMessage")
    public CommonResult replyMessage(WxMessage message){
        if (messageService.replyMessage(message)){
            return ResultUtil.success();
        }else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }
}
