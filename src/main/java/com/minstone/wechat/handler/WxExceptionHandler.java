package com.minstone.wechat.handler;

import com.minstone.wechat.model.ErrorCodeMsg;
import com.minstone.wechat.model.Result;
import com.minstone.wechat.enums.ResultEnum;
import com.minstone.wechat.utils.ResultUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.lang.reflect.Method;

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

        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,msg);
        }

        if (e instanceof DataIntegrityViolationException){ // 数据库报错
            DataIntegrityViolationException exception = (DataIntegrityViolationException)e;

            String msg = exception.getCause().getMessage();

            return ResultUtil.failure(ResultEnum.SERVER_ERROR,msg);
        }
        if (e instanceof WxErrorException){ // 微信框架的报错

            WxErrorException exception = (WxErrorException)e;

            String msg = ErrorCodeMsg.errorMsg(exception.getError().getErrorCode());
            if (msg.equals("unknown")){
                msg = exception.getError().getErrorMsg();
            }
            return ResultUtil.failure(ResultEnum.SERVER_ERROR,msg);

        }else if (e instanceof MissingServletRequestParameterException){ // 控制器提交参数缺失

            MissingServletRequestParameterException exception= (MissingServletRequestParameterException)e;
            String msg = "【" + exception.getParameterType() + "】" + "类型的"
                    + "【" + exception.getParameterName() + "】" + "参数缺失。";
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,msg);

        }else if (e instanceof MissingServletRequestPartException){  // 控制器提交参数缺失

            MissingServletRequestPartException exception= (MissingServletRequestPartException)e;
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,exception.getMessage());
        }
        else{
            logger.error("【系统异常】= {}",e);
            return ResultUtil.failure(ResultEnum.SERVER_ERROR);
        }
    }
}
