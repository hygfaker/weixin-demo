package com.minstone.mobile.mp.wechat.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.ResultEnum;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.message.dao.WxMessageDao;
import com.minstone.mobile.mp.wechat.message.domain.WxMessage;
import com.minstone.mobile.mp.wechat.message.service.IWxMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;


/**
 * @author huangyg
 * @description
 * @since 2017/11/17
 */
@Service
public class WxMessageServiceImpl implements IWxMessageService {

    @Autowired
    private WxMessageDao messageDao;

    @Autowired
    private Validator validator;

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
        if (insertResult > 0) {
            return message;
        } else {
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
     * 3-1. 获取消息详情
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public WxMessage getDetail(WxMessage message) throws WxErrorException {
        ValidatorUtil.mustParam(message, validator, "msgCode");
        WxMessage selectResult = messageDao.selectByPrimaryKey(message.getMsgCode());
        if (selectResult == null) {
            throw new CommonException(ResultEnum.MESSAGE_NOTFOUND);
        }
        return selectResult;
    }

    /**
     * 3-2. 获取消息分页
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public PageInfo<WxMessage> getPage(WxMessage message, int currentPage, int pageSize) throws WxErrorException {
        if (currentPage < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        ValidatorUtil.mustParam(message, validator, "publicCode");
        message.setDayLimit(message.getDayLimit()!=null ? message.getDayLimit() : 5);
        List<WxMessage> messageList = messageDao.selectAll(message);

        PageHelper.startPage(currentPage, pageSize);
        PageInfo<WxMessage> pageInfo = new PageInfo<>(messageList);
        return pageInfo;
    }



    /**
     * 4-1. 回复消息（其实就是更新数据）
     *
     * @return void
     * @author huangyg
     */
    @Override
    public boolean replyMessage(WxMessage message) throws WxErrorException {
        ValidatorUtil.mustParam(message, validator, "msgCode", "replyContent");
        WxMessage selectResult = messageDao.selectByPrimaryKey(message.getMsgCode());
        if (selectResult == null){
            throw new CommonException(ResultEnum.MESSAGE_NOTFOUND);
        }
        return messageDao.updateByPrimaryKeySelective(message) > 0 ? true : false;
    }


    /**
     * 2-2. 批量删除消息
     *
     * @param message 消息实体
     * @return boolean
     * @author huangyg
     */
//    @Override
//    public boolean forceDelete(WxMessage message) throws WxErrorException {
//        return false;
//    }
//
//    /**
//     * 2-3. 批量删除消息
//     *
//     * @param message 消息实体
//     * @return boolean
//     * @author huangyg
//     */
//    @Override
//    public boolean batchDelete(WxMessage message) throws WxErrorException {
//        return false;
//    }
//
//    /**
//     * 2-4. 强制批量删除消息
//     *
//     * @param message 消息实体
//     * @return boolean
//     * @author huangyg
//     */
//    @Override
//    public boolean batchForceDelete(WxMessage message) throws WxErrorException {
//        return false;
//    }


}
