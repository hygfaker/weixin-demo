package com.minstone.wechat.contoller;

import com.minstone.wechat.model.Result;
import com.minstone.wechat.config.WechatMpProperties;
import com.minstone.wechat.utils.JsonUtil;
import com.minstone.wechat.utils.ResultUtil;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
        logger.info(service.getWxMpConfigStorage().toString());
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


    // 授权获取 openid
    @GetMapping("/openid")
    public void getOpenid(@RequestParam(value = "isAuthPage") Boolean isAuthPage,
                          @RequestParam(value = "callbackUrl",required = false) String callBackUrl,
                          HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException, ServletException {
        String authUrl = "";
        if(isAuthPage){
            authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/info" + "&response_type=code&scope=snsapi_userinfo&state=" + callBackUrl + "#wechat_redirect";
        }else{
            authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/code" + "&response_type=code&scope=snsapi_base&state=" + callBackUrl + "#wechat_redirect";

        }
        logger.info("微信网页授权的 url：" + authUrl);
        response.sendRedirect(authUrl); // 重定向
    }

    // 返回 openid
    @GetMapping("/openid/code")
    public Result getAccessToken(@RequestParam(value = "code") String code,
                               @RequestParam(value = "state") String callbackUrl,
                               HttpServletResponse response,HttpServletRequest request) throws WxErrorException, IOException {

        // 请求以下链接获取 access_token
        String url = "https://imp.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code="+code+"&grant_type=authorization_code";
        String res = this.service.get(url,null);

        String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();


        if (callbackUrl.contains("www")){  // 如果有传入 callbackurl，则跳转到 url
            if( !callbackUrl.contains("?")){
                callbackUrl += "?";
            }else{
                callbackUrl += "&";
            }
            callbackUrl += "openid=" + openid;
            logger.info("回调的页面：" + callbackUrl);
            // 跳转到回调页面
            response.sendRedirect(callbackUrl); // 重定向
            return ResultUtil.success();
        }else{ // 没有传入 callbackurl，则返回用户 openid
            HashMap<String,String> map = new HashMap<>();
            map.put("openid",openid);
            return ResultUtil.success(map);
        }

    }
    @GetMapping("/openid/info")
    public Result getUserInfo(@RequestParam(value = "code") String code,
                            @RequestParam(value = "state") String callbackUrl,
                            HttpServletResponse response,HttpServletRequest request) throws WxErrorException, IOException {

        WxMpOAuth2AccessToken accessTokenObject =  this.service.oauth2getAccessToken(code);
        String accessToken = accessTokenObject.getAccessToken();
        String openid = accessTokenObject.getOpenId();


        WxMpUser user = this.service.oauth2getUserInfo(accessTokenObject,"zh_CN");

        if (callbackUrl.contains("www")){  // 如果有传入 callbackurl，则跳转到 url
            if( !callbackUrl.contains("?")){
                callbackUrl += "?";
            }else{
                callbackUrl += "&";
            }
//            callbackUrl += "userInfo=" + JsonUtil.toJson(user);
            logger.info("回调的页面：" + callbackUrl);
            // 跳转到回调页面
            response.sendRedirect(callbackUrl); // 重定向
            return ResultUtil.success(JsonUtil.toJson(user));
        }else{ // 没有传入 callbackurl，则返回用户 openid

            return ResultUtil.success(user);
        }


    }

/*  该接口主要用于跳转到相应回调页面

    // 获取用户 openid，需要以下两个方法才能获取。
    @GetMapping("/openid")
    public void getOpenid(@RequestParam(value = "isAuthPage") Boolean isAuthPage,
                       @RequestParam(value = "callbackUrl") String callBackUrl,
                       HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException, ServletException {
        String url = "" ;
        if(isAuthPage){
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/info" + "&response_type=code&scope=snsapi_userinfo&state=" + callBackUrl + "#wechat_redirect";
        }else{
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/code" + "&response_type=code&scope=snsapi_base&state=" + callBackUrl + "#wechat_redirect";

        }
        logger.info("微信网页授权的 url：" + url);
        response.sendRedirect(url); // 重定向
    }
    // 跳转到回调的 url 并且返回 code
    @GetMapping("/openid/code")
    public void getAccessToken(@RequestParam(value = "code") String code,
                                 @RequestParam(value = "state") String callbackUrl,
                                  HttpServletResponse response,HttpServletRequest request) throws WxErrorException, IOException {

        // 请求以下链接获取 access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code="+code+"&grant_type=authorization_code";
        String res = this.imp.get(url,null);

        String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();

        if( !callbackUrl.contains("?")){
            callbackUrl += "?";
        }else{
            callbackUrl += "&";
        }
        callbackUrl += "openid=" + openid;
        logger.info("回调的页面：" + callbackUrl);
        // 跳转到回调页面
        response.sendRedirect(callbackUrl); // 重定向
    }




    // 根据 code 获取微信用户具体信息
    @GetMapping("/openid/info")
    public void getUserInfo(@RequestParam(value = "code") String code,
                               @RequestParam(value = "state") String callbackUrl,
                               HttpServletResponse response,HttpServletRequest request) throws WxErrorException, IOException {

        // 请求以下链接获取 access_token
        String getCodeUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code="+code+"&grant_type=authorization_code";
        String res = this.imp.get(getCodeUrl,null);

        String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();
        String accessToken = new JsonParser().parse(res).getAsJsonObject().get("access_token").getAsString();


        String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String userInfo = this.imp.get(getUserInfo,null);

        logger.info("回调的页面：" + callbackUrl);
        // 跳转到回调页面
        response.sendRedirect(callbackUrl); // 重定向
    }
    */
}
