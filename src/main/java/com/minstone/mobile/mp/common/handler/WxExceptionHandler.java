package com.minstone.mobile.mp.common.handler;

import com.minstone.mobile.mp.common.CommonException;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.ResultEnum;
import com.minstone.mobile.mp.common.WxErrorMsg;
import com.minstone.mobile.mp.utils.ResultUtil;
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

/**
 * Created by huangyg on 2017/8/15.
 *
 * 1. 捕获部分数据库 dao 操作的异常(待办：看下与 CommonException 的区别)
 * 2. 捕获调用接口时参数必传参数未传的异常
 * 3. 捕获第三方微信框架的异常
 * 4. 捕获参数校验异常
 * 5. 系统其它异常
 * 6.（待补充）
 */
@ControllerAdvice
public class WxExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(WxExceptionHandler.class);

    // 设置捕获异常的类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult handle(Exception e){

        if (e instanceof MethodArgumentNotValidException){  // 参数校验异常
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
            return ResultUtil.failure(ResultEnum.PARAM_ERROR,msg);
        }
        if (e instanceof CommonException){
            CommonException exception = (CommonException)e;
            String msg = exception.getMsg();
            int code = exception.getCode();
            return ResultUtil.failure(code,msg);
        }
        if (e instanceof DataIntegrityViolationException){ // 数据库异常
            DataIntegrityViolationException exception = (DataIntegrityViolationException)e;

            String msg = exception.getCause().getMessage();

            return ResultUtil.failure(ResultEnum.SERVER_ERROR,msg);
        }
        if (e instanceof WxErrorException){ // 微信框架的异常

            WxErrorException exception = (WxErrorException)e;

            String msg = WxErrorMsg.errorMsg(exception.getError().getErrorCode());
            if ("unknown".equals(msg)){
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
