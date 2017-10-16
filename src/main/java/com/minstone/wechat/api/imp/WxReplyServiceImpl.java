package com.minstone.wechat.api.imp;

import com.minstone.wechat.api.WxReplyService;
import com.minstone.wechat.dao.WxPublicDao;
import com.minstone.wechat.dao.WxReplyDao;
import com.minstone.wechat.dao.WxReplyKeywordDao;
import com.minstone.wechat.dao.WxReplyRuleDao;
import com.minstone.wechat.domain.WxPublic;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.utils.code.IdGen;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by huangyg on 2017/9/21.
 */

@Component
@Transactional
public class WxReplyServiceImpl implements WxReplyService {
//    todo
//    1.根据主键获取回复内容
//    1.根据公众号主键和回复类型获取回复内容
//    1-1.获取消息回复内容列表（全部）
//    1-2.获取关注时回复内容消息
//    1-3.获取非关键词消息默认回复
//    1-4.获取关键词回复内容列表

//    2.添加回复内容
//    2-1.添加、修改关注时消息回复内容
//    2-2.添加、修改非关键词消息默认回复内容
//    2-3.关键词回复 - 添加规则

//    3.修改开关。
//    3-1.开启、关闭关注时回复
//    3-2.开启、关闭非关键词消息默认回复
//    3-3.开启、关闭关键词回复

//    4-1.获取关键词回复内容列表（同1-4）
//    4-2.添加某个规则 (同2-3)
//    4-3.逻辑删除某个规则
//    4-4.物理删除某个规则
//    4-5.编辑某个规则
//    4-6.开启、关闭某个规则

    @Autowired
    private WxReplyDao wxReplyDao;
    @Autowired
    private WxReplyRuleDao wxReplyRuleDao;
    @Autowired
    private WxReplyKeywordDao wxReplyKeywordDao;

    @Autowired
    private WxPublicDao wxPublicDao;

    /**
     * 添加公众号的时候，初始化【消息回复】数据
     * @throws WxErrorException
     */
    @Override
    public void initData() throws WxErrorException {

    }

