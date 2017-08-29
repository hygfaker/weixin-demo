package com.demo.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
@MapperScan("com.demo.wechat.mapper")
public class WxMpDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
    }
}
