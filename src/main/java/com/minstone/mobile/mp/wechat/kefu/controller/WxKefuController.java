package com.minstone.mobile.mp.wechat.kefu.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.utils.DateUtil;
import com.minstone.mobile.mp.utils.ResultUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;

/**
 * Created by huangyg on 2017/8/7.
 */
@RestController
@RequestMapping("/kefu")
public class WxKefuController {

    @Autowired
    private WxMpService service;

    // 获取客服列表
    @GetMapping("/list")
    public CommonResult kfList() throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfList());

    }

    // 获取在线客服列表
    @GetMapping("/onlineList")
    public CommonResult kfOnlineList() throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfOnlineList());

    }

    // 添加客服
    @PostMapping("/add")
    public CommonResult kfAccountAdd(@ModelAttribute WxMpKfAccountRequest account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfAccountAdd(account));

    }

    // 邀请客服
    @PostMapping("/invite")
    public CommonResult kfAccountInvite(@ModelAttribute WxMpKfAccountRequest account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfAccountInviteWorker(account));

    }

    // 删除客服
    @GetMapping("/delete")
    public CommonResult kfAccountDelete(@RequestParam String account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfAccountDel(account));

    }

    // 上传客服头像？？
    @PostMapping("/uploadImg")
    public CommonResult kfAccountUploadHeadImg(String account, File imgFile) throws WxErrorException{
        return ResultUtil.success();
    }

    // 更新客服信息
    @PostMapping("/update")
    public CommonResult kfAccountUpdate(@ModelAttribute WxMpKfAccountRequest account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfAccountUpdate(account));

    }

    // 创建会话
    @PostMapping("/sessionCreate")
    public CommonResult kfSessionCreate(String openid, String account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfSessionCreate(openid , account));

    }

    // 关闭会话
    @PostMapping("/sessionClose")
    public CommonResult kfSessionClose(String openid, String account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfSessionClose(openid , account));

    }

    // 获取客户会话状态
    @GetMapping("/sessionGet")
    public CommonResult kfSessionGet(@RequestParam String openid) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfSessionGet(openid));

    }

    // 获取客服的会话列表
    @GetMapping("/sessionList")
    public CommonResult kfSessionList(@RequestParam String account) throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfSessionList(account));

    }

    // 获取未接入会话列表
    @GetMapping("/sessionWaitGet")
    public CommonResult kfSessionGetWaitCase() throws WxErrorException{
        return ResultUtil.success(this.service.getKefuService().kfSessionGetWaitCase());

    }

    // 获取聊天记录
    @PostMapping("/chatList")
    public CommonResult kfMsgList(String startTime,
                                  String endTime,
                                  @RequestParam(value = "msgid",required = false,defaultValue = "1") Long msgid,
                                  @RequestParam(value = "number",required = false,defaultValue = "10000") Integer number) throws WxErrorException{

        Date start = DateUtil.dateToUnixTimestamp(startTime);
        Date end = DateUtil.dateToUnixTimestamp(endTime);

        return ResultUtil.success(this.service.getKefuService().kfMsgList(start, end, msgid, number));

    }



}
