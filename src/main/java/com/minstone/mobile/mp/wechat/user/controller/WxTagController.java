package com.minstone.mobile.mp.wechat.user.controller;

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

    // 创建标签
    @PostMapping("/create")
    public String tagCreateSimple(String name) throws WxErrorException {
        WxUserTag userTag = this.wxService.getUserTagService().tagCreate(name);
        return userTag.toString();
    }

    // 获取公众号已创建的标签
    @GetMapping("/get")
    public List<WxUserTag> tagGet() throws WxErrorException{
        return this.wxService.getUserTagService().tagGet();
    }

    // 删除标签
    @GetMapping("/delete")
    public Boolean tagDelete(@RequestParam Long tagid) throws WxErrorException{
        return this.wxService.getUserTagService().tagDelete(tagid);
    }

    // 修改标签
    @PostMapping("/tagUpdate")
    public Boolean tagUpdate(Long tagID, String name) throws WxErrorException{
        return this.wxService.getUserTagService().tagUpdate(tagID,name);
    }

    // 获取标签下粉丝列表
    @GetMapping("/listUser")
    public WxTagListUser tagListUser(Long tagID, String nextOpenid) throws WxErrorException{
        return this.wxService.getUserTagService().tagListUser(tagID,nextOpenid);
    }

    // 批量为用户打标签
    @PostMapping("/batchTagging")
    public Boolean batchTagging(Long tagID, String[] openids) throws WxErrorException{
        return this.wxService.getUserTagService().batchTagging(tagID,openids);
    }

    // 批量为用户取消标签
    @PostMapping("/batchUntagging")
    public Boolean batchUntagging(Long tagID, String[] openids) throws WxErrorException{
        return this.wxService.getUserTagService().batchUntagging(tagID,openids);
    }

    // 获取用户身上的标签列表
    @GetMapping("/userTagList")
    public List<Long> userTagList(String openid) throws WxErrorException{
        return this.wxService.getUserTagService().userTagList(openid);
    }

}
