package com.demo.wechat.utils;

import com.demo.wechat.bean.Result;
import com.demo.wechat.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by huangyg on 2017/8/7.
 */
public class ResultUtil {
    public static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static Result success(Object object){
        Result result = new Result();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setDesc(ResultEnum.SUCCESS.getMsg());
        result.setTime(DateUtil.getStringDate());
        result.setData(object);
        logger.info(result.toString());
        return result;
    }

    public static Result failure(ResultEnum resultEnums){
        Result result = new Result();
        result.setStatus(resultEnums.getCode());
        result.setTime(DateUtil.getStringDate());
        result.setDesc(resultEnums.getMsg());
        logger.info(result.toString());
        return result;
    }
    public static Result failure(ResultEnum resultEnums,String msg){
        Result result = failure(resultEnums);
        result.setDesc(msg);
        return result;
    }


    public static Result success(){
        return success(null);
    }
}
