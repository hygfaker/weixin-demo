package com.minstone.wechat.api.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.wechat.api.WxReplyService;
import com.minstone.wechat.common.CommonException;
import com.minstone.wechat.dao.WxPublicDao;
import com.minstone.wechat.dao.WxReplyDao;
import com.minstone.wechat.dao.WxReplyKeywordDao;
import com.minstone.wechat.dao.WxReplyRuleDao;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.common.ResultEnum;
import com.minstone.wechat.utils.ResultUtil;
import com.minstone.wechat.utils.code.IdGen;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
//    1-4.分页关键词回复内容列表
//    1-5.获取关键词回复内容列表

//    2.添加回复内容
//    2-1.添加、修改关注时消息回复内容
//    2-2.添加、修改非关键词消息默认回复内容
//    2-3.关键词回复 - 添加规则
//    2-4.批量更新关键词

//    3.修改开关。
//    3-1.开启、关闭关注时回复
//    3-2.开启、关闭非关键词消息默认回复
//    3-3.开启、关闭关键词回复

//    4-1.获取关键词回复内容列表（同1-4）
//    4-2.添加某个规则 (同2-3)
//    4-3.逻辑删除某个规则
//    4-4.物理删除某个规则
//    4-5.更新某个规则
//    4-6.开启、关闭某个规则
//    4-7.逻辑删除关键词
//    4-8.物理删除关键词

    @Autowired
    private WxReplyDao wxReplyDao;
    @Autowired
    private WxReplyRuleDao wxReplyRuleDao;
    @Autowired
    private WxReplyKeywordDao wxReplyKeywordDao;

    @Autowired
    private WxPublicDao wxPublicDao;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxReplyServiceImpl.class);


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
        if (selectResult == null) {
            logger.error("没有该公众号信息");
            throw new CommonException(404, "没有该公众号信息");
        }else {
            List<WxReply> selectList= wxReplyDao.selectByPulCodeAndReplyType(publicCode,replyType);
            if (selectList.size() == 0){
                logger.error("该公众号没有相应回复类型信息");
                throw new CommonException(404,"该公众号没有相应回复类型信息");
            }else {
                logger.info("获取成功：List<WxReply> = " + selectList);
                return selectList;
            }
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
        public String addReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{
            // 查询公众号是否存在
            if (wxPublicDao.selectPublicCode(publicCode) == null){
                logger.error("该公众号不存在");
                throw new CommonException(ResultEnum.PARAM_ERROR,"该公众号不存在");
            }else{
                List<WxReply> selectList= wxReplyDao.selectByPulCodeAndReplyType(publicCode,replyType);
                if (selectList.size() != 0){
                    if (wxReplyDao.updateContent(publicCode,content,replyType) < 1){
                        logger.error("更新公众号信息出错");
                        throw new CommonException(ResultEnum.SERVER_ERROR,"更新公众号信息出错");
                    }
                    logger.info("更新公众号信息成功");
                    return selectList.get(0).getReplyCode();
                }else {
                    WxReply wxReply = new WxReply(publicCode,content,replyType);
                    if (wxReplyDao.insert(wxReply) < 1){
                        logger.error("保存公众号信息出错");
                        throw new CommonException(ResultEnum.SERVER_ERROR,"保存公众号信息出错");
                    }
                    logger.info("保存公众号信息出错");
                    return wxReply.getReplyCode();
                }
            }
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
        public boolean updateReplyFlag(String publicCode, Integer useFlag, Integer replyType) throws WxErrorException{
            if (useFlag !=0 && useFlag != 1){
                throw new CommonException(400,"【useFlag】参数只能为 0 或者 1");
            }
            WxReply selectResult = this.getReplyByPubCodeAndReplyType(publicCode,replyType);
            if (selectResult == null){  // 回复信息不存在的时候
                throw new CommonException(404,"该公众号不存在");
            }else {
                if (wxReplyDao.updateReplyFlag(publicCode,replyType,useFlag) < 1){
                    logger.error("开启/关闭回复开关时出错");
                    return false;
                }else {
                    logger.info("开启/关闭回复开关成功");
                    return true;
                }
            }
        }

    /**
     *  4-8.物理删除关键词
     * @param keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     */
        @Override
        public boolean fDeleteKeyword(String keywordCode) throws WxErrorException,CommonException {
            if (wxReplyKeywordDao.fDeleteByPrimaryKey(keywordCode) < 1){
                logger.error("(物理)删除关键词出错");
                return false;
            }else{
                logger.info("(物理)删除关键词成功：keywordCode = " + keywordCode);
                return true;
            }
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
    public boolean followReplyFlag(String publicCode, Integer useFlag) throws WxErrorException,CommonException {
        return this.updateReplyFlag(publicCode,useFlag,0);
    }

    /**
     * 获取关键词规则下的所有关键词（包含删除）
     *
     * @param ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public List<WxReplyKeyword> selectByRuleCode(String ruleCode) throws WxErrorException, CommonException {
        List<WxReplyKeyword> selectResult = wxReplyKeywordDao.selectByRuleCode(ruleCode);
        if (selectResult.size() > 0) {
            return selectResult;
        } else {
            throw new CommonException(404, "该关键词规则不存在");
        }
    }
    /**
     * 获取单个关键词规则
     *
     * @param ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReplyRule getReplyRule(String ruleCode) throws WxErrorException,CommonException {
        List<WxReplyRule> selectResult = wxReplyRuleDao.selectByPrimaryKey(ruleCode);
        if (selectResult.size() > 0) {
            return selectResult.get(0);
        } else {
            throw new CommonException(404, "该关键词规则不存在");
        }
    }
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
    @Deprecated
    @Override
    public WxReply getInfoByReplyCode(@RequestParam String replyCode) throws WxErrorException{
        return wxReplyDao.selectByPrimaryKey(replyCode);
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
     * 1-4.分页获取关键词回复内容列表
     *
     * @param publicCode  公众号主键
     * @param currentPage 当前页
     * @param pageSize    每页显示数量
     * @return
     * @throws WxErrorException
     */
    @Override
    public PageInfo<WxReplyRule> getKeywordPage(String publicCode, int currentPage, int pageSize) throws WxErrorException {

        if (currentPage < 0 ){
            logger.error("【currentPage】参数必须大于0");
            throw new CommonException(ResultEnum.PARAM_ERROR,"【currentPage】参数必须大于0");
        }
        if (pageSize < 0 ){
            logger.error("【pageSize】参数必须大于0");
            throw new CommonException(ResultEnum.PARAM_ERROR,"【pageSize】参数必须大于0");
        }

        // 查找公众号是否存在
        String selectResult = wxPublicDao.selectPublicCode(publicCode);
        if (selectResult == null){
            logger.error("该公众号不存在");
            throw new CommonException(ResultEnum.PARAM_ERROR,"该公众号不存在");

        }else {
            PageHelper.startPage(currentPage,pageSize);
            List<WxReplyRule> list = wxReplyRuleDao.selectAll(publicCode);
            PageInfo<WxReplyRule> page = new PageInfo<>(list);
            return page;
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
     * 2-1.添加、修改关注时回复内容
     *
     * @param publicCode 公众号主键
     * @param content    回复的内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public String addFollowReply(String publicCode, String content) throws WxErrorException {
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
    public String addNormalReply(String publicCode, String content) throws WxErrorException {
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
    public String addReplyRule(WxReplyRule replyRule) throws WxErrorException,CommonException {

        // 查询公众号是否存在
        if (wxPublicDao.selectPublicCode(replyRule.getPublicCode()) == null) {
            throw new CommonException(404, "该公众号不存在");
        }

        List<WxReplyKeyword> keywords = replyRule.getKeywords();
        String ruleCode = IdGen.uuid();
        for (WxReplyKeyword keyword : keywords){
            keyword.setKeywordCode(IdGen.uuid());
            keyword.setRuleCode(ruleCode);
            // 设置默认值
            keyword.setDelFlag(0);
        }
        int insertKeyWordResult = wxReplyKeywordDao.insertBatch(keywords); // 保存关键词

        int insertRuleResult = -1;
        if (insertKeyWordResult > 0){
            replyRule.setRuleCode(ruleCode);
            // 设置默认值
            replyRule.setDelFlag(0);
            insertRuleResult = wxReplyRuleDao.insertSelective(replyRule); // 保存关键词规则
        }
        if (insertRuleResult > 0) {
            return ruleCode;
        } else {
            throw new CommonException(500, "服务器异常");
        }

    }

    /**
     * 2-4.批量更新关键词
     *
     * @param lists 关键词列表
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean updateKeywordBatch(List<WxReplyKeyword> lists) throws WxErrorException {
        if (wxReplyKeywordDao.updateBatch(lists) < 1) {
            logger.error("批量更新关键词出错");
            return false;
        }else {
            logger.info("批量更新关键词成功");
            return true;
        }
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
    public boolean normalReplyFlag(String publicCode, Integer useFlag) throws WxErrorException,CommonException {
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
    public boolean replyRuleFlag(String publicCode, Integer useFlag) throws WxErrorException,CommonException {
        return this.updateReplyFlag(publicCode,useFlag,2);
    }
    /**
     * 4-6.开启、关闭某个规则
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean updateRuleFlag(String ruleCode, int useFlag) throws WxErrorException,CommonException {
        if (useFlag !=0 && useFlag != 1){
            throw new CommonException(400,"【useFlag】参数只能为 0 或者 1");
        }
        if (wxReplyRuleDao.updateRuleFlag(ruleCode,useFlag) < 1){
            logger.error("开启/关闭关键词规则开关出错");
            return false;
        }else {
            logger.info("开启/关闭关键词规则开关成功");
            return true;
        }
    }

    //  todo :1.不要在循环里面写 dao 操作，改成批处理； 2.去除关键词可以改成接口操作。
    @Override
    public boolean updateRule(WxReplyRule replyRule) throws WxErrorException,CommonException {

        List<WxReplyKeyword>keywords = replyRule.getKeywords();

        if (keywords.size() < 0){
            logger.error("关键词规则中的关键词列表为空");
            throw new CommonException(404,"关键词列表为空");
        }
        ArrayList<WxReplyKeyword> updateList = new ArrayList<>();   //  记录需要更新的 keyword 的列表
        ArrayList<WxReplyKeyword> insertList = new ArrayList<>();   //  记录需要插入的 keyword 的列表
        for (WxReplyKeyword replyKeyword : keywords){
            replyKeyword.setRuleCode(replyRule.getRuleCode()); // 为所有关键词设置code
            if (replyKeyword.getKeywordCode() != null){     // 获取需要更新的关键规则的关键词
                updateList.add(replyKeyword);
            }else{
                replyKeyword.setKeywordCode(IdGen.uuid());
                replyKeyword.setDelFlag(0); // 默认不删除
                insertList.add(replyKeyword);
            }
        }
        if (updateList.size() > 0 && wxReplyKeywordDao.updateBatch(updateList) < 0){
//                return ResultUtil.failure(ResultEnum.SERVER_ERROR,"服务器异常 - 批量更新关键词时出错");
            logger.error("批量更新关键词时出错");
            return false;

        }

        if (insertList.size() > 0 && wxReplyKeywordDao.insertBatch(insertList) < 0){
//                return ResultUtil.failure(ResultEnum.SERVER_ERROR,"服务器异常 - 批量插入关键词时出错");
            logger.error("批量插入关键词时出错");
            return false;
        }
        if (wxReplyRuleDao.updateByPrimaryKeySelective(replyRule) < 0){

            logger.error("更新关键词规则失败");
            return false;
        }
        logger.info("更新关键词规则成功");
        return true;

//        if (wxReplyRuleDao.updateByPrimaryKeySelective(replyRule) > 0) return ResultUtil.success() ;
//        else return ResultUtil.failure(ResultEnum.SERVER_ERROR,"服务器异常 - 更新消息规则时出错");

    }


    /**
     * 4-3.逻辑删除某个规则（逻辑删除只会影响规则表，不会影响关键词表）
     *
     * @param ruleCode 规则主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean deleteRule(String ruleCode) throws WxErrorException,CommonException {
//        if (wxReplyKeywordDao.deleteByRuleCode(ruleCode) < 1) { // 删除规则中的关键字
//            logger.error("（逻辑）删除关键词时出错");
////            throw new CommonException(500,"服务器异常，删除关键词时出错");
//            return false;
//
//        }else
        if (wxReplyRuleDao.deleteByPrimaryKey(ruleCode) < 1){
            logger.error("（逻辑）删除关键词规则时出错");
            return false;
//            throw new CommonException(500,"服务器异常，删除关键词规则出错");
        }else {
            logger.info("（逻辑）删除关键词规则成功 - ruleCode:" + ruleCode);
            return true;
        }
    }
    /**
     * 4-4.物理删除某个规则（物理删除会删掉关键词的表）
     *
     * @param ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean fDeleteRule(String ruleCode) throws WxErrorException,CommonException {
        if (wxReplyKeywordDao.fDeleteByRuleCode(ruleCode) < 1) {
//            throw new CommonException(500,"服务器异常，删除关键词出错");
            logger.error("（物理）删除关键词出错");
            return false;
        }else if (wxReplyRuleDao.fDeleteByPrimaryKey(ruleCode) < 1) { // 删除规则
//            throw new CommonException(500,"服务器异常，删除关键词规则出错");
            logger.error("（物理）删除关键词规则出错");
            return false;
        }else {
            logger.info("（物理）删除关键词规则成功 - ruleCode:" + ruleCode);
            return true;
        }

    }
    /**
     * 4-5 更新某个规则（测试：如果没有该规则报什么错？）
     *
     * @param replyRule 规则内容
     * @return
     * @throws WxErrorException
     */

    /**
     *  4-7.逻辑删除关键词
     * @param keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean deleteKeyword(String keywordCode) throws WxErrorException,CommonException {
        if (wxReplyKeywordDao.deleteByPrimaryKey(keywordCode) < 1){
            logger.error("(逻辑)删除关键词出错");
            return false;
        }else{
            logger.info("(逻辑)删除关键词成功：keywordCode = " + keywordCode);
            return true;
        }
    }

    /**
     * 5-4.（逻辑）批量删除关键词规则
     *
     * @param ruleCodes 关键词规则回复主键们
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean deleteRuleBatch(String[] ruleCodes) throws WxErrorException,CommonException {
        if (wxReplyRuleDao.deleteBatch(ruleCodes) < 1){
            logger.error("（逻辑）批量删除关键词规则出错");
            return false;
        }else{
            logger.info("（逻辑）批量删除关键词规则成功：ruleCodes = " + ruleCodes);
            return true;
        }
    }

    @Override

    /**
     * 5-5.（物理）批量删除关键词规则
     *
     * @param ruleCodes 关键词规则回复主键们
     * @return
     * @throws WxErrorException
     */
    public boolean fDeleteRuleBatch(String[] ruleCodes) throws WxErrorException,CommonException {
        if (wxReplyRuleDao.fDeleteBatch(ruleCodes) < 1){
            logger.error("（物理）批量删除关键词规则出错");
            return false;
        }else if (wxReplyKeywordDao.fDeleteByRuleCodeBatch(ruleCodes) < 1) {
            logger.error("（物理）批量删除关键词规则出错");
            return false;
        }else{
            logger.info("（物理）批量删除关键词规则成功：ruleCodes = " + ruleCodes);
            return true;
        }
    }



}

