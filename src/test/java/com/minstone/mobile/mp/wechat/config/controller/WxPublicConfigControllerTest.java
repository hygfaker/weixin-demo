package com.minstone.mobile.mp.wechat.config.controller;

import com.minstone.mobile.mp.wechat.publics.domain.WxPublicConfig;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicConfigService;
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
    public void addTest() throws Exception {
        WxPublicConfig publicConfig = new WxPublicConfig("publicCode",1,1,1,"offLineMsg","onlineMsg");
        String result = publicConfigService.add(publicConfig);
        log.info("insert result : {}",result);
        assertNotNull(result);
    }

    @Test
    public void updateTest() throws Exception {
        WxPublicConfig publicConfig = new WxPublicConfig("18dbdd9f7848469ea46c2e5e39dd2a96","publicCode",0,0,0,"offLineMsg","onlineMsg");
        boolean success = publicConfigService.update(publicConfig);
        log.info("update result : {}",success);
        assertNotEquals(false,success);
    }

    @Test
    public void getTest() throws Exception {
        WxPublicConfig publicConfig = new WxPublicConfig();
//        publicConfig.setConfigCode("configCode");
        publicConfig.setConfigCode("18dbdd9f7848469ea46c2e5e39dd2a96");
        WxPublicConfig result = publicConfigService.get(publicConfig);
        log.info("get result : {}",result);
    }

}