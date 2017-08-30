package com.minstone.wechat.handler;

import com.minstone.wechat.model.ErrorCodeMsg;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.utils.ResultUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

            String msg = ErrorCodeMsg.errorMsg(exception.getError().getErrorCode());
            if (msg.equals("unknown")){
                msg = exception.getError().getErrorMsg();
            }
            return ResultUtil.failure(ResultEnum.SERVER_ERROR,msg);

        }else if (e instanceof MissingServletRequestParameterException){ // 参数缺失

            MissingServletRequestParameterException exception= (MissingServletRequestParameterException)e;
            String msg = "【" + exception.getParameterType() + "】" + "类型的"
                    + "【" + exception.getParameterName() + "】" + "参数缺失。";
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,msg);
        }else{
            logger.error("【系统异常】= {}",e);
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }
}
