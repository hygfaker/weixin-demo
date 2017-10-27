package com.minstone.mobile.mp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -8072930252478967506L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonException.class);
    public static final CommonException DB_INSERT_RESULT_0 = new CommonException(90040001, "数据库操作,insert返回0", new Object[0]);
    public static final CommonException DB_UPDATE_RESULT_0 = new CommonException(90040002, "数据库操作,update返回0", new Object[0]);
    public static final CommonException DB_SELECTONE_IS_NULL = new CommonException(90040003, "数据库操作,selectOne返回null", new Object[0]);
    public static final CommonException DB_LIST_IS_NULL = new CommonException(90040004, "数据库操作,list返回null", new Object[0]);
    public static final CommonException TOKEN_IS_ILLICIT = new CommonException(90040005, "Token 验证非法", new Object[0]);
    public static final CommonException SESSION_IS_OUT_TIME = new CommonException(90040006, "会话超时", new Object[0]);
    public static final CommonException DB_GET_SEQ_NEXT_VALUE_ERROR = new CommonException(90040007, "获取序列出错", new Object[0]);
    protected String msg;
    protected int code = 500;

    public CommonException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }
    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public CommonException(ResultEnum resultEnum,String msg) {
        super(msg);
        this.code = resultEnum.getCode();
        this.msg = msg;
    }

    public CommonException() {
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public CommonException newInstance(String msgFormat, Object... args) {
        return new CommonException(this.code, msgFormat, args);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
        LOGGER.error(cause.getMessage());
    }

    public CommonException(String message) {
        super(message);
        LOGGER.error(message);
    }
}
