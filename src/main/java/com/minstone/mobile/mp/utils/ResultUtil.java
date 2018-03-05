package com.minstone.mobile.mp.utils;

import com.github.pagehelper.PageInfo;
import com.minstone.mobile.mp.common.CommonResult;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by huangyg on 2017/8/7.
 */
public final class ResultUtil {


    public static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    private ResultUtil() {
        throw new RuntimeException("该类为工具类，无法实例化...");
    }

    /**
     * 操作成功返回,如：更新成功，删除成功等，不需要返回 data 字段的内容
     * @return
     */
    public static CommonResult success(){
        return success(null);
    }

    /**
     * 操作成功返回，如：查找成功
     * @param data 返回的数据列表
     * @param pager 分页信息，包括当前页，页总数等
     * @return
     */
    public static CommonResult success(Object data, CommonResult.CommonPage pager){
        CommonResult result = new CommonResult();
        result.setStatus(CommonResultEnum.SUCCESS.getCode());
        result.setDesc(CommonResultEnum.SUCCESS.getMsg());
        result.setTime(DateUtil.getResultStringDate());
        result.setData(data);
        result.setPager(pager);
        logger.info("返回结果：" + result);
        logger.info("============================ end ===============================");
        return result;
    }

    /**
     * 操作成功返回，如：增加成功，查找成功等
     * @param object 返回的数据
     * @return
     */
    public static CommonResult success(Object object){
        CommonResult result = new CommonResult();
        result.setStatus(CommonResultEnum.SUCCESS.getCode());
        result.setDesc(CommonResultEnum.SUCCESS.getMsg());
        result.setTime(DateUtil.getResultStringDate());
        result.setData(object);
        logger.info("返回结果：" + JsonUtil.toJson(result));
        logger.info("============================ end ===============================");

        return result;
    }

    /**
     * 操作失败返回
     * @param resultEnums 公司规范，操作失败的信息实体
     * @return
     */
    public static CommonResult failure(CommonResultEnum resultEnums){
        CommonResult result = new CommonResult();
        result.setStatus(resultEnums.getCode());
        result.setTime(DateUtil.getResultStringDate());
        result.setDesc(resultEnums.getMsg());
        logger.info("返回结果：" + JsonUtil.toJson(result));
        logger.info("============================ end ===============================");

        return result;
    }

    /**
     * 操作失败返回
     * @param resultEnums 公司规范，操作失败的信息实体
     * @param msg 返回的具体信息
     * @return
     */
    public static CommonResult failure(CommonResultEnum resultEnums, String msg){
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
        result.setTime(DateUtil.getResultStringDate());
        result.setDesc(msg);
        logger.info("返回结果：" + JsonUtil.toJson(result));
        logger.info("============================ end ===============================");
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
        CommonResult.CommonPage pager = new CommonResult.CommonPage(pageInfo);
        List data = pageInfo.getList();
        return success(data,pager);
    }




}
