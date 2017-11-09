package com.minstone.mobile.mp.wechat.message.controller;

import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.ResultEnum;
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

    // 1-1. 添加定点推送


    // 2-1. 逻辑删除定点消息
    // 2-2. 物理删除定点消息
    // 2-3. 批量逻辑删除定点消息
    // 2-4. 批量物理删除定点消息

    // 3-1. 更新定点消息
    // 4-1. 修改定点消息状态

    // 5-1. 获取定点消息分页列表
    // 5-2. 获取定点消息

    @Autowired
    private WxMessagePushServiceImpl wxMessagePushService;

    /**
     * 1-1. 添加定点推送
     * @param wxMessagePush 定点推送实体
     * @return java.lang.String 定点推送主键
     * @author huangyg
     */
    @PostMapping("/add")
    public CommonResult add(WxMessagePush wxMessagePush) throws WxErrorException {
        return ResultUtil.success(wxMessagePushService.add(wxMessagePush));
    }

    /**
     * 2-1. 逻辑删除定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/delete")
    public CommonResult delete(WxMessagePush wxMessagePush) throws WxErrorException{
        return wxMessagePushService.delete(wxMessagePush) ? ResultUtil.success() : ResultUtil.failure(ResultEnum.SERVER_ERROR);
    }

    /**
     * 2-2. 物理删除定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/forceDelete")
    public CommonResult forceDelete(WxMessagePush wxMessagePush) throws WxErrorException{
        return wxMessagePushService.forceDelete(wxMessagePush) ? ResultUtil.success() : ResultUtil.failure(ResultEnum.SERVER_ERROR);
    }

    /**
     * 2-3. 批量逻辑删除定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/deleteBatch")
    public CommonResult deleteBatch(WxMessagePush wxMessagePush) throws WxErrorException{
        return wxMessagePushService.deleteBatch(wxMessagePush) ? ResultUtil.success() : ResultUtil.failure(ResultEnum.SERVER_ERROR);
    }

    /**
     * 2-4. 批量物理删除定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/forceDeleteBatch")
    public CommonResult forceDeleteBatch(WxMessagePush wxMessagePush) throws WxErrorException{
        return wxMessagePushService.forceDeleteBatch(wxMessagePush) ? ResultUtil.success() : ResultUtil.failure(ResultEnum.SERVER_ERROR);

    }

    /**
     * 3-1. 更新定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @PostMapping("/update")
    public CommonResult update(WxMessagePush wxMessagePush) throws WxErrorException{
        return ResultUtil.success(wxMessagePushService.update(wxMessagePush));
    }

    /**
     * 4-1. 修改定点消息状态
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/updateFlag")
    public CommonResult updateFlag(WxMessagePush wxMessagePush) throws WxErrorException{
        return ResultUtil.success(wxMessagePushService.updateFlag(wxMessagePush));
    }

    /**
     * 5-1. 获取定点消息分页列表
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/getPage")
    public CommonResult getPage(WxMessagePush wxMessagePush,@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, @RequestParam(value = "pageSize",defaultValue = "20") int pageSize) throws WxErrorException{
        return ResultUtil.pageFormat(wxMessagePushService.getPage(wxMessagePush,currentPage,pageSize));
    }

    /**
     * 5-2. 获取定点消息
     * @param wxMessagePush 定点推送实体
     * @return CommonResult
     * @author huangyg
     */
    @GetMapping("/get")
    public CommonResult getPage(WxMessagePush wxMessagePush) throws WxErrorException{
        return ResultUtil.success(wxMessagePushService.get(wxMessagePush));
    }
}
