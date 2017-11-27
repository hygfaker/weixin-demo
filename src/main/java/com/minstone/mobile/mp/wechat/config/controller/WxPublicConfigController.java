package com.minstone.mobile.mp.wechat.config.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.config.service.IWxPublicConfigService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangyg
 * @description
 * @since 2017/11/24
 */
@RestController
@RequestMapping("config")
public class WxPublicConfigController {
    @Autowired
    IWxPublicConfigService publicConfigService;


    @PostMapping("/add")
    public CommonResult add(WxPublicConfig publicConfig) throws WxErrorException{
        return ResultUtil.success(publicConfigService.add(publicConfig));
    }


    @PostMapping("/update")
    public CommonResult update(WxPublicConfig publicConfig) throws WxErrorException{
        return ResultUtil.success(publicConfigService.update(publicConfig));
    }

    @GetMapping("/get")
    public CommonResult get(WxPublicConfig publicConfig) throws WxErrorException{
        return ResultUtil.success(publicConfigService.get(publicConfig));
    }

}
