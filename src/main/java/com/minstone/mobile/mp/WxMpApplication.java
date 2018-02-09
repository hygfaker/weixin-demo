package com.minstone.mobile.mp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
public class WxMpApplication {

    private static Logger logger = LoggerFactory.getLogger(WxMpApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(WxMpApplication.class, args);
        logger.info("========================= 微信运营管理启动成功 =========================");
    }

}
