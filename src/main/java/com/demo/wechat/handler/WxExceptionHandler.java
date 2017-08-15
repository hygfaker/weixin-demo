package com.demo.wechat.handler;

import com.demo.wechat.bean.Result;
import com.demo.wechat.enums.ResultEnum;
import com.demo.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by huangyg on 2017/8/15.
 */
@ControllerAdvice
public class WxExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(WxExceptionHandler.class);

    // 设置捕获异常的类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){

        if (e instanceof WxErrorException){
            WxErrorException exception = (WxErrorException)e;
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }else {
            logger.error("【系统异常】={}",e);
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }
}
