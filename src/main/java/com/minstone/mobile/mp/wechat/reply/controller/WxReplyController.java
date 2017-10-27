package com.minstone.mobile.mp.wechat.reply.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.wechat.reply.service.impl.WxReplyServiceImpl;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.ResultEnum;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyKeyword;
import com.minstone.mobile.mp.wechat.reply.domain.WxReplyRule;
import com.minstone.mobile.mp.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author huangyg
 * @description
 * @since 2017/10/13
 */
@RestController
@RequestMapping("reply")
public class WxReplyController {

    @Autowired
    private WxReplyServiceImpl wxReplyService;
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
    @GetMapping("/getFollowReply")
    public CommonResult getFollowInfo(@RequestParam String publicCode) throws WxErrorException {
        return ResultUtil.success(wxReplyService.getFollowInfo(publicCode).get(0));
    }

    //    1-2. 开启、关闭关注时回复开关
    @PostMapping("/followReplyFlag")
    public CommonResult followReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException {
        if (wxReplyService.followReplyFlag(publicCode,useFlag)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    1-3. 添加、修改关注时回复内容
    @PostMapping("/addFollowReply")
    public CommonResult addFollowReply(@RequestParam String publicCode, @RequestParam String content) throws WxErrorException {
        return ResultUtil.success(wxReplyService.addFollowReply(publicCode,content));
    }

    /************ 非关键词回复 ************/

    //    2-1. 获取非关键词回复
    @GetMapping("/getNormalInfo")
    public CommonResult getNormalInfo(@RequestParam String publicCode) throws WxErrorException {
        return ResultUtil.success(wxReplyService.getNormalInfo(publicCode).get(0));
    }

    //    2-2. 开启/关闭非关键词回复开关
    @PostMapping("/normalReplyFlag")
    public CommonResult normalReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException {
        if (wxReplyService.normalReplyFlag(publicCode,useFlag)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    2-3. 添加/修改非关键词回复内容
    @PostMapping("/addNormalReply")
    public CommonResult addNormalReply(@RequestParam String publicCode, @RequestParam String content) throws WxErrorException {
        return ResultUtil.success(wxReplyService.addNormalReply(publicCode,content));
    }

    /************ 关键词回复 ************/

    //    3-1. 获取关键词规则列表（分页）
    @GetMapping("/getReplyRuleList")
    public CommonResult getKeywordPage(@RequestParam String publicCode, @RequestParam(value = "currentPage",defaultValue = "1") int currentPage, @RequestParam(value = "pageSize",defaultValue = "20") int pageSize) throws WxErrorException {
        PageInfo page = wxReplyService.getKeywordPage(publicCode, currentPage, pageSize);
        return ResultUtil.pageFormat(page);
    }

    //    3-1-1. 获取单个关键词规则
    @GetMapping("/getReplyRule")
    public CommonResult getReplyRule(@RequestParam String ruleCode) throws WxErrorException,CommonException {
        return ResultUtil.success(wxReplyService.getReplyRule(ruleCode));
    }

    //    3-1-2. 获取关键词规则下的关键词
    @GetMapping("/getReplyRuleKeyword")
    public CommonResult getReplyRuleKeyword(@RequestParam String ruleCode) throws WxErrorException,CommonException{
        return ResultUtil.success(wxReplyService.selectByRuleCode(ruleCode));
    }

    //    3-2. 开启、关闭所有关键词回复开关
    @PostMapping("/replyRuleFlag")
    public CommonResult replyRuleFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException, CommonException {

        if (wxReplyService.replyRuleFlag(publicCode,useFlag)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-3. 添加关键词规则
    @PostMapping("/addReplyRule")
    public CommonResult addReplyRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException,CommonException {
        return ResultUtil.success(wxReplyService.addReplyRule(wxReplyRule));
    }

    //    3-4. 删除关键词规则（逻辑）
    @GetMapping("/deleteRule")
    public CommonResult deleteRule(@RequestParam String ruleCode) throws WxErrorException {
        if (wxReplyService.deleteRule(ruleCode)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-4.删除关键词规则（物理)
    @GetMapping("/forceDeleteRule")
    public CommonResult forceDeleteRule(@RequestParam String ruleCode) throws WxErrorException {
        if (wxReplyService.forceDeleteRule(ruleCode)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-1. 批量删除关键词规则（逻辑）
    @GetMapping("/deleteRuleBatch")
    public CommonResult deleteRuleBatch(@RequestParam String[] ruleCodes) throws WxErrorException{
        if (wxReplyService.deleteRuleBatch(ruleCodes)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-1. 批量删除关键词规则（物理）
    @GetMapping("/forceDeleteRuleBatch")
    public CommonResult forceDeleteRuleBatch(@RequestParam String[] ruleCodes) throws WxErrorException{
        if (wxReplyService.forceDeleteRuleBatch(ruleCodes)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-4-2. 删除关键词（逻辑）
    @GetMapping("/deleteKeyword")
    public CommonResult deleteKeyword(@RequestParam String keywordCode)throws WxErrorException {
        if (wxReplyService.deleteKeyword(keywordCode)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-6. 修改关键词规则
    @PostMapping("/updateRule")
    public CommonResult editRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException,CommonException {
        if (wxReplyService.updateRule(wxReplyRule)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }


    //    3-6-2. 批量修改关键词
    @Deprecated
    @PostMapping("/updateKeywordPatch")
    public CommonResult updateKeywordPatch(@RequestBody @Valid List<WxReplyKeyword> lists) throws WxErrorException {
        if (wxReplyService.updateKeywordBatch(lists)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }

    //    3-7. 开启、关闭一个关键词回复开关
    @PostMapping("/singleRuleFlag")
    public CommonResult updateRuleFlag(@RequestParam String ruleCode, @RequestParam int useFlag) throws WxErrorException,CommonException {
        if (wxReplyService.updateRuleFlag(ruleCode,useFlag)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }

    }

}