    /**
     * 1.根据主键获取回复内容
     *
     * @param replyCode
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReply getInfoByReplyCode(@RequestParam String replyCode) throws WxErrorException{
        return wxReplyDao.selectByPrimaryKey(replyCode);
    }

    /**
     *  1.根据公众号主键和回复类型获取回复内容
     *  查找的时候先查看数据库是否有相应公众号，在查相关数据。
     *
     * @param publicCode 公众号主键
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getInfo(String publicCode ,Integer replyType) throws WxErrorException{
        String selectResult = wxPublicDao.selectPublicCode(publicCode);
        if (selectResult != null){
            return wxReplyDao.selectByPulCodeAndReplyType(publicCode,replyType);
        }else {
            return null;
        }
    }

    /**
     * 1-1.获取消息回复内容列表（全部）
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getList(String publicCode) throws WxErrorException {
        return  wxReplyDao.selectByPulCode(publicCode);
    }

    /**
     * 1-2.获取关注时回复内容消息
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getFollowInfo(String publicCode) throws WxErrorException {
        return this.getInfo(publicCode,0);
    }

    /**
     * 1-3.获取非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getNormalInfo(String publicCode) throws WxErrorException {
        return this.getInfo(publicCode,1);
    }

    /**
     * 1-4.获取关键词回复内容列表
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReplyRule> getKeyWordInfo(String publicCode) throws WxErrorException {
        // 查找公众号是否存在
        String selectResult = wxPublicDao.selectPublicCode(publicCode);
        if (selectResult != null){
            return wxReplyRuleDao.selectAll(publicCode);
        }else {
            return null;
        }
    }

    //    根据公众号主键和回复类型获取回复内容。获取列表，防止数据库数据混乱，多条数据相同
    public WxReply getReplyByPubCodeAndReplyType(String publicCode, Integer replyType){
        List<WxReply> list = wxReplyDao.selectByPulCodeAndReplyType(publicCode,replyType);
        if (list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     *  2.添加回复内容,添加的时候应该判断数据库是否有 public_code 和 reply_type 是否有重复
     *  用于关注回复、非关键词回复
     *
     * @param publicCode 公众号主键
     * @param content   回复的内容
     * @param replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public int addReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{

        // 查询公众号是否存在
        if (wxPublicDao.selectPublicCode(publicCode) == null) return ResultEnum.PUBLIC_NOTFOUND.getCode(); // 公众号不存在，404

        // 查询是否存在该回复信息
        WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode,replyType);
        if (selectResult != null){ // 回复信息已经存在，则更新该回复信息
            return  wxReplyDao.updateContent(publicCode,content,replyType);
        }else{
            WxReply wxReply = new WxReply(publicCode,content,replyType);
            return wxReplyDao.insert(wxReply);
        }
    }

    /**
     * 2-1.添加、修改关注时回复内容
     *
     * @param publicCode 公众号主键
     * @param content    回复的内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public int addFollowReply(String publicCode, String content) throws WxErrorException {
        return this.addReplyContent(publicCode,content,0);
    }

    /**
     * 2-2.添加、修改非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @param content    回复的内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public int addNormalReply(String publicCode, String content) throws WxErrorException {
        return this.addReplyContent(publicCode,content,1);
    }

    /**
     *  2-3.关键词回复 - 添加规则
     *
     * @param replyRule 关键词回复规则
     * @return
     * @throws WxErrorException
     */
    @Override
    public int addReplyRule(WxReplyRule replyRule) throws WxErrorException {

        // 查询公众号是否存在
        if (wxPublicDao.selectPublicCode(replyRule.getPublicCode()) == null) return ResultEnum.PUBLIC_NOTFOUND.getCode(); // 公众号不存在，404

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

    /**
     *
     * 3.修改开关。
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @param useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    @Override
    public int updateReplyFlag(String publicCode, Integer useFlag, Integer replyType) throws WxErrorException{
        WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode,replyType);
        if (selectResult == null){  // 回复信息不存在的时候
            return -1;
        }else {
            return wxReplyDao.updateReplyFlag(publicCode,replyType,useFlag);
        }
    }


    //  abstract method - 根据主键修改回复开关
    public int updateReplyFlag(String replyCode, Integer useFlag) throws WxErrorException{
        return wxReplyDao.updateReplyFlagByKey(replyCode,useFlag);
    }

    /**
     * 3-1.开启/关闭，关注时回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public int followReplyFlag(String publicCode, Integer useFlag) throws WxErrorException {
        return this.updateReplyFlag(publicCode,useFlag,0);
    }

    /**
     * 3-2.开启/关闭，非关键词回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public int normalReplyFlag(String publicCode, Integer useFlag) throws WxErrorException {
        return this.updateReplyFlag(publicCode,useFlag,1);
    }

    /**
     * 3-3.开启、关闭关键词回复
     *
     * @param publicCode 公众号主键
     * @param useFlag  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public int keywordReplyFlag(String publicCode, Integer useFlag) throws WxErrorException {
        return this.updateReplyFlag(publicCode,useFlag,2);
    }

    /**
     * 4-3.逻辑删除某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public int deleteRule(String ruleCode) throws WxErrorException {
        int keywordDelete = wxReplyKeywordDao.deleteByRuleCode(ruleCode); // 删除规则中的关键字
        int ruleDelete = -1;
        if (keywordDelete > 0){ // 成功删除关键字后再删除规则
            ruleDelete = wxReplyRuleDao.deleteByPrimaryKey(ruleCode);
        }
        return ruleDelete;
    }

    /**
     * 4-4.物理删除某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public int FDeleteRule(String ruleCode) throws WxErrorException {
        int keywordDelete = wxReplyKeywordDao.FDeleteByRuleCode(ruleCode);
        int ruleDelete = -1;
        if (keywordDelete > 0){
            ruleDelete = wxReplyRuleDao.FDeleteByPrimaryKey(ruleCode); // 删除规则
        }
        return ruleDelete;
    }

    /**
     * 4-5.编辑某个规则（测试：如果没有该规则报什么错？）
     *
     * @param replyRule 规则内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public int editRule(WxReplyRule replyRule) throws WxErrorException {
        List<WxReplyKeyword>keywords = replyRule.getKeywords();
        if (keywords.size() > 0){
            for (WxReplyKeyword replyKeyword : keywords){
//                todo 编写更新关键词，当新增加关键词....
                if (replyKeyword.getKeywordCode())
                wxReplyKeywordDao.updateByPrimaryKeySelective(replyKeyword);
            }
        }
        return wxReplyRuleDao.updateByPrimaryKeySelective(replyRule);
    }

    /**
     * 4-6.开启、关闭某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public int updateRuleFlag(String ruleCode, int useFlag) throws WxErrorException {
        return wxReplyRuleDao.updateRuleFlag(ruleCode,useFlag);
    }

}

