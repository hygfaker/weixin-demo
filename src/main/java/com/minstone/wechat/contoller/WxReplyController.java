package com.minstone.wechat.contoller;

import com.minstone.wechat.api.WxReplyService;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.domain.WxReplyRule;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.model.Result;
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

    //    todo
    //    1.根据主键获取回复内容  xx
    //    1.根据公众号主键和回复类型获取回复内容  xx
    //    1-1.获取消息回复内容列表（全部）    xx
    //    1-2.获取关注时回复内容消息
    //    1-3.获取非关键词消息默认回复
    //    1-4.获取关键词回复内容列表
    //    1-5.获取某个关键词回复内容

    //    2.添加回复内容  xx
    //    2-1.添加、修改关注时消息回复内容
    //    2-2.添加、修改非关键词消息默认回复内容
    //    2-3.关键词回复 - 添加规则

    //    3.修改开关。  xx
    //    3-1.开启、关闭关注时回复
    //    3-2.开启、关闭非关键词消息默认回复
    //    3-3.开启、关闭关键词回复

    //    4-1.获取关键词回复内容列表（同1-4）
    //    4-2.添加某个规则 (同2-3)
    //    4-3.逻辑删除某个规则
    //    4-4.物理删除某个规则  xx
    //    4-5.编辑某个规则
    //    4-6.开启、关闭某个规则
    //    4-7.获取某个关键词回复内容（同1-5)

    //    1-2.获取关注时回复内容消息
    @GetMapping("/getFollowInfo")
    public Result getFollowInfo(@RequestParam String publicCode) throws WxErrorException {
        List<WxReply> lists = wxReplyService.getFollowInfo(publicCode);
        if (lists == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(lists);
        }
    }

    //    1-3.获取非关键词消息默认回复
    @GetMapping("/getNormalInfo")
    public Result getNormalInfo(@RequestParam String publicCode) throws WxErrorException {
        List<WxReply> lists = wxReplyService.getFollowInfo(publicCode);
        if (lists == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(lists);
        }
    }

    //    1-4.获取关键词回复内容列表(未添加分页)
    @GetMapping("/getKeyWordInfo")
    public Result getKeyWordInfo(@RequestParam String publicCode) throws WxErrorException {
        List<WxReplyRule> lists = wxReplyService.getKeyWordInfo(publicCode);
        if (lists == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(lists);
        }
    }

    //    2-1.添加、修改关注时消息回复内容
    @PostMapping("/addFollowReply")
    public Result addFollowReply(@RequestParam String publicCode,@RequestParam String content) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.addFollowReply(publicCode,content));
    }

    //    2-2.添加、修改非关键词消息默认回复内容
    @PostMapping("/addNormalReply")
    public Result addNormalReply(@RequestParam String publicCode, @RequestParam String content) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.addNormalReply(publicCode,content));
    }

    //    2-3.添加某个规则
    @PostMapping("/addReplyRule")
    public Result addReplyRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.addReplyRule(wxReplyRule));
    }

    //    3-1.开启、关闭关注时回复
    @PostMapping("/followReplyFlag")
    public Result followReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException{
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.followReplyFlag(publicCode,useFlag));
    }

    //    3-2.开启、关闭非关键词消息默认回复
    @PostMapping("/normalReplyFlag")
    public Result normalReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException{
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.normalReplyFlag(publicCode,useFlag));
    }

    //    3-3.开启、关闭关键词回复
    @PostMapping("/keywordReplyFlag")
    public Result keywordReplyFlag(@RequestParam String publicCode, @RequestParam Integer useFlag) throws WxErrorException{
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");

        return ResultUtil.returnResult(wxReplyService.keywordReplyFlag(publicCode,useFlag));
    }

    //    4-3.逻辑删除某个规则
    @GetMapping("/deleteRule")
    public Result deleteRule(@RequestParam String ruleCode) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.deleteRule(ruleCode));
    }

    //    4-4.物理删除某个规则
    @GetMapping("/fDeleteRule")
    public Result FDeleteRule(@RequestParam String ruleCode) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.FDeleteRule(ruleCode));
    }

    //    4-5.编辑某个规则
    @PostMapping("updateRule")
    public Result editRule(@RequestBody @Valid WxReplyRule wxReplyRule) throws WxErrorException{
        return ResultUtil.returnResult(wxReplyService.editRule(wxReplyRule));
    }

    //    4-6.开启、关闭某个规则
    @GetMapping("updateRuleFlag")
    public Result updateRuleFlag(@RequestParam String ruleCode,@RequestParam int useFlag) throws WxErrorException{
        if (useFlag != 0 || useFlag != 1)
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,"【useFlag】参数只能为 0 或者 1");
        return ResultUtil.returnResult(wxReplyService.updateRuleFlag(ruleCode,useFlag));
    }

}
