package com.minstone.mobile.mp.config;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author huangyg
 * @description
 * @since 2018/2/6
 */

//@Configuration
////@EnableWebMvc
//@Log4j
//public class ResourceConfig extends WebMvcConfigurerAdapter {
//
//    @Value("${publicImgUploadPath}")
//    private String targetPath;
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info(targetPath);
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations(this.targetPath);
//    }
//
//}