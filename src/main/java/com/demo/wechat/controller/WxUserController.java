package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import com.demo.wechat.config.WechatMpProperties;
import com.demo.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private WechatMpProperties properties;

    // 获取用户列表
    @GetMapping("/list")
    public WxMpUserList userList(@RequestParam(value = "nextOpenid",required = false) String nextOpenid) throws WxErrorException{
        System.out.println(service.getWxMpConfigStorage().toString());
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
        return ResultUtil.success(this.service.getBlackListService().getBlacklist(nextOpenid));

    }

    // 拉黑用户
    @PostMapping("/pushBlackList")
    public Result pushToBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException{
        this.service.getBlackListService().pushToBlacklist(openidList);
        return ResultUtil.success();

    }

    // 取消拉黑用户
    @PostMapping("/removeBlackList")
    public Result removeFromBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException{
        this.service.getBlackListService().pullFromBlacklist(openidList);
        return ResultUtil.success();
    }

    // 获取用户 openid，需要以下两个方法才能获取。
    @GetMapping("/openid")
    public String openid(@RequestParam(value = "isAuthPage") Boolean isAuthPage,
                         @RequestParam(value = "callbackUrl") String callBackUrl,
                         HttpServletRequest request) throws WxErrorException {
        String url = "" ;

        if(isAuthPage){
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/accessToken" + "&response_type=code&scope=snsapi_userinfo&state=" + callBackUrl + "#wechat_redirect";
        }else{
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/accessToken" + "&response_type=code&scope=snsapi_base&state=" + callBackUrl + "#wechat_redirect";

        }
        logger.info(url);

        return "redirect:" + url;

    }

    @GetMapping("/openid/accessToken")
    public String accessToken(@RequestParam(value = "code") String code,
                            @RequestParam(value = "state") String state) throws WxErrorException{
        String openid = "";

        // 请求以下链接获取 access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code="+code+"&grant_type=authorization_code";
        String res = this.service.get(url,null);
        // 获取 access_token、openid 等信息
        return res;
    }

}
