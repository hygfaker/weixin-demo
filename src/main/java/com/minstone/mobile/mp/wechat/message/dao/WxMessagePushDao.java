package com.minstone.mobile.mp.wechat.message.dao;

import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WxMessagePushDao {

    /**
     * 添加定点推送
     * @param record
     * @return int
     * @author huangyg
     */
    int insertSelective(WxMessagePush record);

    /**
     * 物理删除定点推送
     * @param record
     * @return int
     * @author huangyg
     */
    int delete(WxMessagePush record);

    /**
     * 物理删除定点推送
     * @param record
     * @return int
     * @author huangyg
     */
    int forceDelete(WxMessagePush record);

    /**
     * 批量逻辑删除定点推送
     * @param list 推送消息主键数组
     * @return int
     * @author huangyg
     */
    int deleteBatch(String[] list);

    /**
     * 批量物理删除定点推送
     * @param list 推送消息主键数组
     * @return int
     * @author huangyg
     */
    int forceDeleteBatch(String[] list);

    /**
     * 更新定点推送
     * @param record
     * @return int
     * @author huangyg
     */
    int updateByPrimaryKey(WxMessagePush record);

    /**
     * 修改开关
     * @param record
     * @return int
     * @author huangyg
     */
    int updateFlag(WxMessagePush record);

    /**
     * 获取定点消息
     * @param record 定点消息实体
     * @return com.minstone.mobile.mp.wechat.message.domain.WxMessagePush
     * @author huangyg
     */
    WxMessagePush select(WxMessagePush record);

    /**
     * 获取公众号下的定点推送
     * @param record 定点消息实体
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.domain.WxMessagePush>
     * @author huangyg
     */
    List<WxMessagePush> selectAll(WxMessagePush record);



}