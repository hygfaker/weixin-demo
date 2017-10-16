package com.minstone.wechat.utils;

import com.minstone.wechat.model.Result;
import com.minstone.wechat.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyg on 2017/8/7.
 */
public final class ResultUtil {


    public static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    private ResultUtil() {
        throw new RuntimeException("this is a util class,can not instance!");
    }

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
//        result.setData(new int[0]);
        logger.info(result.toString());
        return result;
    }
    public static Result failure(ResultEnum resultEnums,String msg){
        Result result = failure(resultEnums);
        result.setDesc(msg);
        return result;
    }

    //    DAO 操作统一在这里处理：对返回的数据做结果判断
    public static Result returnResult(Integer result){
        if (result > 0){
            return success();
        } else if (result == 0 || result == -1){     // 0表示 update 时候失败 ， -1表示 select 的时候没找到资源
            return failure(ResultEnum.NOTFOUND_ERROR);
        } else if (result == ResultEnum.PUBLIC_NOTFOUND.getCode()){
            return failure(ResultEnum.NOTFOUND_ERROR);
        } else{
            return failure(ResultEnum.SERVER_ERROR);
        }
    }

    public static Result success(){
        return success(new int[0]);
    }
}
