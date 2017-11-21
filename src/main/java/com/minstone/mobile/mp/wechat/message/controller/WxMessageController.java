package com.minstone.mobile.mp.wechat.message.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangyg
 * @description
 * @since 2017/11/17
 */

@RestController

public class WxMessageController {
    @PostMapping("test")
    public CommonResult test(@RequestParam String app_id,@RequestParam String app_type,@RequestParam String dataVersion,@RequestParam String user_id){
        return ResultUtil.success("app_id : " + app_id + "   app_type : " + app_type + "    dataVersion : " + dataVersion+ "   user_id : " + user_id);
    }
}
