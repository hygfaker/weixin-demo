package com.minstone.mobile.mp.wechat.reply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.utils.ValidatorUtil;
import com.minstone.mobile.mp.wechat.publics.service.impl.WxPublicServiceImpl;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.utils.code.IdGen;
import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import com.minstone.mobile.mp.wechat.publics.dao.WxPublicDao;
import com.minstone.mobile.mp.wechat.reply.dao.WxReplyDao;
import com.minstone.mobile.mp.wechat.reply.dao.WxReplyKeywordDao;
import com.minstone.mobile.mp.wechat.reply.dao.WxReplyRuleDao;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyKeyword;
import com.minstone.mobile.mp.common.ResultEnum;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyg on 2017/9/21.
 */

@Service
@Transactional
public class WxReplyServiceImpl implements IWxReplyService {
//    todo

//    0. 初始化数据

//    ===== 关注时回复 =====
//    1-1. 获取关注时回复
//    1-2. 开启、关闭关注时回复开关
//    1-3. 添加、修改关注时回复内容

//    ===== 非关键词回复 =====
//    2-1. 获取非关键词回复
//    2-2. 开启、关闭非关键词回复开关
//    2-3. 添加、修改非关键词回复内容

//    ===== 关键词回复 =====
//    3-1. 获取关键词规则列表（分页）
//    3-1-1. 获取单个关键词规则
//    3-1-2. 获取关键词规则下的关键词列表（分页）
//    3-1-3. 根据公众号获取所有关键词（分页）

//    3-2. 开启、关闭所有关键词回复开关

//    3-3. 添加关键词规则（添加跟修改在接口看下能不能合并）
//    3-3-1. 添加关键词
//    3-3-2. 批量添加关键词


//    3-4. 删除关键词规则（物理逻辑）
//    3-4-1. 批量删除关键词规则（物理逻辑）
//    3-4-2. 删除关键词（物理逻辑）
//    3-4-3. 批量删除关键词（物理逻辑）

//    3-6. 修改关键词规则
//    3-6-1. 修改关键词
//    3-6-2. 批量修改关键词

//    3-7. 开启、关闭一个关键词回复开关

    // TODO: 2017/11/8 mapper 还没完善
//    4-1. 根据公众号、关键词获取回复消息

    @Autowired
    private WxReplyDao wxReplyDao;
    @Autowired
    private WxReplyRuleDao wxReplyRuleDao;
    @Autowired
    private WxReplyKeywordDao wxReplyKeywordDao;
    @Autowired
    private WxPublicDao wxPublicDao;
    @Autowired
    private Validator validator;

    private static Logger logger = LoggerFactory.getLogger(WxPublicServiceImpl.class);

    /**
     * 添加公众号的时候，初始化【消息回复】数据
     *
     * @throws WxErrorException
     */
    @Override
    public void initData() throws WxErrorException {

    }

    /************ 封装的方法 ************/
    /**
     * 1.根据公众号主键和回复类型获取回复内容
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     *              replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getInfo(WxReply reply) throws WxErrorException {
        ValidatorUtil.param(reply, validator, "publicCode", "replyType");
        // 检查公众号是否存在
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(reply);
        if (checkPublicCode.size() == 0) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        } else {
            List<WxReply> selectList = wxReplyDao.selectByPublicCodeAndReplyType(reply);
            if (selectList.size() == 0) {
                throw new CommonException(ResultEnum.REPLY_TYPE_NOTFOUND);
            } else {
                logger.info("获取成功：List<WxReply> = " + selectList);
                return selectList;
            }
        }
    }

    /**
     * 2.添加回复内容
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     *              content   回复的内容
     *              replyType 消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReply addContent(WxReply reply) throws WxErrorException {
        ValidatorUtil.param(reply, validator, "publicCode", "content");
        // 检查公众号是否存在
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(reply);
        if (checkPublicCode.size() == 0) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        } else {
            List<WxReply> selectList = wxReplyDao.selectByPublicCodeAndReplyType(reply);
            if (selectList.size() > 0) {
                if (wxReplyDao.updateContent(reply) < 1) {
                    throw new CommonException(ResultEnum.UPDATE_REPLY_CONTENT_ERROR);
                }
                logger.info("更新公众号信息成功");
                return selectList.get(0);
            } else {
                if (wxReplyDao.insert(reply) < 1) {
                    throw new CommonException(ResultEnum.SAVE_REPLY_CONTENT_ERROR);
                }
                logger.info("保存公众号信息成功");
                return reply;
            }
        }
    }

    /**
     * 3.修改开关。
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     *              replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     *              useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean updateFlag(WxReply reply) throws WxErrorException {
        ValidatorUtil.param(reply, validator, "publicCode", "replyType", "replyFlag");
        WxReply selectResult = this.getReplyByPublicCodeAndReplyType(reply);
        if (selectResult == null) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        } else {
            return wxReplyDao.updateReplyFlag(reply) > 0 ? true : false;
        }
    }

    //    根据公众号主键和回复类型获取回复内容。获取列表，防止数据库数据混乱，多条数据相同
    public WxReply getReplyByPublicCodeAndReplyType(WxReply reply) {
        List<WxReply> list = wxReplyDao.selectByPublicCodeAndReplyType(reply);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /************ 关注时回复 ************/

