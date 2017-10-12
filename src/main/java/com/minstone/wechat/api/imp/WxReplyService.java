package com.minstone.wechat.api.imp;

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

/**
 * Created by huangyg on 2017/9/21.
 */
@RestController
@RequestMapping("reply")
public class WxReplyService implements WxReplyService {
//    todo
//    获取消息回复内容列表（全部）
//    获取关注时回复内容消息
//    获取非关键词消息默认回复
//    获取关键词回复内容列表

//    添加、修改关注时消息回复内容
//    添加、修改非关键词消息默认回复内容

//    开启、关闭关注时回复
//    开启、关闭非关键词消息默认回复

    @Autowired
    private com.minstone.wechat.api.service.WxReplyService wxReplyService;


    //  根据主键获取回复内容
    @Override
    @GetMapping("/getInfoByReplyCode")
    public Result getInfoByReplyCode(@RequestParam String replyCode) throws WxErrorException{
        WxReply wxReply = wxReplyService.getReplyByKey(replyCode);
        if (wxReply == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(wxReply);
        }
    }

    //  根据公众号主键和回复类型获取回复内容以及开关
    @Override
    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam String publicCode , @RequestParam Integer replyType) throws WxErrorException{
        WxReply wxReply = wxReplyService.getReplyByPubCodeAndReplyType(publicCode,replyType);
        if (wxReply == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(wxReply);
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
    public Result getList(String publicCode) throws WxErrorException {
        return null;
    }

    /**
     * 1-2.获取关注时回复内容消息
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result getFollowInfo(String publicCode) throws WxErrorException {
        return null;
    }

    /**
     * 1-3.获取非关键词消息默认回复
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result getNormalInfo(String publicCode) throws WxErrorException {
        return null;
    }

    /**
     * 1-4.获取关键词回复内容列表
     *
     * @param publicCode 公众号主键
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result getKeyWordInfo(String publicCode) throws WxErrorException {
        return null;
    }

    //  添加回复内容
    @Override
    @PostMapping("/addContent")
    public Result addReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{
        int addResult = wxReplyService.addReplyContent(publicCode,content,replyType);
        return wxReplyService.returnResult(addResult);
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
    public Result addFollowReply(String publicCode, String content) throws WxErrorException {
        return null;
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
    public Result addNormalReply(String publicCode, String content) throws WxErrorException {
        return null;
    }

    //  根据主键修改回复内容
    @Override
    @PostMapping("/updateContentByKey")
    public Result updateReplyContent(@RequestParam String replyCode , @RequestParam String content) throws WxErrorException{
        int updateResult = wxReplyService.updateContentByKey(replyCode,content);
        return wxReplyService.returnResult(updateResult);
    }

    //  根据公众号主键和回复类型修改回复内容
    @Override
    @PostMapping("/updateContent")
    public Result updateReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyContent(publicCode,content,replyType);
        return wxReplyService.returnResult(updateResult);
    }

    //  根据主键修改回复内容
    @Override
    @PostMapping("/updateSwitchByKey")
    public Result updateReplyFlagByKey(@RequestParam String replyCode , @RequestParam  Integer replyFlag) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyFlagByKey(replyCode,replyFlag);
        return wxReplyService.returnResult(updateResult);
    }

    //  根据公众号主键和回复类型修改回复开关
    @Override
    @PostMapping("/updateSwitch")
    public Result updateReplyFlag(@RequestParam String publicCode , @RequestParam  Integer replyFlag , @RequestParam Integer replyType) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyFlag(publicCode,replyType,replyFlag);
        return wxReplyService.returnResult(updateResult);
    }

    /**
     * 3-1.开启/关闭，关注时回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result followReplyFlag(String publicCode, Integer replyType) throws WxErrorException {
        return null;
    }

    /**
     * 3-2.开启/关闭，非关键词回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result normalReplyFlag(String publicCode, Integer replyType) throws WxErrorException {
        return null;
    }

    /**
     * 3-3.开启、关闭关键词回复
     *
     * @param publicCode 公众号主键
     * @param replyType  消息回复类型。0为关注时回复，1为非关键词消息默认回复，2为关键词回复。
     * @return
     * @throws WxErrorException
     */
    @Override
    public Result keywordReplyFlag(String publicCode, Integer replyType) throws WxErrorException {
        return null;
    }

    /***********************  添加关键词规则  **********************/
    //  添加关键词回复规则内容
    @Override
    @PostMapping("/addReplyRule")
    public Result addReplyRule(@RequestBody @Valid WxReplyRule replyRule) throws WxErrorException {
        int addResult = wxReplyService.addReplyRule(replyRule);
        return wxReplyService.returnResult(addResult);
    }

    //  查询回复规则
    @GetMapping("/findRules")
    public Result findReplyRule(@RequestParam String publicCode) throws WxErrorException{
        return ResultUtil.success(wxReplyService.findRules(publicCode));
    }

    //  根据关键词主键查找关键词回复规则
    @GetMapping("findReplyRule")
    public Result findReplyRuleByKey(@RequestParam String ruleCode) throws WxErrorException{
        return ResultUtil.success(wxReplyService.findRuleByKey(ruleCode));
    }

    //  根据关键词查询回复规则
    @GetMapping("/findKeywords")
    public Result findRuleKeyword(@RequestParam String keyword) throws WxErrorException{
        return ResultUtil.success(wxReplyService.findKeywords(keyword));
    }

}

