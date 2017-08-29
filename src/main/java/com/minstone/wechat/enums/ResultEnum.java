package com.minstone.wechat.enums;

/**
 * Created by huangyg on 2017/8/9.
 */
public enum ResultEnum {
    SUCCESS(200,"执行成功"),
    PARAM_ERROR(400,"参数缺失"),
    UNAUTHORIZED(401,"缺失鉴权信息"),
    FORBIDDEN(403,"操作不允许"),
    NOTFOUND_ERROR(404,"资源无法访问/不存在"),
    SERVER_ERROR(500,"服务器异常");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
