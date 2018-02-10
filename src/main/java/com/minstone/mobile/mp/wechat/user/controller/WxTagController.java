package com.minstone.mobile.mp.wechat.user.controller;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.PagerUtil;
import com.minstone.mobile.mp.utils.ResultUtil;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyg on 2017/8/2.
 */
@RestController
@RequestMapping("/tag")
public class WxTagController {
    @Autowired
    private WxMpService wxService;

    @Autowired
    private IWxPublicService publicService;

    // 创建标签
    @PostMapping("/create")
    public CommonResult tagCreateSimple(String name) throws WxErrorException {
        WxUserTag userTag = this.wxService.getUserTagService().tagCreate(name);
        return ResultUtil.success(userTag);
    }

    // 获取公众号已创建的标签
    @GetMapping("/get")
    public CommonResult tagGet(@RequestParam String publicCode) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        List<WxUserTag> tagList = this.wxService.getUserTagService().tagGet();
        return ResultUtil.success(tagList);
    }

    // 获取公众号已创建的标签，包括黑名单+全部用户
    @GetMapping("/getAll")
    public CommonResult getAll(@RequestParam String publicCode,
                               @RequestParam(value = "nextOpenid", required = false) String nextOpenid) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        List<WxUserTag> tagList = this.wxService.getUserTagService().tagGet();

        // 获取全部用户
        List<String> list = this.wxService.getUserService().userList(nextOpenid).getOpenids();
        WxUserTag allUserTag = new WxUserTag();
        allUserTag.setCount(list.size());
        allUserTag.setId((long) 100000);
        allUserTag.setName("全部用户");

        // 获取黑名单
        WxMpUserBlacklistGetResult result = this.wxService.getBlackListService().getBlacklist(nextOpenid);
        WxUserTag blackListTag = new WxUserTag();
        blackListTag.setCount(result.getCount());
        blackListTag.setId((long) 100001);
        blackListTag.setName("黑名单");

        tagList.add(0,allUserTag);
        tagList.add(tagList.size(),blackListTag);
//        List<WxUserTag> allList = new ArrayList<>();
//        allList.add();

        return ResultUtil.success(tagList);
    }

    // 删除标签
    @GetMapping("/delete")
    public CommonResult tagDelete(@RequestParam String publicCode, @RequestParam int tagid) throws WxErrorException, IOException {
        this.configStorage(publicCode);

        if (this.wxService.getUserTagService().tagDelete((long)tagid)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 修改标签
    @PostMapping("/update")
    public CommonResult tagUpdate(@RequestParam String publicCode, Long tagID, String name) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        if (this.wxService.getUserTagService().tagUpdate(tagID, name)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 获取标签下粉丝列表
    @GetMapping("/usersOfTag")
    public CommonResult tagListUser(@RequestParam String publicCode,
                                    Long tagID,
                                    String nextOpenid,
                                    @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        WxTagListUser listUser = this.wxService.getUserTagService().tagListUser(tagID, nextOpenid);
        if (listUser.getCount() > 0) {
            PageInfo<String> page = PagerUtil.lowPager(currentPage, pageSize, listUser.getData().getOpenidList());
            return ResultUtil.pageFormat(page);
        } else {
            return ResultUtil.success();
        }
    }

    // 分页直接获取标签下粉丝列表
    @GetMapping("userinfoOfTagPage")
    public CommonResult userinfoOfTagPage(@RequestParam String publicCode,
                                          @RequestParam Long tagID,
                                          String nextOpenid,
                                          @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        WxTagListUser listUser = this.wxService.getUserTagService().tagListUser(tagID, nextOpenid);
        if (listUser.getCount() > 0) {
            PageInfo<WxMpUser> page = PagerUtil.lowPager(currentPage, pageSize, listUser.getData().getOpenidList());
            // 根据请求的分页参数，拿到相应数据去获取基本信息
            List<WxMpUser> result = new ArrayList<>();
            if (listUser.getData().getOpenidList().size() == 0) {
                return ResultUtil.success();
            } else {
                int startIndex = (page.getPageNum() - 1) * pageSize;
                int endIndex = startIndex + page.getPageSize();
                result = this.wxService.getUserService().userInfoList(listUser.getData().getOpenidList().subList(startIndex, endIndex));
            }
            page.setList(result);
            return ResultUtil.pageFormat(page);
        } else {
            return ResultUtil.success();
        }
    }

    // 批量为用户打标签
    @PostMapping("/batchTagForUser")
    public CommonResult batchTagging(@RequestParam String publicCode,
                                     @RequestParam Long tagID,
                                     @RequestParam String[] openids) throws WxErrorException, IOException {
        this.configStorage(publicCode);

        if (this.wxService.getUserTagService().batchTagging(tagID, openids)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 批量为用户取消标签
    @PostMapping("/batchCancelTag")
    public CommonResult batchUntagging(@RequestParam String publicCode,
                                       @RequestParam Long tagID,
                                       @RequestParam String[] openids) throws WxErrorException, IOException {
        this.configStorage(publicCode);

        if (this.wxService.getUserTagService().batchUntagging(tagID, openids)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 批量为用户修改标签（单选）
    @PostMapping("/batchChangeTag")
    public CommonResult batchChangeTag(@RequestParam String publicCode,
                                       @RequestParam Long originTagID,
                                       @RequestParam Long targetTagID,
                                       @RequestParam String[] openids) throws WxErrorException, IOException {
        this.configStorage(publicCode);

        // 先取消标签,再打上标签
        if (this.wxService.getUserTagService().batchUntagging(originTagID, openids)) {
            if (this.wxService.getUserTagService().batchTagging(targetTagID, openids)){
                return ResultUtil.success();
            }else{
                // 重新打回原来的标签
                this.wxService.getUserTagService().batchTagging(originTagID, openids);
            }
        }
        return ResultUtil.failure(CommonResultEnum.SERVER_ERROR);
    }


    // 获取用户身上的标签列表
    @GetMapping("/tagsOfUser")
    public CommonResult userTagList(@RequestParam String publicCode, String openid) throws WxErrorException, IOException {
        this.configStorage(publicCode);
        List<Long> tagList = this.wxService.getUserTagService().userTagList(openid);
        return ResultUtil.success(tagList);
    }

    /**
     * 根据接收的 publicCode 参数判断 access_token
     *
     * @param publicCode 公众号
     * @return void
     * @author huangyg
     */
    public void configStorage(String publicCode) throws WxErrorException, IOException {
        WxPublic checkPublic = publicService.get(publicCode);
        if (checkPublic == null) {
            throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
        }
        // 判断是否需要切换公众号
        if (!checkPublic.getAppSecret().equals(new WxMpInMemoryConfigStorage().getSecret())) {
            WxMpInMemoryConfigStorage config = publicService.switchPublic(checkPublic);
            wxService.setWxMpConfigStorage(config);
        }
    }

}
