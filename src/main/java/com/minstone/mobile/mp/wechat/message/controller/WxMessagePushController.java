package com.minstone.mobile.mp.wechat.message.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.wechat.message.service.impl.WxMessagePushServiceImpl;
import com.minstone.mobile.mp.wechat.message.domain.WxMessagePush;
import com.minstone.mobile.mp.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangyg
 * @description
 * @since 2017/10/24
 */
@RestController
@RequestMapping("push")
public class WxMessagePushController {

    // TODO: 2017/10/24
    // 添加定点推送
    // 批量添加定点推送

    // 删除定点推送（逻辑）
    // 删除定点推送（物理）
    // 批量删除定点推送（逻辑）
    // 批量删除定点推送（物理）

    // 修改定点推送
    // 开启、关闭定点推送

    // 分页查看定点推送

    @Autowired
    private WxMessagePushServiceImpl wxMessagePushService;

    /**
     * 添加定点提送
     * @param wxMessagePush 定点推送实体
     * @return java.lang.String 定点推送主键
     * @author huangyg
     */
    @PostMapping("/add")
    public CommonResult add(WxMessagePush wxMessagePush) throws WxErrorException {
        return ResultUtil.success(wxMessagePushService.add(wxMessagePush));
    }

    /**
     * 删除定点推送（逻辑)
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/delete")
    public CommonResult delete(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 删除定点推送（物理)
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/forceDelete")
    public CommonResult forceDelete(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 批量删除定点推送（逻辑)
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/deleteBatch")
    public CommonResult deleteBatch(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 批量删除定点推送（物理)
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/forceDeleteBatch")
    public CommonResult forceDeleteBatch(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 修改定点推送
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @PostMapping("/update")
    public CommonResult update(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 开启、关闭定点推送
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @PostMapping("modifyPushFlag")
    public CommonResult modifyPushFlag(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 分页查看定点推送
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @PostMapping("getPage")
    public CommonResult getPage(WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }
}
