package com.minstone.mobile.mp.wechat.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfMsgRecord;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huangyg on 2017/8/9.
 */
@RestController
@RequestMapping("/template")
public class WxTemplateController {

    @Autowired
    private WxMpService wxService;


    // 设置所属行业
    @PostMapping("/setIndustry")
    public CommonResult setIndustry(@RequestParam String primary, @RequestParam String second) throws WxErrorException{
        WxMpTemplateIndustry industry = new WxMpTemplateIndustry(new WxMpTemplateIndustry.Industry(primary),
                new WxMpTemplateIndustry.Industry(second));
        if (this.wxService.getTemplateMsgService().setIndustry(industry)){
           return ResultUtil.success(new int[0]);
        }else{
            throw new CommonException(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 获取设置的行业信息
    @GetMapping("/industry")
    public CommonResult getIndustry() throws WxErrorException{
           return ResultUtil.success(this.wxService.getTemplateMsgService().getIndustry());
    }
    // 获得模板列表
    @GetMapping("/list")
    public CommonResult getPage(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws WxErrorException {
        if (currentPage < 0) {
            throw new CommonException(CommonResultEnum.PARAME_LIMITE_POSITIVE);
        }
        if (pageSize < 0) {
            throw new CommonException(CommonResultEnum.PARAME_LIMITE_POSITIVE);
        }
        PageHelper.startPage(currentPage,pageSize);
        List<WxMpTemplate> resultList = this.wxService.getTemplateMsgService().getAllPrivateTemplate();

        // 分页处理
        PageInfo<WxMpTemplate> page = new PageInfo<>(resultList);
        int totalPages = (int) Math.ceil((double) page.getSize() / pageSize);
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        if (pageSize > page.getSize()) {
            currentPage = 1;
            pageSize = page.getSize();
        }

        page.setPageNum(currentPage);
        page.setPages(totalPages);// 向上取整
        int endIndex = (currentPage * pageSize) < page.getSize() ? (currentPage * pageSize) : page.getSize();

        int currentPageSize = (endIndex == pageSize) ? pageSize : (page.getSize() - (currentPage - 1) * pageSize);
        page.setPageSize(currentPageSize);

        page.setList(resultList.subList((currentPage - 1) * pageSize, endIndex));
        return ResultUtil.pageFormat(page);
    }

    // 删除模板
    @GetMapping("/delete")
    public CommonResult delPrivateTemplate(@RequestParam ("templateId") String templateId) throws WxErrorException{
        if (this.wxService.getTemplateMsgService().delPrivateTemplate(templateId)){
            return ResultUtil.success(new int[0]);
        }else{
            throw new CommonException(CommonResultEnum.SERVER_ERROR);
        }
    }

    // 发送模板消息
    @PostMapping("/send")
    public CommonResult sendTemplateMsg(@RequestBody WxMpTemplateMessage templateMessage) throws WxErrorException{
        String msgid = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        HashMap<String,String> res = new HashMap<>();
        res.put("msgid",msgid);
        return ResultUtil.success(res);
    }
}
