package com.minstone.mobile.mp.wechat.message.service.impl;

import com.minstone.mobile.mp.wechat.message.dao.WxMessageDao;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangyg
 * @description
 * @since 2017/11/17
 */
@Service
public class WxMessageServiceImpl implements IWxMessageService {

    @Autowired
    private WxMessageDao messageDao;

    private static Logger logger = LoggerFactory.getLogger(WxMessageServiceImpl.class);

    // ----------------- 提供的服务 -----------------//
    // 1-1. 保存消息
    // 2-1. 删除消息
    // 2-2. 批量删除消息
    // 2-3. 批量删除消息
    // 2-4. 批量删除消息
    // 3-1. 获取消息


    /**
     * 1-1. 保存消息
     *
     * @param message 消息实体
     * @return com.minstone.mobile.mp.wechat.message.reply.WxMessage
     * @author huangyg
     */
    @Override
    public WxMessage add(WxMessage message) throws WxErrorException {
        int insertResult = messageDao.insertSelective(message);
        if (insertResult > 0){
            return message;
        }else{
            return null;
        }
    }

    /**
     * 2-1. 删除消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean delete(WxMessage message) throws WxErrorException {
        return false;
    }

    /**
     * 2-2. 批量删除消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean forceDelete(WxMessage message) throws WxErrorException {
        return false;
    }

    /**
     * 2-3. 批量删除消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean batchDelete(WxMessage message) throws WxErrorException {
        return false;
    }

    /**
     * 2-4. 强制批量删除消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean batchForceDelete(WxMessage message) throws WxErrorException {
        return false;
    }

    /**
     * 3-1. 获取消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean get(WxMessage message) throws WxErrorException {
        return false;
    }
}
