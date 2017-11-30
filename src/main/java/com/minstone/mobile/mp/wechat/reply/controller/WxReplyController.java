package com.minstone.mobile.mp.wechat.reply.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.wechat.reply.domain.WxReply;
import com.minstone.mobile.mp.wechat.reply.service.IWxReplyService;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyKeyword;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author huangyg
 * @description
 * @since 2017/10/13
 */
@RestController
@RequestMapping("reply")
public class WxReplyController {

    @Autowired
    private IWxReplyService wxReplyService;
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

    /************ 关注时回复 ************/

    //    1-1. 获取关注时回复
    @GetMapping("/getFollow")
    public CommonResult getFollow(WxReply reply) throws WxErrorException {
        return ResultUtil.success(wxReplyService.getFollow(reply));
    }

    //    1-2. 开启、关闭关注时回复开关
    @GetMapping("/followFlag")
    public CommonResult followFlag(WxReply reply) throws WxErrorException {
        if (wxReplyService.followFlag(reply)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    1-3. 添加、修改关注时回复内容
    @PostMapping("/addFollow")
    public CommonResult addFollow(WxReply reply) throws WxErrorException {
        return ResultUtil.success(wxReplyService.addFollow(reply));
    }

    /************ 非关键词回复 ************/

    //    2-1. 获取非关键词回复
    @GetMapping("/getNormal")
    public CommonResult getNormal(WxReply reply) throws WxErrorException {
        return ResultUtil.success(wxReplyService.getNormal(reply));
    }

    //    2-2. 开启/关闭非关键词回复开关
    @GetMapping("/normalFlag")
    public CommonResult normalFlag(WxReply reply) throws WxErrorException {
        if (wxReplyService.normalFlag(reply)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    2-3. 添加/修改非关键词回复内容
    @PostMapping("/addNormal")
    public CommonResult addNormal(WxReply reply) throws WxErrorException {
        return ResultUtil.success(wxReplyService.addNormal(reply));
    }

    /************ 关键词回复 ************/

    //    3-1. 获取关键词规则列表（分页）
    @GetMapping("/getRulePage")
    public CommonResult getRulePage(WxReplyRule rule, @RequestParam(value = "currentPage",defaultValue = "1") int currentPage, @RequestParam(value = "pageSize",defaultValue = "20") int pageSize) throws WxErrorException {
        PageInfo page = wxReplyService.getRulePage(rule, currentPage, pageSize);
        return ResultUtil.pageFormat(page);
    }

    //    3-1-1. 获取单个关键词规则
    @GetMapping("/getRule")
    public CommonResult getRule(WxReplyRule rule) throws WxErrorException,CommonException {
        return ResultUtil.success(wxReplyService.getRule(rule));
    }

    //    3-1-2. 获取关键词规则下的关键词
    @GetMapping("/getKeyword")
    public CommonResult getKeyword(WxReplyRule rule) throws WxErrorException,CommonException{
        return ResultUtil.success(wxReplyService.getKeywords(rule));
    }

    //    3-2. 开启、关闭所有关键词规则回复开关
    @GetMapping("/allRuleFlag")
    public CommonResult allRuleFlag(WxReply reply) throws WxErrorException, CommonException {
        if (wxReplyService.allRuleFlag(reply)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-3. 添加关键词规则
    @PostMapping("/addRule")
    public CommonResult addRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException,CommonException {
        return ResultUtil.success(wxReplyService.addRule(wxReplyRule));
    }

    //    3-4. 删除关键词规则（逻辑）
    @GetMapping("/deleteRule")
    public CommonResult deleteRule(WxReplyRule rule) throws WxErrorException {
        if (wxReplyService.deleteRule(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-4.删除关键词规则（物理)
    @GetMapping("/forceDeleteRule")
    public CommonResult forceDeleteRule(WxReplyRule rule) throws WxErrorException {
        if (wxReplyService.forceDeleteRule(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-1. 批量删除关键词规则（逻辑）
    @GetMapping("/deleteRuleBatch")
    public CommonResult deleteRuleBatch(WxReplyRule rule) throws WxErrorException{
        if (wxReplyService.deleteRuleBatch(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-1. 批量删除关键词规则（物理）
    @GetMapping("/forceDeleteRuleBatch")
    public CommonResult forceDeleteRuleBatch(WxReplyRule rule) throws WxErrorException{
        if (wxReplyService.forceDeleteRuleBatch(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-2. 删除关键词（逻辑）
    @GetMapping("/deleteKeyword")
    public CommonResult deleteKeyword(WxReplyKeyword keyword)throws WxErrorException {
        if (wxReplyService.deleteKeyword(keyword)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-3. 删除关键词（物理）
    @GetMapping("/forceDeleteKeyword")
    public CommonResult forceDeleteKeyword(WxReplyKeyword keyword)throws WxErrorException {
        if (wxReplyService.forceDeleteKeyword(keyword)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-6. 修改关键词规则
    @PostMapping("/updateRule")
    public CommonResult updateRule(@RequestBody @Valid WxReplyRule replyRule) throws WxErrorException,CommonException {
        if (wxReplyService.updateRule(replyRule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-6-2. 批量修改关键词
    @Deprecated
    @PostMapping("/updateKeywordPatch")
    public CommonResult updateKeywordPatch(@RequestBody WxReplyRule rule) throws WxErrorException {
        if (wxReplyService.updateKeywordBatch(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    //    3-7. 开启、关闭一个关键词回复开关
    @GetMapping("/singleRuleFlag")
    public CommonResult singleRuleFlag(WxReplyRule rule) throws WxErrorException,CommonException {
        if (wxReplyService.singleRuleFlag(rule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }

    }

    @GetMapping("/selectTest")
    public CommonResult selectTest(WxReplyRule rule) throws WxErrorException{
        return ResultUtil.success(wxReplyService.selectTest(rule));
    }


}
