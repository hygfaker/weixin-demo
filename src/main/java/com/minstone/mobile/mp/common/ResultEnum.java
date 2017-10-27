package com.minstone.mobile.mp.common;

/**
 * Created by huangyg on 2017/8/9.
 */
public enum ResultEnum {

//    接口规范返回状态码及描述
    SUCCESS(200,"执行成功"),
    PARAM_ERROR(400,"参数缺失"),
    UNAUTHORIZED(401,"缺失鉴权信息"),
    FORBIDDEN(403,"操作不允许"),
    NOTFOUND_ERROR(404,"资源无法访问/不存在"),
    SERVER_ERROR(500,"服务器异常"),

//    DAO操作返回状态及描述
    PUBLIC_NOTFOUND(-9999,"找不到该公众号");

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
