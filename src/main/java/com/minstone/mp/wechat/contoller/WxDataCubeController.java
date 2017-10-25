package com.minstone.mp.wechat.contoller;
import com.minstone.mp.wechat.utils.ResultUtil;
import com.minstone.mp.wechat.common.CommonResult;
import com.minstone.mp.wechat.utils.DateUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by huangyg on 2017/8/27.
 */
@RestController
@RequestMapping("data")
public class WxDataCubeController {

    // todo
    // 获取图文群发每日数据
    // 获取图文群发总数据
    // 获取图文统计数据
    // 获取图文统计分时数据
    // 获取图文分享转发数据
    // 获取图文分享转发分时数据

    @Autowired
    private WxMpService wxMpService;

    /**
     * <pre>
     * 获取图文群发每日数据（getarticlesummary）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度1天，endDate不能早于begingDate
     */
    @RequestMapping("/newsSummary")
    public CommonResult getArticleSummary(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getArticleSummary(begin,end));
    }

    /**
     * <pre>
     * 获取图文群发总数据（getarticletotal）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN
     *
     *  endDate
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度1天，endDate不能早于begingDate，最晚为昨天。
     */
    @RequestMapping("/newsTotal")
    public CommonResult getArticleTotal(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getArticleTotal(begin,end));
    }

    /**
     * <pre>
     * 获取图文统计数据（getuserread）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度3天，endDate不能早于begingDate
     */
    @RequestMapping("/newsRead")
    public CommonResult getUserRead(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getUserRead(begin,end));
    }

    /**
     * <pre>
     * 获取图文统计分时数据（getuserreadhour）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度1天，endDate不能早于begingDate
     */
    @RequestMapping("/newsHour")
    public CommonResult getUserReadHour(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getUserReadHour(begin,end));
    }

    /**
     * <pre>
     * 获取图文分享转发数据（getusershare）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度7天，endDate不能早于begingDate
     */
    @RequestMapping("/newsShare")
    public CommonResult getUserShare(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getUserShare(begin,end));
    }

    /**
     * <pre>
     * 获取图文分享转发分时数据（getusersharehour）
     * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
     * 接口url格式：https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN
     *
     * @param beginDate 开始时间
     * @param endDate   最大时间跨度1天，endDate不能早于begingDate
     */
    @RequestMapping("/newsShareHour")
    public CommonResult getUserShareHour(@RequestParam String beginDate, @RequestParam String endDate) throws WxErrorException {
        Date begin = DateUtil.dateToUnixTimestamp(beginDate, "yyyy-MM-dd");
        Date end = DateUtil.dateToUnixTimestamp(endDate, "yyyy-MM-dd");
        return ResultUtil.success(this.wxMpService.getDataCubeService().getUserShareHour(begin,end));
    }
}
