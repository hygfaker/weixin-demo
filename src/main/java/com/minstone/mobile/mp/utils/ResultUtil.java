package com.minstone.mobile.mp.utils;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/7.
 */
public final class ResultUtil {


    public static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    private ResultUtil() {
        throw new RuntimeException("该类为工具类，无法实例化...");
    }

    /**
     * 操作成功返回,如：更新成功，删除成功等
     * @return
     */
    public static CommonResult success(){
        return success(new int[0]);
    }

    /**
     * 操作成功返回，如：查找成功
     * @param data 返回的数据列表
     * @param pager 分页信息，包括当前页，页总数等
     * @return
     */
    public static CommonResult success(Object data, Object pager){
        CommonResult result = new CommonResult();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setDesc(ResultEnum.SUCCESS.getMsg());
        result.setTime(DateUtil.getStringDate());
        result.setData(data);
        result.setPager(pager);
        logger.info("返回结果：" + result.toString());
        logger.info("============================ end ====================================");
        return result;
    }

    /**
     * 操作成功返回，如：增加成功，查找成功等
     * @param object 返回的数据
     * @return
     */
    public static CommonResult success(Object object){
        CommonResult result = new CommonResult();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setDesc(ResultEnum.SUCCESS.getMsg());
        result.setTime(DateUtil.getStringDate());
        result.setData(object);
        logger.info("返回结果：" + result.toString());
        logger.info("============================ end ====================================");

        return result;
    }

    /**
     * 操作失败返回
     * @param resultEnums 公司规范，操作失败的信息实体
     * @return
     */
    public static CommonResult failure(ResultEnum resultEnums){
        CommonResult result = new CommonResult();
        result.setStatus(resultEnums.getCode());
        result.setTime(DateUtil.getStringDate());
        result.setDesc(resultEnums.getMsg());
        logger.info("返回结果：" + result.toString());
        logger.info("============================ end ====================================");

        return result;
    }

    /**
     * 操作失败返回
     * @param resultEnums 公司规范，操作失败的信息实体
     * @param msg 返回的具体信息
     * @return
     */
    public static CommonResult failure(ResultEnum resultEnums, String msg){
        CommonResult result = failure(resultEnums);
        result.setDesc(msg);
        return result;
    }

    /**
     * 操作失败返回，公司规范，操作失败的信息实体
     * @param code 错误码
     * @param msg 返回的具体信息
     * @return
     */
    public static CommonResult failure(int code, String msg){
        CommonResult result = new CommonResult();
        result.setStatus(code);
        result.setTime(DateUtil.getStringDate());
        result.setDesc(msg);
        logger.info("返回结果：" + result.toString());
        logger.info("============================ end ====================================");
        return result;
    }
/*
    pageIndex 当前页
    pageSize 每页数量
    pageCount 总页数
    itemCount 总记录数
*/

    /**
     * 格式化为公司标准接口返回页面内容实体
     * @param pageInfo PageHelper 实体
     * @return
     */
    public static CommonResult pageFormat(PageInfo pageInfo){
        Map<String,Object> pager = new HashMap<>();
        pager.put("pageIndex",Integer.valueOf(pageInfo.getPageNum()));
        pager.put("pageSize",Integer.valueOf(pageInfo.getPageSize()));
        pager.put("pageCount",Integer.valueOf(pageInfo.getPages()));
        pager.put("itemCount",Long.valueOf(pageInfo.getTotal()));

        List data = pageInfo.getList();
        return success(data,pager);

    }

//    /**
//     * DAO 操作统一在这里处理：对返回的数据做结果判断
//     * @param result dao操作返回结果
//     * @return
//     */
//    public static CommonResult returnResult(Integer result){
//        if (result > 0){
//            return success();
//        } else if (result == 0 || result == -1){     // 0表示 update 时候失败 ， -1表示 select 的时候没找到资源
//            return failure(ResultEnum.NOTFOUND_ERROR);
//        } else if (result == ResultEnum.PUBLIC_NOTFOUND.getCode()){
//            return failure(ResultEnum.NOTFOUND_ERROR);
//        } else{
//            return failure(ResultEnum.SERVER_ERROR);
//        }
//    }




}
