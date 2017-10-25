package com.minstone.mp.wechat.contoller;

import com.minstone.mp.wechat.api.IWxMessagePushService;
import com.minstone.mp.wechat.common.CommonResult;
import com.minstone.mp.wechat.domain.WxMessagePush;
import com.minstone.mp.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;


/**
 * @author huangyg
 * @description
 * @since 2017/10/24
 */
@ResponseBody
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
    private IWxMessagePushService wxMessagePushService;

    /**
     * 添加定点提送并且返回实体主键
     * @param wxMessagePush 定点推送实体
     * @return java.lang.String
     * @author huangyg
     */
    @PostMapping("/addReturnData")
    public CommonResult addReturnData(@RequestBody WxMessagePush wxMessagePush) throws WxErrorException{
        return ResultUtil.success();
    }

    /**
     * 添加定点提送
     * @param wxMessagePush 定点推送实体
     * @return java.lang.String
     * @author huangyg
     */
    @PostMapping("/addLocation")
    public CommonResult add(@RequestBody WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }

    /**
     * 删除定点推送（逻辑)
     * @param wxMessagePush 定点推送实体
     * @return com.minstone.mp.wechat.common.CommonResult
     * @author huangyg
     */

    @GetMapping("/deleteLocation")
    public CommonResult delete(@RequestBody WxMessagePush wxMessagePush){
        return ResultUtil.success();
    }


}