    /**
     * 1-1. 获取关注时回复
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getFollow(WxReply reply) throws WxErrorException {
        reply.setReplyType(0);
        return this.getInfo(reply);
    }

    /**
     * 1-2. 开启、关闭关注时回复开关
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     *              useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean followFlag(WxReply reply) throws WxErrorException, CommonException {
        reply.setReplyType(0);
        return this.updateFlag(reply);
    }

    /**
     * 1-3. 添加、修改关注时回复内容
     *
     * @param reply 公众号实体
     *              reply 公众号主键
     *              content   回复的内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReply addFollow(WxReply reply) throws WxErrorException {
        reply.setReplyType(0);
        return this.addContent(reply);
    }

    /************ 非关键词回复 ************/

    /**
     * 2-1. 获取非关键词回复
     *
     * @param reply 公众号实体
     *              publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WxReply> getNormal(WxReply reply) throws WxErrorException {
        reply.setReplyType(1);
        return this.getInfo(reply);
    }

    /**
     * 2-2. 开启、关闭非关键词回复开关
     *
     * @param reply 公众号实体
     *              <p>
     *              publicCode 公众号主键
     *              useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean normalFlag(WxReply reply) throws WxErrorException, CommonException {
        reply.setReplyType(1);
        return this.updateFlag(reply);
    }

    /**
     * 2-3. 添加/修改非关键词回复内容
     *
     * @param reply 公众号实体
     *              <p>
     *              publicCode 公众号主键
     *              content   回复的内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReply addNormal(WxReply reply) throws WxErrorException {
        reply.setReplyType(1);
        return this.addContent(reply);
    }

    /************ 关键词规则 ************/

