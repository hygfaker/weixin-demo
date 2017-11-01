package com.minstone.mobile.mp.wechat.message.service.impl;

import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.wechat.message.service.IWxMessagePushService;
import com.minstone.mobile.mp.wechat.message.controller.WxMessagePushController;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.message.dao.WxMessagePushDao;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangyg
 * @description
 * @since 2017/10/24
 */
@Component
@Transactional
public class WxMessagePushServiceImpl implements IWxMessagePushService {
    private static Logger logger = LoggerFactory.getLogger(IWxMessagePushService.class);

    @Autowired
    private WxMessagePushDao wxMessagePushDao;

    @Override
    public WxMessagePush add(WxMessagePush wxMessagePush)  throws WxErrorException {
        // 构造 wxMessagePush 实体消息
        String pushCode = IdGen.uuid();
        wxMessagePush.setPushCode(pushCode);
        // 默认不删除
        wxMessagePush.setDelFlag(1);
        int insertResult = wxMessagePushDao.insertSelective(wxMessagePush);
        if (insertResult < 1){
            logger.error("插入定点消息出错");
            throw new CommonException(500,"插入定点消息出错");
        }else {
            logger.info("插入定点消息成功");
            return wxMessagePush;
        }
    }
}
