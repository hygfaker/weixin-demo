package com.minstone.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
@MapperScan("com.minstone.wechat.dao")
public class WxMpDemoApplication {

    private static Logger logger = LoggerFactory.getLogger(WxMpDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
        logger.info("微信运营管理启动成功....");
    }

}
