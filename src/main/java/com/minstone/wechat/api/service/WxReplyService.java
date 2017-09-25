package com.minstone.wechat.api.service;

import com.minstone.wechat.dao.WxReplyDao;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangyg on 2017/9/21.
 */
@Service
@Transactional
public class WxReplyService {

    @Autowired
    private WxReplyDao wxReplyDao;

    //    添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
    public int addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException {
        // 先查询是否存在
        WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode,replyType);
        if (selectResult != null){ // 回复信息已经存在
            return -1;
        }else{
            WxReply wxReply = new WxReply(publicCode,content,replyType);
            return wxReplyDao.insert(wxReply);
        }
    }

    //    根据主键修改回复内容
    public int updateContentByKey(String replyCode , String content) throws WxErrorException {
        return wxReplyDao.updateContentByKey(replyCode,content);
    }

    //    根据公众号主键和回复类型修改回复内容
    public int updateReplyContent(String publicCode , String content , Integer replyType) throws WxErrorException {
        return wxReplyDao.updateContent(publicCode,content,replyType);
    }

    //    根据主键修改回复开关
    public int updateReplyFlagByKey(String replyCode , Integer replyFlag) throws WxErrorException {
        WxReply selectResult = this.getReplyByKey(replyCode);
        if (selectResult == null){ // 回复信息不存在的时候
            return -1;
        }else {
            return wxReplyDao.updateReplyFlagByKey(replyCode, replyFlag);
        }
    }

    //    根据公众号主键和回复类型修改回复开关。有可能存在，有可能不存在
    public int updateReplyFlag(String publicCode, Integer replyType, Integer replyFlag) throws WxErrorException{
        WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode , replyType);
        if (selectResult == null){  // 回复信息不存在的时候
            return -1;
        }else {
            return wxReplyDao.updateReplyFlag(publicCode,replyType,replyFlag);
        }
    }

    //    根据公众号主键和回复类型获取回复内容以及开关,获取列表，防止数据库数据混乱，多条数据相同
    public WxReply getReplyByPubCodeAndReplyType(String publicCode, Integer replyType){
        List<WxReply> list = wxReplyDao.selectByPulCodeAndReplyType(publicCode,replyType);
        if (list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    //    根据主键获取回复内容以及开关
    public WxReply getReplyByKey(String replyCode){
        return wxReplyDao.selectByPrimaryKey(replyCode);
    }

    //    根据 dao 返回的数据做结果判断
    public Result returnResult(Integer result){
        if (result > 0){
            return ResultUtil.success();
        }else if (result == 0 || result == -1){ // update时候：0表示失败
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR,"找不到该回复信息，更新失败");
        }else{
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }
}
