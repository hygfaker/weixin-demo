package com.demo.wechat.utils;

import com.demo.wechat.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyg on 2017/8/7.
 */
public class ResultUtils {
    public static Logger logger = LoggerFactory.getLogger(ResultUtils.class);
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        logger.info(result.toString());
        return result;
    }

    public static Result failure(String msg, Integer code){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        logger.info(result.toString());
        return result;
    }

    public static Result success(){
        return success(null);
    }
}
