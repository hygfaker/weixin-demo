package com.minstone.mobile.mp.common;

/**
 * @author huangyg
 * @description 查询数据库的结果
 * @since 2017/10/27
 */
public enum DaoEnum {


    PUBLIC_NOTFOUND("找不到该公众号信息"),
    SAVE_PUBLIC_ERROR("保存公众号信息失败"),
    FORCE_DELETE_IMG("强制删除公众号图片信息出错"),
    UPDATE_IMG_ERROR("更新图片失败"),
    PARAME_LIMITE_POSITIVE("该参数必须大于0"),
    BATCH_DELETE_ERROR("批量删除有误，请确保参数传递正确"),
    PARAME_ERROR("参数有误"),
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
