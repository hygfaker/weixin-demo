package com.minstone.mobile.mp.wechat.user.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.ResultUtil;
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
    public CommonResult tagCreateSimple(String name) throws WxErrorException {
        WxUserTag userTag = this.wxService.getUserTagService().tagCreate(name);
        return ResultUtil.success(userTag);
    }

    // 获取公众号已创建的标签
    @GetMapping("/get")
    public CommonResult tagGet() throws WxErrorException{
        List<WxUserTag> tagList = this.wxService.getUserTagService().tagGet();
        return ResultUtil.success(tagList);
    }

    // 删除标签
    @GetMapping("/delete")
    public CommonResult tagDelete(@RequestParam Long tagid) throws WxErrorException{
        if (this.wxService.getUserTagService().tagDelete(tagid)){
            return ResultUtil.success();
        }else{
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 修改标签
    @PostMapping("/update")
    public CommonResult tagUpdate(Long tagID, String name) throws WxErrorException{
        if (this.wxService.getUserTagService().tagUpdate(tagID,name)){
            return ResultUtil.success();
        }else{
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 获取标签下粉丝列表
    @GetMapping("/usersOfTag")
    public CommonResult tagListUser(Long tagID, String nextOpenid) throws WxErrorException{
         WxTagListUser listUser = this.wxService.getUserTagService().tagListUser(tagID,nextOpenid);
        return ResultUtil.success(listUser);
    }

    // 批量为用户打标签
    @PostMapping("/batchTagForUser")
    public CommonResult batchTagging(Long tagID, String[] openids) throws WxErrorException{
        if (this.wxService.getUserTagService().batchTagging(tagID,openids)){
            return ResultUtil.success();
        }else{
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 批量为用户取消标签
    @PostMapping("/batchCanceltag")
    public CommonResult batchUntagging(Long tagID, String[] openids) throws WxErrorException{
        if (this.wxService.getUserTagService().batchUntagging(tagID,openids)){
            return ResultUtil.success();
        }else{
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 获取用户身上的标签列表
    @GetMapping("/tagsOfUser")
    public CommonResult userTagList(String openid) throws WxErrorException{
        List<Long> tagList = this.wxService.getUserTagService().userTagList(openid);
        return ResultUtil.success(tagList);
    }

}
