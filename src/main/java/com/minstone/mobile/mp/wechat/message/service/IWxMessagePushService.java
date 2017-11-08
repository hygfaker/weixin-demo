package com.minstone.mobile.mp.wechat.message.service;

import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangyg on 2017/10/24.
 */
@Service
public interface IWxMessagePushService {

    // ----------------- 提供的服务 -----------------//
    // 添加定点推送

    // TODO: 2017/11/8
    // 批量添加定点推送

    // 删除定点推送（逻辑）
    // 删除定点推送（物理）
    // 批量删除定点推送（逻辑）
    // 批量删除定点推送（物理）

    // 修改定点推送
    // 开启、关闭定点推送

    // 分页查看定点推送

    public WxMessagePush add(WxMessagePush wxMessagePush) throws WxErrorException;


    public boolean delete(WxMessagePush wxMessagePush);

    public boolean fDelete(WxMessagePush wxMessagePush);

    public boolean deleteBatch(WxMessagePush wxMessagePush);

    public boolean fDeleteBatch(WxMessagePush wxMessagePush);
//
    public boolean update(WxMessagePush wxMessagePush);
//
//    public int modifyPushFlag(WxMessagePush wxMessagePush);
//
//    public int getPage(WxMessagePush wxMessagePush);
}
