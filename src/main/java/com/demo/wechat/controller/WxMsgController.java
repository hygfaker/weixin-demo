package com.demo.wechat.controller;

import com.demo.wechat.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangyg on 2017/8/10.
 */
@RestController
@RequestMapping("autoreply")
public class WxMsgController {
//    @GetMapping("/subMsg")
//    public Result subscribeMsg(String msg){
//
//    }

    private static Logger logger = LoggerFactory.getLogger(WxMsgController.class);

    @GetMapping("/index")
    public String index(@RequestParam("code")String code){
        logger.error(code);
        return "test";
    }
}
