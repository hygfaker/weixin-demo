package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.utils.ResultUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangyg on 2017/8/2.
 */
@RestController
@RequestMapping("/user")
public class WxUserController {

    private static Logger logger = LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    private WxMpService service;

    // 获取用户列表
    @GetMapping("/list")
    public WxMpUserList userList(@RequestParam(value = "nextOpenid",required = false) String nextOpenid) throws WxErrorException{
        return this.service.getUserService().userList(nextOpenid);
    }

    // 获取用户基本信息
    @GetMapping("/info")
    public WxMpUser userInfo(@RequestParam(value = "openid") String openid,
                             @RequestParam(value = "lang",required = false) String lang) throws WxErrorException{
        return this.service.getUserService().userInfo(openid,lang);
    }

    // 批量获取用户基本信息
    @PostMapping("/infoList")
    public List<WxMpUser> userInfoList(@RequestParam("openids") List<String> openids) throws WxErrorException{
        return this.service.getUserService().userInfoList(openids);
    }

    // 修改用户备注名
    @PostMapping("/updateRemark")
    public void userUpdateRemark(@RequestParam("openid") String openid,
                                 @RequestParam("remark") String remark) throws WxErrorException{
        this.service.getUserService().userUpdateRemark(openid,remark);
    }

    // 获取公众号的黑名单列表
    @GetMapping("/blacklist")
    public Result blackList(@RequestParam(value = "nextOpenid",required = false) String nextOpenid) throws WxErrorException{
        return ResultUtils.success(this.service.getBlackListService().getBlacklist(nextOpenid));

    }

    // 拉黑用户
    @PostMapping("/pushBlackList")
    public Result pushToBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException{
        this.service.getBlackListService().pushToBlacklist(openidList);
        return ResultUtils.success();

    }

    // 取消拉黑用户
    @PostMapping("/removeBlackList")
    public Result removeFromBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException{
        this.service.getBlackListService().pullFromBlacklist(openidList);
        return ResultUtils.success();
    }

    // 绑定用户信息
    @PostMapping("/bind")
    public void accountBind(@RequestParam("userid") String userid){

        logger.info("userid={}",userid);
    }
}
