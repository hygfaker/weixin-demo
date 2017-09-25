package com.minstone.wechat.api.imp;

import com.minstone.wechat.api.WxReplyApi;
import com.minstone.wechat.api.service.WxReplyService;
import com.minstone.wechat.domain.WxReply;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangyg on 2017/9/21.
 */
@RestController
@RequestMapping("reply")
public class WxReplyImp implements WxReplyApi {
//    todo
//    开启/关闭关注时回复、非关键词回复、规则回复
//    设置关注时回复、非关键词回复、规则回复的内容

    @Autowired
    private WxReplyService wxReplyService;


    //    根据主键获取回复内容
    @Override
    @GetMapping("/getByKey")
    public Result getReplyDetailByKey(@RequestParam String replyCode) throws WxErrorException{
        WxReply wxReply = wxReplyService.getReplyByKey(replyCode);
        if (wxReply == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(wxReply);
        }
    }

    //    根据公众号主键和回复类型获取回复内容以及开关
    @Override
    @GetMapping("/get")
    public Result getReplyDetail(@RequestParam String publicCode , @RequestParam Integer replyType) throws WxErrorException{
        WxReply wxReply = wxReplyService.getReplyByPubCodeAndReplyType(publicCode,replyType);
        if (wxReply == null){
            return ResultUtil.failure(ResultEnum.NOTFOUND_ERROR);
        }else{
            return ResultUtil.success(wxReply);
        }
    }

    //    添加回复内容
    @Override
    @PostMapping("/addContent")
    public Result addReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{
        int addResult = wxReplyService.addReplyContent(publicCode,content,replyType);
        return wxReplyService.returnResult(addResult);
    }

    //    根据主键修改回复内容
    @Override
    @PostMapping("/updateContentByKey")
    public Result updateReplyContent(@RequestParam String replyCode , @RequestParam String content) throws WxErrorException{
        int updateResult = wxReplyService.updateContentByKey(replyCode,content);
        return wxReplyService.returnResult(updateResult);
    }

    //    根据公众号主键和回复类型修改回复内容
    @Override
    @PostMapping("/updateContent")
    public Result updateReplyContent(@RequestParam String publicCode , @RequestParam String content , @RequestParam Integer replyType) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyContent(publicCode,content,replyType);
        return wxReplyService.returnResult(updateResult);
    }

    //    根据主键修改回复内容
    @Override
    @PostMapping("/updateSwitchByKey")
    public Result updateReplyFlagByKey(@RequestParam String replyCode , @RequestParam  Integer replyFlag) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyFlagByKey(replyCode,replyFlag);
        return wxReplyService.returnResult(updateResult);
    }

    //    根据公众号主键和回复类型修改回复开关
    @Override
    @PostMapping("/updateSwitch")
    public Result updateReplyFlag(@RequestParam String publicCode , @RequestParam  Integer replyFlag , @RequestParam Integer replyType) throws WxErrorException{
        int updateResult = wxReplyService.updateReplyFlag(publicCode,replyType,replyFlag);
        return wxReplyService.returnResult(updateResult);
    }


}

