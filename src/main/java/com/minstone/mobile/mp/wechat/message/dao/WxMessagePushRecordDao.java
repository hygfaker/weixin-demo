package com.minstone.mobile.mp.wechat.message.dao;

import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WxMessagePushRecordDao {

    /**
     * 物理删除记录
     * @param record
     * @return int
     * @author huangyg
     */
    int forceDelete(WxMessagePushRecord record);

    /**
     * 逻辑删除记录
     * @param record
     * @return int
     * @author huangyg
     */
    int delete(WxMessagePushRecord record);


    /**
     * 添加消息记录
     * @param record
     * @return int
     * @author huangyg
     */
    int insert(WxMessagePushRecord record);

    /**
     * 添加消息记录（参数为空则不添加）
     * @param record
     * @return int
     * @author huangyg
     */
    int insertSelective(WxMessagePushRecord record);


    /**
     * 获取消息记录
     * @param record 消息记录实体
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord>
     * @author huangyg
     */
    List<WxMessagePushRecord> select(WxMessagePushRecord record);

    List<String> select(String userCode);

//    List<WxMessagePushRecord> selectAll(WxMessagePushRecord record);

    // TODO: 2017/11/9 直接根据微信原始 id 和用户获取记录（没拿来用，考虑数据库性能...）
    WxMessagePushRecord selectRecord(WxMessagePushRecord record);

    /**
     * 参数为空则不更新
     * @param record
     * @return int
     * @author huangyg
     */
    int update(WxMessagePushRecord record);

}