package com.demo.wechat.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Created by huangyg on 2017/8/27.
 */
@RestController
@RequestMapping("data")
public class WxDataCubeController {

    @Autowired
    private WxMpService wxMpService;

    private static Logger logger = (Logger) LoggerFactory.getLogger(WxDataCubeController.class);
}
