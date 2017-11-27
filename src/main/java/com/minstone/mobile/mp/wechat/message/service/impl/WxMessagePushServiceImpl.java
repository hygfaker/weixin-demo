package com.minstone.mobile.mp.wechat.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.ResultEnum;
import com.minstone.mobile.mp.utils.DateUtil;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.message.dao.WxMessagePushRecordDao;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePushRecord;
import com.minstone.mobile.mp.wechat.message.service.IWxMessagePushService;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.message.dao.WxMessagePushDao;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicDao;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

import java.util.List;
import java.util.Map;

/**
 * @author huangyg
 * @description
 * @since 2017/10/24
 */
@Service
@Transactional
public class WxMessagePushServiceImpl implements IWxMessagePushService {

    // 1-1. 添加定点推送


    // 2-1. 逻辑删除定点消息
    // 2-2. 物理删除定点消息
    // 2-3. 批量逻辑删除定点消息
    // 2-4. 批量物理删除定点消息

    // 3-1. 更新定点消息
    // 4-1. 修改定点消息状态

    // 5-1. 获取定点消息分页列表

    private static Logger logger = LoggerFactory.getLogger(IWxMessagePushService.class);

    @Autowired
    private WxMessagePushDao wxMessagePushDao;

    @Autowired
    private WxPublicDao wxPublicDao;

    @Autowired
    private Validator validator;

    @Autowired
    private WxMessagePushRecordDao messagePushRecordDao;

    /**
     * 1-1. 添加定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return com.minstone.mobile.mp.wechat.message.reply.WxMessagePush
     * @author huangyg
     */
    @Override
    public WxMessagePush add(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "publicCode",
                "pushName", "latitude", "longitude", "radius", "content");

        // 判断数据库是否存在名称相同的的定点推送消息
        WxMessagePush checkResult = wxMessagePushDao.select(wxMessagePush);
        if (checkResult != null){
            throw new CommonException(ResultEnum.LOCATION_PUSH_EXISTED);
        }

