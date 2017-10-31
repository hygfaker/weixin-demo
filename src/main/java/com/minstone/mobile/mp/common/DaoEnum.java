package com.minstone.mobile.mp.common;

/**
 * @author huangyg
 * @description 查询数据库的结果
 * @since 2017/10/27
 */
public enum DaoEnum {


    PUBLIC_NOTFOUND("找不到该公众号信息"),
    FORCE_DELETE_IMG("强制删除公众号图片信息出错"),
    PUBLIC_IMG_NOTFOUND("找不到该公众号图片信息");


    private String message;

    DaoEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
