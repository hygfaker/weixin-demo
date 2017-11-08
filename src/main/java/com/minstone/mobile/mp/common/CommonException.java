package com.minstone.mobile.mp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonException extends RuntimeException {

    public static Logger logger = LoggerFactory.getLogger(CommonException.class);

    private static final long serialVersionUID = -8072930252478967506L;

    protected String msg;
    protected int code = 500;

    public CommonException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
        logger.error(this.msg);
    }

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        logger.error(this.msg);
    }

    public CommonException(ResultEnum resultEnum,String msg) {
        super(msg + resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = msg + resultEnum.getMsg();
        logger.error(this.msg);
    }

    public CommonException() {
    }

    @Override
    public String getMessage() {
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
        logger.error(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
        logger.error(cause.getMessage());
    }

    public CommonException(String message) {
        super(message);
        logger.error(message);
    }
}
