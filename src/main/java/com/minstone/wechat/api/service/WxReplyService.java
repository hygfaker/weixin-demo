package com.minstone.wechat.api.service;

import com.minstone.wechat.dao.WxReplyDao;
import com.minstone.wechat.dao.WxReplyKeywordDao;
import com.minstone.wechat.dao.WxReplyRuleDao;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import com.minstone.wechat.utils.code.IdGen;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/9/21.
 */
@Service
@Transactional
public class WxReplyService {

    @Autowired
    private WxReplyDao wxReplyDao;

    @Autowired
    private WxReplyRuleDao wxReplyRuleDao;

    @Autowired
    private WxReplyKeywordDao wxReplyKeywordDao;

    /**
     *  添加/修改回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public int addReplyContent(String publicCode, String content, Integer replyType) throws WxErrorException {
        // 先查询是否存在
        WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode,replyType);
         if (selectResult != null){ // 回复信息已经存在，则更新该回复信息
            return wxReplyDao.updateContent(publicCode,content,replyType);
        }else{
             WxReply wxReply = new WxReply(publicCode,content,replyType);
             return wxReplyDao.insert(wxReply);
        }
    }

    /**
     *  根据主键修改回复内容
     *
     * @param replyCode  消息回复主键
     * @param content   回复的内容
     * @return
     * @throws WxErrorException
     */
    public int updateContentByKey(String replyCode , String content) throws WxErrorException {
        return wxReplyDao.updateContentByKey(replyCode,content);
    }

    /**
     *  根据公众号主键和回复类型修改回复内容
     *
     * @param publicCode 公众号主键
     * @param content   回复内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    public int updateReplyContent(String publicCode , String content , Integer replyType) throws WxErrorException {
        return wxReplyDao.updateContent(publicCode,content,replyType);
    }

    /**
     *  根据主键修改回复开关
     *
     * @param replyCode 消息回复主键
     * @param replyFlag 回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    public int updateReplyFlagByKey(String replyCode , Integer replyFlag) throws WxErrorException {
        WxReply selectResult = this.getReplyByKey(replyCode);
        if (selectResult == null){ // 回复信息不存在的时候
            return -1;
        }else {
            return wxReplyDao.updateReplyFlagByKey(replyCode, replyFlag);
        }
    }

    /**
     *
     * 根据公众号主键和回复类型修改回复开关。有可能存在，有可能不存在
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @param replyFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
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
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR,"找不到该信息，更新失败");
        }else{
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }
    /***********************  添加关键词规则  **********************/
    // 添加关键词回复规则
    public int addReplyRule(WxReplyRule replyRule){

        List<WxReplyKeyword> keywords = replyRule.getKeywords();
        String ruleCode = IdGen.uuid();
        for (WxReplyKeyword keyword : keywords){
            keyword.setKeywordCode(IdGen.uuid());
            keyword.setRuleCode(ruleCode);
        }
        int insertKeyWordResult = wxReplyKeywordDao.insertPatch(keywords);

        int insertRuleResult = -1;
        if (insertKeyWordResult > 0){
            replyRule.setRuleCode(ruleCode);
            insertRuleResult = wxReplyRuleDao.insert(replyRule);
        }

        return insertRuleResult;
    }

    // 物理删除
    public int FDeleteRule(String ruleCode){
        int keywordDelete = wxReplyKeywordDao.FDeleteByRuleCode(ruleCode);
        int ruleDelete = -1;
        if (keywordDelete > 0){
            ruleDelete = wxReplyRuleDao.FDeleteByPrimaryKey(ruleCode); // 删除规则
        }
        return ruleDelete;
    }

    // 逻辑删除
    public int deleteRule(String ruleCode){
        int keywordDelete = wxReplyKeywordDao.deleteByRuleCode(ruleCode); // 删除规则中的关键字
        int ruleDelete = -1;
        if (keywordDelete > 0){
            ruleDelete = wxReplyRuleDao.deleteByPrimaryKey(ruleCode); // 删除规则
        }
        return ruleDelete;
    }

    // 查找关键词回复规则 - 根据规则主键
    public WxReplyRule findRuleByKey(String ruleCode){
        // 查找规则表
        WxReplyRule replyRule = wxReplyRuleDao.selectByPrimaryKey(ruleCode);
        // 查找关键字表
        List<WxReplyKeyword> keywords = wxReplyKeywordDao.selectByPrimaryKey(ruleCode);
        replyRule.setKeywords(keywords);
        return replyRule;
    }



    // 查找某条规则里面的关键词内容
    public List<WxReplyKeyword> findKeywords(String ruleCode){
        return wxReplyKeywordDao.selectByPrimaryKey(ruleCode);
    }

    // 根据公众号主键查找所有关键词回复规则，根据时间
    public List<WxReplyRule> findRules(String publicCode){
        List<WxReplyRule> replyRules =  wxReplyRuleDao.selectAll(publicCode);
        return replyRules;
    }


}
