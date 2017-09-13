package com.minstone.wechat.controller;

import com.google.gson.Gson;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private static Logger logger = LoggerFactory.getLogger(WxPublicController.class);

    @Autowired
    private  WxRuleMapper wxRuleMapper;
    @Autowired
    private  WxKeywordMapper wxKeywordMapper;

    // 添加规则
    @PostMapping("/addRule")
    public Result addKeywordReplyRule(@ModelAttribute WxRule wxRule, @RequestParam String wxKeywords){
        Gson gson = new Gson();
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map = gson.fromJson(wxKeywords, map.getClass());
        int ruleCode = wxRuleMapper.insert(wxRule);

        List<WxKeyword> keywords = new ArrayList<WxKeyword>();
        for(Map.Entry<String, Boolean> item : map.entrySet()){
            WxKeyword keyword = new WxKeyword();
            keyword.setWxruleCode(ruleCode);
            keyword.setWxruleMatch(item.getValue());
            keyword.setWxruleKeyword(item.getKey());
            keywords.add(keyword);
        }
        logger.info("--keywords = " + keywords);

        int keyword = wxKeywordMapper.batchInsert(keywords);
        logger.info("keywords = " + keywords);
        return ResultUtil.success();
    }

}
