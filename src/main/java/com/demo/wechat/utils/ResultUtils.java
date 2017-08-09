package com.demo.wechat.utils;

import com.demo.wechat.bean.Result;
import com.demo.wechat.enums.ResultEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyg on 2017/8/7.
 */
public class ResultUtils {
    public static Logger logger = LoggerFactory.getLogger(ResultUtils.class);
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnums.SUCCESS.getCode());
        result.setMsg(ResultEnums.SUCCESS.getMsg());
        result.setData(object);
        logger.info(result.toString());
        return result;
    }

    public static Result failure(ResultEnums resultEnums){
        Result result = new Result();
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        logger.info(result.toString());
        return result;
    }

    public static Result success(){
        return success(null);
    }
}
