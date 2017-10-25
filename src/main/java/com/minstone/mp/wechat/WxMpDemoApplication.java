package com.minstone.mp.wechat;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
@MapperScan("com.minstone.mp.wechat.dao")
public class WxMpDemoApplication {

    private static Logger logger = LoggerFactory.getLogger(WxMpDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
        logger.info("============ 微信运营管理启动成功 ============");
    }
    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        properties.setProperty("supprotMethodsArguments","true");
        pageHelper.setProperties(properties);
        logger.info("------------ 开始配置分页 PageHelper ------------");
        return pageHelper;
    }

}
