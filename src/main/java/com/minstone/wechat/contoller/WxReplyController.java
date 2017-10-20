package com.minstone.wechat.contoller;

import com.github.pagehelper.PageInfo;
import com.minstone.wechat.api.WxReplyService;
import com.minstone.wechat.common.CommonException;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyKeyword;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.common.ResultEnum;
import com.minstone.wechat.common.CommonResult;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by huangyg on 2017/10/13.
 */
@RestController
@RequestMapping("reply")
public class WxReplyController {

    @Autowired
    private WxReplyService wxReplyService;
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

    /************************ 关注时回复 ************************/

    //    1-1. 获取关注时回复
    @GetMapping("/getFollowInfo")
    public CommonResult getFollowInfo(@RequestParam String publicCode) throws WxErrorException {
        List<WxReply> lists = wxReplyService.getFollowInfo(publicCode);
        if (lists == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(lists.get(0));
        }
    }

    //    1-2. 开启、关闭关注时回复开关
    @PostMapping("/followReplyFlag")
    public CommonResult followReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException {
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.followReplyFlag(publicCode,useFlag));
    }

    //    1-3. 添加、修改关注时回复内容
    @PostMapping("/addFollowReply")
    public CommonResult addFollowReply(@RequestParam String publicCode, @RequestParam String content) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.addFollowReply(publicCode,content));
    }

    /************************ 非关键词回复 ************************/

    //    2-1. 获取非关键词回复
    @GetMapping("/getNormalInfo")
    public CommonResult getNormalInfo(@RequestParam String publicCode) throws WxErrorException {
        List<WxReply> lists = wxReplyService.getFollowInfo(publicCode);
        if (lists == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(lists.get(0));
        }
    }

    //    2-2. 开启、关闭非关键词回复开关
    @PostMapping("/normalReplyFlag")
    public CommonResult normalReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException {
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.normalReplyFlag(publicCode,useFlag));
    }

    //    2-3. 添加、修改非关键词回复内容
    @PostMapping("/addNormalReply")
    public CommonResult addNormalReply(@RequestParam String publicCode, @RequestParam String content) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.addNormalReply(publicCode,content));
    }

    /************************ 关键词回复 ************************/

    //    3-1. 获取关键词规则列表（分页）
    @GetMapping("/getReplyRuleList")
    public CommonResult getKeywordPage(@RequestParam String publicCode, @RequestParam(value = "currentPage",defaultValue = "1") int currentPage, @RequestParam(value = "pageSize",defaultValue = "20") int pageSize) throws WxErrorException {
        if (currentPage < 0 ){
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【currentPage】参数必须大于0");
        }
        if (pageSize < 0 ){
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【pageSize】参数必须大于0");
        }

        PageInfo page = wxReplyService.getKeywordPage(publicCode, currentPage, pageSize);
        return ResultUtil.pageFormat(page);
    }

    //    3-1-1. 获取单个关键词规则
    @GetMapping("/getReplyRule")
    public CommonResult getReplyRule(@RequestParam String ruleCode) throws WxErrorException,CommonException{
        return ResultUtil.success(wxReplyService.getReplyRule(ruleCode));
    }

    //    3-2. 开启、关闭关键词回复开关
    @PostMapping("/keywordReplyFlag")
    public CommonResult keywordReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException {
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");

        return ResultUtil.returnResult(wxReplyService.keywordReplyFlag(publicCode,useFlag));
    }

    //    3-3. 添加关键词规则
    @PostMapping("/addReplyRule")
    public CommonResult addReplyRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.addReplyRule(wxReplyRule));
    }

    //    3-4. 删除关键词规则（逻辑）
    @GetMapping("/deleteRule")
    public CommonResult deleteRule(@RequestParam String ruleCode) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.deleteRule(ruleCode));
    }

    //    3-4.删除关键词规则（物理）
    @GetMapping("/fDeleteRule")
    public CommonResult FDeleteRule(@RequestParam String ruleCode) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.FDeleteRule(ruleCode));
    }

    //    3-4-1. 批量删除关键词规则（逻辑）
    @GetMapping("/deleteRuleBatch")
    public CommonResult deleteRuleBatch(@RequestParam String[] ruleCodes) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.deleteRuleBatch(ruleCodes));
    }

    //    3-4-1. 批量删除关键词规则（物理）
    @GetMapping("/fdeleteRuleBatch")
    public CommonResult FDeleteRuleBatch(@RequestParam String[] ruleCodes) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.FDeleteRuleBatch(ruleCodes));
    }

    //    3-4-2. 删除关键词（逻辑）
    @GetMapping("/deleteKeyword")
    public CommonResult deleteKeyword(@RequestParam String keywordCode)throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.deleteKeyword(keywordCode));
    }

    //    3-6. 修改关键词规则
    @PostMapping("/updateRule")
    public CommonResult editRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.updateRule(wxReplyRule));
    }

    //    3-6-2. 批量修改关键词
    @PostMapping("/updateKeywordPatch")
    public CommonResult updateKeywordPatch(@RequestBody @Valid List<WxReplyKeyword> lists) throws WxErrorException {
        return ResultUtil.returnResult(wxReplyService.updateKeywordBatch(lists));
    }

    //    3-7. 开启、关闭一个关键词回复开关
    @GetMapping("/updateRuleFlag")
    public CommonResult updateRuleFlag(@RequestParam String ruleCode, @RequestParam int useFlag) throws WxErrorException {
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.updateRuleFlag(ruleCode,useFlag));
    }
    //   ces
    @GetMapping("/test")
    public CommonResult getKeywordPage(@RequestParam String publicCode) throws WxErrorException {
        return ResultUtil.success(wxReplyService.test(publicCode));
    }
}