    /**
     * 3-1. 获取关键词规则列表（分页）
     *
     * @param
     * @param rule 关键词规则实体
     *             currentPage 当前页
     *             pageSize 页数
     * @return
     * @throws WxErrorException
     */
    @Override
    public PageInfo<WxReplyRule> getRulePage(WxReplyRule rule, int currentPage, int pageSize) throws WxErrorException {

        if (currentPage < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(ResultEnum.PARAME_LIMITE_POSITIVE);
        }
        // 检查公众号是否存在
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(rule);
        if (checkPublicCode.size() == 0) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        } else {
            PageHelper.startPage(currentPage, pageSize);
            List<WxReplyRule> list = wxReplyRuleDao.selectAll(rule);
            PageInfo<WxReplyRule> page = new PageInfo<>(list);
            return page;
        }
    }

    /**
     * 3-1-1. 获取单个关键词规则
     *
     * @param rule ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxReplyRule getRule(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule, validator, "ruleCode");
        List<WxReplyRule> selectResult = wxReplyRuleDao.selectByPrimaryKey(rule);
        if (selectResult.size() > 0) {
            return selectResult.get(0);
        } else {
            throw new CommonException(ResultEnum.NOTFOUND_ERROR);
        }
    }

    /**
     * 3-1-2. 获取关键词规则下的关键词
     *
     * @param rule 关键词规则实体
     *             ruleCode 关键词规则主键
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public List<WxReplyKeyword> getKeywordByRule(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule, validator, "ruleCode");
        List<WxReplyKeyword> selectResult = wxReplyKeywordDao.selectByRuleCode(rule);
        if (selectResult.size() > 0) {
            return selectResult;
        } else {
            throw new CommonException(ResultEnum.NOTFOUND_ERROR);
        }
    }

    /**
     * 3-1-3. 获取关键词规则下的关键词
     *
     * @param rule 关键词规则实体
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public List<WxReplyKeyword> getKeywords(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule, validator, "publicCode");
        List<WxReplyKeyword> selectResult = wxReplyKeywordDao.selectKeywords(rule);
        if (selectResult.size() > 0) {
            return selectResult;
        } else {
            throw new CommonException(ResultEnum.NOTFOUND_ERROR);
        }
    }

    /**
     * 3-2. 开启、关闭所有关键词规则回复开关
     *
     * @param reply publicCode 公众号主键
     *              useFlag  回复是否开启。0为关闭，1为开启，默认为1开启
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean allRuleFlag(WxReply reply) throws WxErrorException, CommonException {
        reply.setReplyType(2);
        return this.updateFlag(reply);
    }

    /**
     * 3-3. 添加关键词规则
     *
     * @param replyRule 关键词回复规则
     * @return
     * @throws WxErrorException
     */
    @Override
    public String addRule(WxReplyRule replyRule) throws WxErrorException, CommonException {
        // 查询公众号是否存在
        List<String> checkPublicCode = wxPublicDao.selectPublicCode(replyRule.getPublicCode());
        if (checkPublicCode.size() == 0) {
            throw new CommonException(ResultEnum.PUBLIC_NOTFOUND);
        }
        // 查询关键词是否存在
        List<String> existKeywords = wxReplyKeywordDao.checkKeywords(replyRule);

        List<WxReplyKeyword> keywords = replyRule.getKeywords();
        String ruleCode = IdGen.uuid();
        for (WxReplyKeyword keyword : keywords) {
            keyword.setKeywordCode(IdGen.uuid());
            keyword.setRuleCode(ruleCode);
            // 设置默认值
            if (existKeywords.contains(keyword.getKeyword())){
                throw new CommonException(ResultEnum.KEYWORD_HAS_EXISTED,keyword.getKeyword());
            }
            keyword.setDelFlag(0);
        }
        // 保存关键词
        int insertKeyWordResult = wxReplyKeywordDao.insertBatch(keywords);

        int insertRuleResult = -1;
        if (insertKeyWordResult > 0) {
            replyRule.setRuleCode(ruleCode);
            // 设置默认值
            replyRule.setDelFlag(0);
            // 保存关键词规则
            insertRuleResult = wxReplyRuleDao.insertSelective(replyRule);
        }
        if (insertRuleResult > 0) {
            return ruleCode;
        } else {
            throw new CommonException(500, "服务器异常");
        }

    }

    /**
     * 3-4. 删除关键词规则（逻辑）
     *
     * @param rule 关键词规则实体
     *             ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean deleteRule(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule,validator,"ruleCode");
        return wxReplyRuleDao.deleteByPrimaryKey(rule) > 0 ? true : false;
    }

    /**
     * 3-4.删除关键词规则（物理)
     *
     * @param rule 关键词规则实体
     *             ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean forceDeleteRule(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule,validator,"ruleCode");
        return wxReplyRuleDao.forceDeleteByPrimaryKey(rule) > 0 ? true : false;
    }

    /**
     * 3-4-1. 批量删除关键词规则（逻辑）
     *
     * @param rule 关键词规则实体
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public boolean deleteRuleBatch(WxReplyRule rule) throws WxErrorException, CommonException {
        // TODO: 2017/11/7 ruleCodes 参数校验
        return wxReplyRuleDao.deleteBatch(rule.getRuleCodes()) > 0 ? true : false;

    }

    /**
     * 3-4-1. 批量删除关键词规则（物理）
     *
     * @param rule 关键词规则实体
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public boolean forceDeleteRuleBatch(WxReplyRule rule) throws WxErrorException, CommonException {
        // TODO: 2017/11/7 ruleCodes 参数校验
        return wxReplyRuleDao.forceDeleteBatch(rule.getRuleCodes()) > 0 ? true : false;
    }

    /**
     * 3-4-2. 删除关键词（逻辑）
     *
     * @param keyword 关键词实体
     *                keywordCode 关键词主键
     * @return
     * @throws CommonException
     */
    @Override
    public boolean deleteKeyword(WxReplyKeyword keyword) throws WxErrorException, CommonException {
        ValidatorUtil.param(keyword,validator,"keywordCode");
        return wxReplyKeywordDao.deleteByPrimaryKey(keyword) > 0 ? true : false;
    }

    /**
     * 3-4-3.删除关键词（物理）
     *
     * @param keyword 关键词实体
     *                keywordCode 关键词主键
     * @return
     * @throws WxErrorException
     * @throws CommonException
     */
    @Override
    public boolean forceDeleteKeyword(WxReplyKeyword keyword) throws WxErrorException, CommonException {
        ValidatorUtil.param(keyword,validator,"keywordCode");
        return wxReplyKeywordDao.forceDeleteByPrimaryKey(keyword) > 0 ? true : false;
    }


    /**
     * 3-6. 修改关键词规则
     *
     * @param replyRule 规则内容
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean updateRule(WxReplyRule replyRule) throws WxErrorException, CommonException {

        List<WxReplyKeyword> keywords = replyRule.getKeywords();

        if (keywords.size() < 0) {
            throw new CommonException(ResultEnum.KEYWORDS_PARAME_ERROR);
        }
        //  记录需要更新的 keyword 的列表
        ArrayList<WxReplyKeyword> updateList = new ArrayList<>();
        //  记录需要插入的 keyword 的列表
        ArrayList<WxReplyKeyword> insertList = new ArrayList<>();
        //  遍历参数中的关键词列表，构造更新和插入的新的关键词列表
        for (WxReplyKeyword replyKeyword : keywords) {
            // 为所有关键词设置code
            replyKeyword.setRuleCode(replyRule.getRuleCode());
            // 如果传入参数，有 keyword，则是更新，插入更新列表，反之插入保存列表
            if (replyKeyword.getKeywordCode() != null) {
                updateList.add(replyKeyword);
            } else {
                replyKeyword.setKeywordCode(IdGen.uuid());
                // 默认不删除
                replyKeyword.setDelFlag(0);
                insertList.add(replyKeyword);
            }
        }
        if (updateList.size() > 0 && wxReplyKeywordDao.updateBatch(updateList) < 0) {
            logger.error("批量更新关键词时出错");
            return false;
        }

        if (insertList.size() > 0 && wxReplyKeywordDao.insertBatch(insertList) < 0) {
            logger.error("批量插入关键词时出错");
            return false;
        }

        return wxReplyRuleDao.updateByPrimaryKeySelective(replyRule) > 0 ? true : false;

    }

    /**
     * 3-6-2. 批量修改关键词
     *
     * @param rule 关键词规则实体
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean updateKeywordBatch(WxReplyRule rule) throws WxErrorException {
        if (wxReplyKeywordDao.updateBatch(rule.getKeywords()) < 0) {
            logger.error("批量更新关键词出错");
            return false;
        } else {
            logger.info("批量更新关键词成功");
            return true;
        }
    }

    /**
     * 3-7. 开启、关闭一个关键词回复开关
     *
     * @param rule 关键词实体
     *             ruleCode 关键词主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public boolean singleRuleFlag(WxReplyRule rule) throws WxErrorException, CommonException {
        ValidatorUtil.param(rule,validator,"ruleCode","useFlag");
        if (wxReplyRuleDao.updateRuleFlag(rule) < 0) {
            logger.error("开启/关闭关键词规则开关出错");
            return false;
        } else {
            logger.info("开启/关闭关键词规则开关成功");
            return true;
        }
    }

    // TODO: 2017/11/8 mapper 还没完善 
    /**
     * 4-1. 根据公众号、关键词获取回复消息
     *
     * @param rule 关键词实体
     * @return com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule
     * @author huangyg
     */
    @Override
    public List<WxReplyRule> getMatchContent(WxReplyRule rule) throws WxErrorException {
        return wxReplyRuleDao.selectMatchContent(rule);
    }

    @Override
    public List<WxReplyRule> selectTest(WxReplyRule rule) throws WxErrorException {
        return wxReplyRuleDao.selectTest(rule);
    }

    /************ 目前没啥用的方法 ************/

    /**
     * 1.根据主键获取回复内容
     *
     * @param reply 消息回复实体
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    @Override
    public WxReply getInfoByReplyCode(WxReply reply) throws WxErrorException {
        return wxReplyDao.selectByPrimaryKey(reply);
    }

    /**
     * 2.获取消息回复内容列表（全部）
     *
     * @param reply 消息回复实体
     *              publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Deprecated
    @Override
    public List<WxReply> getList(WxReply reply) throws WxErrorException {
        return wxReplyDao.selectByPulCode(reply);
    }


}

