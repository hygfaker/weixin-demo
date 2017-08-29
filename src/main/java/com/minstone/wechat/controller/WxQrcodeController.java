package com.minstone.wechat.controller;

import com.minstone.wechat.bean.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangyg on 2017/8/10.
 */
@RestController
@RequestMapping("/qrcode")
public class WxQrcodeController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * <pre>
     * 换取临时二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneid       场景值ID，临时二维码时为32位非0整型
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    @GetMapping("/createTmpTicket")
    public Result qrCodeCreateTmpTicket(@RequestParam("sceneid") int sceneid,
                                        @RequestParam(value = "expireSecondes") Integer expireSeconds) throws WxErrorException{
        return ResultUtil.success(wxMpService.getQrcodeService().qrCodeCreateTmpTicket(sceneid,expireSeconds));
    }

    /**
     * <pre>
     * 换取永久二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneid 场景值ID，最大值为100000（目前参数只支持1--100000）
     */
    @GetMapping("/createrLastTicket")
    public Result qrCodeCreateLastTicket(@RequestParam("sceneid") int sceneid) throws WxErrorException{
        return ResultUtil.success(wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneid));
    }

    /**
     * <pre>
     * 换取二维码图片url地址（可以选择是否生成压缩的网址）
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param ticket       二维码ticket
     * @param needShortUrl 是否需要压缩的二维码地址
     */
    @GetMapping("/qrocdeUrl")
    public Result  qrCodePictureUrl(@RequestParam("ticket") String ticket,@RequestParam(value = "needShortUrl",defaultValue = "true") boolean needShortUrl) throws WxErrorException{
        return ResultUtil.success(wxMpService.getQrcodeService().qrCodePictureUrl(ticket,needShortUrl));
    }

}
