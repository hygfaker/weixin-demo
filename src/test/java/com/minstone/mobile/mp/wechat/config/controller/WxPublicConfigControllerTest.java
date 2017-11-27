package com.minstone.mobile.mp.wechat.config.controller;

import com.minstone.mobile.mp.wechat.config.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.config.service.IWxPublicConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author huangyg
 * @description
 * @since 2017/11/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WxPublicConfigControllerTest {

    @Autowired
    private IWxPublicConfigService publicConfigService;

    @Test
    public void addOrModifyFlag() throws Exception {

        WxPublicConfig publicConfig = new WxPublicConfig("configCode","publicCode",1,1,1,"offLineMsg","onlineMsg");
        String result = publicConfigService.addOrModifyFlag(publicConfig);
        log.info(result);
        assertNotNull(result);
    }

}