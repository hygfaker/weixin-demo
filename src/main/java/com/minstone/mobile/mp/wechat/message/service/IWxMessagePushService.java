package com.minstone.mobile.mp.wechat.message.service;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/10/24.
 */

public interface IWxMessagePushService {

    // ----------------- 提供的服务 -----------------//
    // 1-1. 添加定点推送


    // 2-1. 逻辑删除定点消息
    // 2-2. 物理删除定点消息
    // 2-3. 批量逻辑删除定点消息
    // 2-4. 批量物理删除定点消息

    // 3-1. 更新定点消息
    // 4-1. 修改定点消息状态

    // 5-1. 获取定点消息分页
    // 5-2. 获取定点消息列表
    // 5-3. 获取定点消息

    // 6-1. 添加推送记录
    // 7-1. 获取推送记录

    /**
     * 1-1. 添加定点消息
     * @param wxMessagePush 定点消息实体
     * @return com.minstone.mobile.mp.wechat.message.domain.WxMessagePush
     * @author huangyg
     */
    public WxMessagePush add(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 2-1. 逻辑删除定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean delete(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 2-2. 物理删除定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean forceDelete(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 2-3. 批量逻辑删除定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean deleteBatch(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 2-4. 批量物理删除定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean forceDeleteBatch(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 3-1. 更新定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean update(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 4-1. 修改定点消息状态
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public boolean updateFlag(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 5-1. 获取定点消息分页
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public PageInfo<WxMessagePush> getPage(WxMessagePush wxMessagePush,int currentPage, int pageSize) throws WxErrorException;

    /**
     * 5-2. 获取定点消息列表
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public List<WxMessagePush> getList(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 5-3. 获取定点消息
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    public WxMessagePush get(WxMessagePush wxMessagePush) throws WxErrorException;

    /**
     * 6-1. 添加推送记录
     * @param wxMessagePushRecord 消息记录实体
     * @return boolean
     * @author huangyg
     */
    public WxMessagePushRecord addRecord(WxMessagePushRecord wxMessagePushRecord) throws WxErrorException;

    /**
     * 7-1. 分页获取推送记录
     * @param wxMessagePushRecord 消息记录实体
     * @return boolean
     * @author huangyg
     */
    public PageInfo<WxMessagePushRecord> getRecord(WxMessagePushRecord wxMessagePushRecord,int currentPage,int PageSize) throws WxErrorException;



    /**
     * 7-2. 获取推送记录列表
     * @param wxMessagePushRecord 推送记录列表
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord>
     * @author huangyg
     */
    public List<WxMessagePushRecord> getRecord(WxMessagePushRecord wxMessagePushRecord) throws WxErrorException;

    /**
     * 7-3.根据用户获取记录列表
     * @param userCode
     * @return java.util.List<java.lang.String>
     * @author huangyg
     */
    public List<String> getRecord(String userCode) throws WxErrorException;

    /**
     * 7-4.单位推送记录统计查询
     *
     * @param startDate 开始时间
     * @param startDate 开始时间
     * @return
     * @author huangyg
     */
    public PageInfo<Map<String,Integer>> getRecordByDate(String pushCode,String startDate, String endDate,int currentPage,int pageSize) throws WxErrorException;

    /**
     * 7-5. 根据微信原始 id 和用户 id 获取定点消息表中可以推送的消息列表（如果定点消息记录表中有数据则不能推）
     * @param openId 微信原始 id
     * @param userCode 用户 id
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.domain.WxMessagePush>
     * @author huangyg
     */
    public List<WxMessagePush> selectMessageByOpenIdAndUserCode(String openId,String userCode) throws WxErrorException;
}