        // 构造 wxMessagePush 实体消息
        wxMessagePush.setPushCode(IdGen.uuid());
        // 默认不删除
        wxMessagePush.setDelFlag(0);
        // 默认开启定点消息推送
        wxMessagePush.setPushFlag(1);
        if (wxMessagePushDao.insertSelective(wxMessagePush) > 0) {
            return wxMessagePush;
        } else {
            throw new CommonException(ResultEnum.SAVE_LOCATION_PUSH_ERROR);
        }
    }

    /**
     * 2-1. 逻辑删除定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean delete(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushCode");
        if (wxMessagePushDao.select(wxMessagePush) != null) {
            return wxMessagePushDao.delete(wxMessagePush) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.LOCATION_PUSH_NOTFOUND);
        }
    }

    /**
     * 2-2. 物理删除定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean forceDelete(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushCode");
        if (wxMessagePushDao.select(wxMessagePush) != null) {
            return wxMessagePushDao.forceDelete(wxMessagePush) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.LOCATION_PUSH_NOTFOUND);
        }

    }

    /**
     * 2-3. 批量逻辑删除定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean deleteBatch(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushCodes");
        return wxMessagePushDao.deleteBatch(wxMessagePush.getPushCodes()) > 0 ? true : false;
    }

    /**
     * 2-4. 批量物理删除定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean forceDeleteBatch(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushCodes");
        return wxMessagePushDao.forceDeleteBatch(wxMessagePush.getPushCodes()) > 0 ? true : false;

    }

    /**
     * 3-1. 更新定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean update(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushCode",
                "pushName", "latitude", "longitude", "radius", "content");
        if (wxMessagePushDao.select(wxMessagePush) != null) {
            return wxMessagePushDao.updateByPrimaryKey(wxMessagePush) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.LOCATION_PUSH_NOTFOUND);
        }

    }

    /**
     * 4-1. 修改定点消息状态
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public boolean updateFlag(WxMessagePush wxMessagePush) throws WxErrorException {
        ValidatorUtil.mustParam(wxMessagePush, validator, "pushFlag","pushCode");
        if (wxMessagePushDao.select(wxMessagePush) != null) {
            return wxMessagePushDao.updateFlag(wxMessagePush) > 0 ? true : false;
        } else {
            throw new CommonException(ResultEnum.LOCATION_PUSH_NOTFOUND);
        }
    }

    /**
     * 5-1. 获取定点消息分页列表
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<WxMessagePush> getPage(WxMessagePush wxMessagePush, int currentPage, int pageSize) throws
            WxErrorException {
        if (currentPage < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }

        // 检查公众号是否存在
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(wxMessagePush.getPublicCode());
        if (checkPublicCode.size() == 0) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        } else {
            PageHelper.startPage(currentPage, pageSize);
            List<WxMessagePush> list = wxMessagePushDao.selectAll(wxMessagePush);
            PageInfo<WxMessagePush> page = new PageInfo<>(list);
            return page;
        }
    }

    /**
     * 5-2. 获取定点消息列表
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public List<WxMessagePush> getList(WxMessagePush wxMessagePush) throws WxErrorException{
        return this.getPage(wxMessagePush,1,99999).getList();
    }

    /**
     * 5-3. 获取定点消息
     *
     * @param wxMessagePush 定点消息实体
     * @return boolean
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public WxMessagePush get(WxMessagePush wxMessagePush) throws WxErrorException {
         ValidatorUtil.mustParam(wxMessagePush,validator,"pushCode");
         return wxMessagePushDao.select(wxMessagePush);
    }

    /**
     * 6-1. 添加推送记录
     *
     * @param messagePushRecord 消息记录实体
     * @return boolean
     * @author huangyg
     */
    @Override
    public WxMessagePushRecord addRecord(WxMessagePushRecord messagePushRecord) throws WxErrorException {
        ValidatorUtil.mustParam(messagePushRecord,validator,"userCode","pushCode");
        List<WxMessagePushRecord> checkResult = messagePushRecordDao.select(messagePushRecord);
        if (checkResult.size() > 0) {
            return null;
        }
        messagePushRecord.setDelFlag(0);
        messagePushRecord.setPushDate(DateUtil.getStringDate());
        messagePushRecord.setRecordCode(IdGen.uuid());
        // 推送状态，暂时没啥用
        messagePushRecord.setPushState(0);
        messagePushRecordDao.insert(messagePushRecord);
        return null;
    }

    /**
     * 7-1. 分页获取推送记录
     *
     * @param messagePushRecord 消息记录实体
     * @return boolean
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<WxMessagePushRecord> getRecord(WxMessagePushRecord messagePushRecord,int currentPage,int pageSize) throws WxErrorException {
        // 灵活获取记录列表，所以不进行参数校验。（不传获取全部）

        if (currentPage < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }

        PageHelper.startPage(currentPage, pageSize);
        List<WxMessagePushRecord> list = messagePushRecordDao.select(messagePushRecord);
        PageInfo<WxMessagePushRecord> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 7-2. 获取推送记录列表
     * @param wxMessagePushRecord 推送记录列表
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.reply.WxMessagePushRecord>
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public List<WxMessagePushRecord> getRecord(WxMessagePushRecord wxMessagePushRecord) throws WxErrorException{
        return this.getRecord(wxMessagePushRecord,1,99999).getList();
    }

    /**
     * 7-3.根据用户获取记录列表
     * @param userCode
     * @return java.util.List<java.lang.String>
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> getRecord(String userCode) throws WxErrorException{
        return messagePushRecordDao.selectByUserCode(userCode);
    }

    /**
     * 7-4.单位推送记录统计查询
     *
     * @param pushCode 定点消息主键
     * @param startDate 开始时间
     * @param startDate 结束时间
     * @return
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Map<String,Integer>> getRecordByDate(String pushCode,String startDate, String endDate,int currentPage,int pageSize) throws WxErrorException{
        if (currentPage < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }

        int len = startDate.length();

        PageHelper.startPage(currentPage,pageSize);

        List<Map<String,Integer>> list = messagePushRecordDao.selectRecordByDate(pushCode,startDate,endDate,len);
        PageInfo<Map<String,Integer>> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 7-5. 根据微信原始 id 和用户 id 获取定点消息表中可以推送的消息列表（如果定点消息记录表中有数据则不能推）
     * @param openId 微信原始 id
     * @param userCode 用户 id
     * @return java.util.List<com.minstone.mobile.mp.wechat.message.reply.WxMessagePush>
     * @author huangyg
     */
    @Transactional(readOnly = true)
    @Override
    public List<WxMessagePush> selectMessageByOpenIdAndUserCode(String openId,String userCode) throws WxErrorException{
        return wxMessagePushDao.selectMessageByOpenIdAndUserCode(openId,userCode);
    }
}
