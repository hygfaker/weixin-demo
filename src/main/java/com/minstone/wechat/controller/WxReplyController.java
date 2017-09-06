package com.minstone.wechat.controller;

import com.minstone.wechat.domain.WxKeyword;
import com.minstone.wechat.domain.WxRule;
import com.minstone.wechat.mapper.WxKeywordMapper;
import com.minstone.wechat.mapper.WxRuleMapper;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangyg on 2017/9/6.
 */
@RestController
@RequestMapping("reply")
public class WxReplyController {
    //todo
    // 关键词回复 - 添加规则
    // 关键词回复 - 更新规则
    // 关键词回复 - 删除规则
    // 关键词回复 - 关闭规则

    @Autowired
    private static WxRuleMapper wxRuleMapper;
    private static WxKeywordMapper wxKeywordMapper;

    @PostMapping("/addRule")
    public Result addKeywordReplyRule(@RequestParam WxRule wxRule, @RequestParam WxKeyword wxKeyword){
        wxRuleMapper.insert(wxRule);
        wxKeywordMapper.insert(wxKeyword);
        return ResultUtil.success();
    }

}
