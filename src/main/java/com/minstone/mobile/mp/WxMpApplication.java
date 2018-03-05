package com.minstone.mobile.mp;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
@Log4j
public class WxMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMpApplication.class, args);
        log.info("========================= 微信运营管理启动成功 =========================");
    }

}
