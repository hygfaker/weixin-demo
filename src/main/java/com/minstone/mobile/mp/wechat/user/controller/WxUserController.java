package com.minstone.mobile.mp.wechat.user.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.StorageAnnotation;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.PagerUtil;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.config.WechatMpProperties;
import com.minstone.mobile.mp.utils.JsonUtil;
import com.google.gson.JsonParser;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import lombok.extern.log4j.Log4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 该数据的获取主要是根据公众号配置的 appkey、appsecret、token 来获取。
 * <p>
 *
 * @author huangyg
 * @date 2017/8/2
 */

@RestController
@RequestMapping("/user")
@Log4j
public class WxUserController {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WechatMpProperties properties;

    @Autowired
    private IWxPublicService publicService;

    // 获取用户列表
    @GetMapping("/list")
    @StorageAnnotation
    public CommonResult userList(@RequestParam(value = "nextOpenid", required = false) String nextOpenid,
                                 @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                 @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) throws WxErrorException, IOException {
        List<String> list = this.wxService.getUserService().userList(nextOpenid).getOpenids();
        PageInfo<String> page = PagerUtil.lowPager(currentPage, pageSize, list);
        return ResultUtil.pageFormat(page);
    }

    // 获取用户基本信息
    @GetMapping("/info")
    public CommonResult userInfo( @RequestParam(value = "openid") String openid,
                             @RequestParam(value = "lang", required = false) String lang) throws WxErrorException {
        return ResultUtil.success(this.wxService.getUserService().userInfo(openid, lang));
    }

