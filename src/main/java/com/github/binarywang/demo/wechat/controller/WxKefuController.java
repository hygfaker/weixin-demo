package com.github.binarywang.demo.wechat.controller;

import com.github.binarywang.demo.wechat.domain.Result;
import com.github.binarywang.demo.wechat.utils.DateUtils;
import com.github.binarywang.demo.wechat.utils.ResultUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
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
    public Result kfList(){
        try {
             return ResultUtils.success(this.service.getKefuService().kfList());
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 获取在线客服列表
    @GetMapping("/onlineList")
    public Result kfOnlineList(){
        try {
            return ResultUtils.success(this.service.getKefuService().kfOnlineList());
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 添加客服
    @PostMapping("/add")
    public Result kfAccountAdd(@ModelAttribute WxMpKfAccountRequest account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfAccountAdd(account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 邀请客服
    @PostMapping("/invite")
    public Result kfAccountInvite(@ModelAttribute WxMpKfAccountRequest account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfAccountInviteWorker(account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 删除客服
    @GetMapping("/delete")
    public Result kfAccountDelete(@RequestParam String account){
        try {
            if (this.service.getKefuService().kfAccountDel(account)){
                return ResultUtils.success();
            }else{
                return ResultUtils.failure("failure",200);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 上传客服头像？？
    @PostMapping("/uploadImg")
    public Result kfAccountUploadHeadImg(String account, File imgFile) throws WxErrorException{
        if (this.service.getKefuService().kfAccountUploadHeadImg(account,imgFile)){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("failure",200);
        }
    }

    // 更新客服信息
    @PostMapping("/update")
    public Result kfAccountUpdate(@ModelAttribute WxMpKfAccountRequest account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfAccountUpdate(account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 创建会话
    @PostMapping("/sessionCreate")
    public Result kfSessionCreate(String openid, String account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfSessionCreate(openid , account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 关闭会话
    @PostMapping("/sessionClose")
    public Result kfSessionClose(String openid, String account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfSessionClose(openid , account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 获取客户会话状态
    @GetMapping("/sessionGet")
    public Result kfSessionGet(@RequestParam String openid){
        try {
            return ResultUtils.success(this.service.getKefuService().kfSessionGet(openid));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 获取客服的会话列表
    @GetMapping("/sessionList")
    public Result kfSessionList(@RequestParam String account){
        try {
            return ResultUtils.success(this.service.getKefuService().kfSessionList(account));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 获取未接入会话列表
    @GetMapping("/sessionWaitGet")
    public Result kfSessionGetWaitCase(){
        try {
            return ResultUtils.success(this.service.getKefuService().kfSessionGetWaitCase());
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }

    // 获取聊天记录
    @PostMapping("/chatList")
    public Result kfMsgList(String startTime,
                            String endTime,
                            @RequestParam(value = "msgid",required = false,defaultValue = "1") Long msgid,
                            @RequestParam(value = "number",required = false,defaultValue = "10000") Integer number){

        Date start = DateUtils.dateToUnixTimestamp(startTime);
        Date end = DateUtils.dateToUnixTimestamp(endTime);
        try {
            return ResultUtils.success(this.service.getKefuService().kfMsgList(start, end, msgid, number));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResultUtils.failure(e.toString(),500);
        }
    }


}
