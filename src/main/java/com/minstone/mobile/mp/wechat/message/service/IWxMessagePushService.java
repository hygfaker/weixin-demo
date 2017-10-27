package com.minstone.mobile.mp.wechat.message.service;

import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangyg on 2017/10/24.
 */
@Service
@Transactional
public interface IWxMessagePushService {

    // TODO: 2017/10/24
    // 添加定点推送
    // 批量添加定点推送

    // 删除定点推送（逻辑）
    // 删除定点推送（物理）
    // 批量删除定点推送（逻辑）
    // 批量删除定点推送（物理）

    // 修改定点推送
    // 开启、关闭定点推送

    // 分页查看定点推送

    public WxMessagePush add(WxMessagePush wxMessagePush);


//    public int delete(WxMessagePush wxMessagePush);
//
//    public int fDelete(WxMessagePush wxMessagePush);
//
//    public int deleteBatch(WxMessagePush wxMessagePush);
//
//    public int fDeleteBatch(WxMessagePush wxMessagePush);
//
//    public int update(WxMessagePush wxMessagePush);
//
//    public int modifyPushFlag(WxMessagePush wxMessagePush);
//
//    public int getPage(WxMessagePush wxMessagePush);
}
