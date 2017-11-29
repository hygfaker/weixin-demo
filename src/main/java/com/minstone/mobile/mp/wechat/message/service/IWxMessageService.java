package com.minstone.mobile.mp.wechat.message.service;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import me.chanjar.weixin.common.exception.WxErrorException;
import com.minstone.mobile.mp.wechat.message.dto.MessageDto;
/**
 * @author huangyg
 * @description
 * @since 2017/11/17
 */

public interface IWxMessageService {

    // ----------------- 提供的服务 -----------------//
    // 1-1. 保存消息
    // 2-1. 删除消息
    // 2-2. 批量删除消息
    // 2-3. 批量删除消息
    // 2-4. 批量删除消息
    // 3-1. 获取消息
    // 3-2. 获取消息列表
    // 4-1. 回复消息


    /**
     * 1-1. 保存消息
     * @param message 消息实体
     * @return com.minstone.mobile.mp.wechat.message.reply.WxMessage
     * @author huangyg
     */
    public WxMessage add(WxMessage message) throws WxErrorException;

    /**
     * 2-1. 删除消息
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean delete(WxMessage message) throws WxErrorException;

    /**
     * 2-2. 批量删除消息
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
//    public boolean forceDelete(WxMessage message) throws WxErrorException;
//
//    /**
//     * 2-3. 批量删除消息
//     * @param message 消息实体
//     * @return boolean
//     * @author huangyg
//     */
//    public boolean batchDelete(WxMessage message) throws WxErrorException;
//
//    /**
//     * 2-4. 强制批量删除消息
//     * @param message 消息实体
//     * @return boolean
//     * @author huangyg
//     */
//    public boolean batchForceDelete(WxMessage message) throws WxErrorException;

    /**
     * 3-1. 获取消息
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    public WxMessage getDetail(WxMessage message) throws WxErrorException;

    /**
     * 3-2. 获取消息分页
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    public PageInfo<WxMessage> getPage(WxMessage message, int currentPage, int pageSize) throws WxErrorException;



    /**
     * 4-1. 回复消息
     * @param message 消息实体
     * @return void
     * @author huangyg
     */

    public boolean replyMessage(WxMessage message) throws WxErrorException;
}