    // 批量获取用户基本信息
    @PostMapping("/infoList")
    public CommonResult userInfoList(@RequestParam("openids") List<String> openids,
                                     @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "5")  int pageSize) throws WxErrorException, IOException {
        if (openids.size() == 0){
            return ResultUtil.success();
        }else{
            List<WxMpUser> list = this.wxService.getUserService().userInfoList(openids);
            PageInfo<WxMpUser> page = PagerUtil.lowPager(currentPage, pageSize, list);
            return ResultUtil.pageFormat(page);
        }
    }

    // 分页直接获取所有用户基本信息、
    @GetMapping("/userInfoPage")
    public CommonResult userInfoPage(@RequestParam(value = "nextOpenid", required = false) String nextOpenid,
                                     @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "5")  int pageSize) throws WxErrorException, IOException {
        // 先获取列表
        List<String> list = this.wxService.getUserService().userList(nextOpenid).getOpenids();
        PageInfo<WxMpUser> page = PagerUtil.lowPager(currentPage, pageSize, list);

        // 根据请求的分页参数，拿到相应数据去获取基本信息
        List<WxMpUser> result = new ArrayList<>();
        if (list.size() == 0){
            return ResultUtil.success();
        }else{
            int startIndex = (page.getPageNum()-1) * pageSize;
            int endIndex = startIndex + page.getPageSize();
            result = this.wxService.getUserService().userInfoList(list.subList(startIndex,endIndex));
        }
        page.setList(result);
        return ResultUtil.pageFormat(page);
    }

    // 修改用户备注名
    @PostMapping("/updateRemark")
    public CommonResult userUpdateRemark( @RequestParam("openid") String openid,
                                 @RequestParam("remark") String remark) throws WxErrorException, IOException {
        this.wxService.getUserService().userUpdateRemark(openid, remark);
        return ResultUtil.success();
    }

    // 获取公众号的黑名单列表
    @GetMapping("/blacklist")
    public CommonResult blackList(@RequestParam(value = "nextOpenid",required = false) String nextOpenid,
                                  @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "5")  int pageSize) throws WxErrorException, IOException {
        WxMpUserBlacklistGetResult result = this.wxService.getBlackListService().getBlacklist(nextOpenid);
        if (result.getCount() > 0){
            PageInfo<String> page = PagerUtil.lowPager(currentPage, pageSize, result.getOpenidList());
            return ResultUtil.pageFormat(page);
        }else{
            return ResultUtil.success(new int[0]);
        }
    }

    // 分页直接获取黑名单列表基本信息
    @GetMapping("/blackInfoPage")
    public CommonResult blackInfoPage(@RequestParam(value = "nextOpenid", required = false) String nextOpenid,
                                     @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "5")  int pageSize) throws WxErrorException, IOException {
        // 先获取列表
        WxMpUserBlacklistGetResult blacklist = this.wxService.getBlackListService().getBlacklist(nextOpenid);
        PageInfo<WxMpUser> page = PagerUtil.lowPager(currentPage, pageSize, blacklist.getOpenidList());

        // 根据请求的分页参数，拿到相应数据去获取基本信息
        List<WxMpUser> result = new ArrayList<>();
        if (blacklist.getOpenidList().size() == 0){
            return ResultUtil.success(new int[0]);
        }else{
            int startIndex = (page.getPageNum()-1) * pageSize;
            int endIndex = startIndex + page.getPageSize();
            result = this.wxService.getUserService().userInfoList(blacklist.getOpenidList().subList(startIndex,endIndex));
        }
        page.setList(result);
        return ResultUtil.pageFormat(page);
    }

    // 拉黑用户
    @PostMapping("/pushBlackList")
    public CommonResult pushToBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException, IOException {

        this.wxService.getBlackListService().pushToBlacklist(openidList);
        return ResultUtil.success();

    }

    // 取消拉黑用户
    @PostMapping("/removeBlackList")
    public CommonResult removeFromBlacklist(@RequestParam(value = "openids") List<String> openidList) throws WxErrorException, IOException {
        this.wxService.getBlackListService().pullFromBlacklist(openidList);
        return ResultUtil.success();
    }


    // 授权获取 openid
    @GetMapping("/openid")
    public void getOpenid(@RequestParam(value = "isAuthPage") Boolean isAuthPage,
                          @RequestParam(value = "callbackUrl", required = false) String callBackUrl,
                          HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException, ServletException {

        String authUrl = "";
        if (isAuthPage) {
            authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/info" + "&response_type=code&scope=snsapi_userinfo&state=" + callBackUrl + "#wechat_redirect";
        } else {
            authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.properties.getAppId() + "&redirect_uri=" + request.getRequestURL() + "/code" + "&response_type=code&scope=snsapi_base&state=" + callBackUrl + "#wechat_redirect";

        }
        log.info("微信网页授权的 url：" + authUrl);
        response.sendRedirect(authUrl); // 重定向
    }

    // 返回 openid
    @GetMapping("/openid/code")
    public CommonResult getAccessToken(@RequestParam(value = "code") String code,
                                       @RequestParam(value = "state") String callbackUrl,
                                       HttpServletResponse response, HttpServletRequest request) throws WxErrorException, IOException {

        // 请求以下链接获取 access_token
        String url = "https://impl.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code=" + code + "&grant_type=authorization_code";
        String res = this.wxService.get(url, null);

        String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();


        if (callbackUrl.contains("www")) {  // 如果有传入 callbackurl，则跳转到 url
            if (!callbackUrl.contains("?")) {
                callbackUrl += "?";
            } else {
                callbackUrl += "&";
            }
            callbackUrl += "openid=" + openid;
            log.info("回调的页面：" + callbackUrl);
            // 跳转到回调页面
            response.sendRedirect(callbackUrl); // 重定向
            return ResultUtil.success();
        } else { // 没有传入 callbackurl，则返回用户 openid
            HashMap<String, String> map = new HashMap<>();
            map.put("openid", openid);
            return ResultUtil.success(map);
        }

    }

    @GetMapping("/openid/info")
    public CommonResult getUserInfo(@RequestParam(value = "code") String code,
                                    @RequestParam(value = "state") String callbackUrl,
                                    HttpServletResponse response, HttpServletRequest request) throws WxErrorException, IOException {

        WxMpOAuth2AccessToken accessTokenObject = this.wxService.oauth2getAccessToken(code);
        String accessToken = accessTokenObject.getAccessToken();
        String openid = accessTokenObject.getOpenId();


        WxMpUser user = this.wxService.oauth2getUserInfo(accessTokenObject, "zh_CN");

        if (callbackUrl.contains("www")) {  // 如果有传入 callbackurl，则跳转到 url
            if (!callbackUrl.contains("?")) {
                callbackUrl += "?";
            } else {
                callbackUrl += "&";
            }
//            callbackUrl += "userInfo=" + JsonUtil.toJson(user);
            log.info("回调的页面：" + callbackUrl);
            // 跳转到回调页面
            response.sendRedirect(callbackUrl); // 重定向
            return ResultUtil.success(JsonUtil.toJson(user));
        } else { // 没有传入 callbackurl，则返回用户 openid

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
            log.info("微信网页授权的 url：" + url);
            response.sendRedirect(url); // 重定向
        }
        // 跳转到回调的 url 并且返回 code
        @GetMapping("/openid/code")
        public void getAccessToken(@RequestParam(value = "code") String code,
                                     @RequestParam(value = "state") String callbackUrl,
                                      HttpServletResponse response,HttpServletRequest request) throws WxErrorException, IOException {

            // 请求以下链接获取 access_token
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.properties.getAppId() + "&secret=" + this.properties.getSecret() + "&code="+code+"&grant_type=authorization_code";
            String res = this.impl.get(url,null);

            String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();

            if( !callbackUrl.contains("?")){
                callbackUrl += "?";
            }else{
                callbackUrl += "&";
            }
            callbackUrl += "openid=" + openid;
            log.info("回调的页面：" + callbackUrl);
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
            String res = this.impl.get(getCodeUrl,null);

            String openid = new JsonParser().parse(res).getAsJsonObject().get("openid").getAsString();
            String accessToken = new JsonParser().parse(res).getAsJsonObject().get("access_token").getAsString();


            String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            String userInfo = this.impl.get(getUserInfo,null);

            log.info("回调的页面：" + callbackUrl);
            // 跳转到回调页面
            response.sendRedirect(callbackUrl); // 重定向
        }
        */

    /**
     * 根据接收的 publicCode 参数判断 access_token
     *
     * @param publicCode 公众号
     * @return void
     * @author huangyg
     */
    public void configStorage(String publicCode) throws WxErrorException, IOException {
        WxPublic checkPublic = publicService.get(publicCode);
        if (checkPublic == null) {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
        // 判断是否需要切换公众号
        if (!checkPublic.getAppSecret().equals(new WxMpInMemoryConfigStorage().getSecret())) {
            WxMpInMemoryConfigStorage config = publicService.switchPublic(checkPublic);
            wxService.setWxMpConfigStorage(config);
        }
    }

}
