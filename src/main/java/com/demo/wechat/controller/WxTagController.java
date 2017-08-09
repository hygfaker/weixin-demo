package com.demo.wechat.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangyg on 2017/8/2.
 */
@RestController
@RequestMapping("/tag")
public class WxTagController {
    @Autowired
    private WxMpService wxService;


    @PostMapping("/create")
    public String tagCreateSimple(String name) throws WxErrorException {
        WxUserTag userTag = this.wxService.getUserTagService().tagCreate(name);
        return userTag.toString();
    }

    @GetMapping("/get")
    public List<WxUserTag> tagGet() throws WxErrorException{
        return this.wxService.getUserTagService().tagGet();
    }

    @GetMapping("/delete")
    public Boolean tagDelete(@RequestParam Long tagid) throws WxErrorException{
        return this.wxService.getUserTagService().tagDelete(tagid);
    }

    @PostMapping("/tagUpdate")
    public Boolean tagUpdate(Long tagID, String name) throws WxErrorException{
        return this.wxService.getUserTagService().tagUpdate(tagID,name);
    }

    @GetMapping("/listUser")
    public WxTagListUser tagListUser(Long tagID, String nextOpenid) throws WxErrorException{
        return this.wxService.getUserTagService().tagListUser(tagID,nextOpenid);
    }

    @PostMapping("/batchTagging")
    public Boolean batchTagging(Long tagID, String[] openids) throws WxErrorException{
        return this.wxService.getUserTagService().batchTagging(tagID,openids);
    }

    @PostMapping("/batchUntagging")
    public Boolean batchUntagging(Long tagID, String[] openids) throws WxErrorException{
        return this.wxService.getUserTagService().batchUntagging(tagID,openids);
    }

    @GetMapping("/userTagList")
    public List<Long> userTagList(String openid) throws WxErrorException{
        return this.wxService.getUserTagService().userTagList(openid);
    }

}
