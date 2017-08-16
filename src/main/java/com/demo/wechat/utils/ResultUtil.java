package com.demo.wechat.utils;

import com.demo.wechat.bean.Result;
import com.demo.wechat.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyg on 2017/8/7.
 */
public class ResultUtil {
    public static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        logger.info(result.toString());
        return result;
    }

    public static Result failure(ResultEnum resultEnums){
        Result result = new Result();
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        logger.info(result.toString());
        return result;
    }
    public static Result failure(ResultEnum resultEnums,String msg){
        Result result = new Result();
        result.setCode(resultEnums.getCode());
        result.setMsg(msg);
        logger.info(result.toString());
        return result;
    }


    public static Result success(){
        return success(null);
    }
}
