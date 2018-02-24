package com.minstone.mobile.mp.common.constants;

/**
 * @author huangyg
 * @description
 * @since 2017/8/9.
 */
public enum CommonResultEnum {

    /*****************   接口规范返回状态码及描述 *****************/
    /**
     * 200,"执行成功"
     */
    SUCCESS(200,"执行成功"),

    /**
     * 400,"参数缺失"
     */
    PARAM_ERROR(400,"参数缺失"),

    /**
     * 401,"缺失鉴权信息"
     */
    UNAUTHORIZED(401,"缺失鉴权信息"),
    /**
     * 403,"操作不允许"
     */
    FORBIDDEN(403,"操作不允许"),
    /**
     * 404,"资源无法访问/不存在"
     */
    NOTFOUND_ERROR(404,"资源无法访问/不存在"),
    /**
     * 500,"服务器异常"
     */
    SERVER_ERROR(500,"服务器异常"),


    /***************** 接口的其他友好提示 *****************/
    /**
     * 404,"找不到该公众号信息"
     */
    PUBLIC_NOTFOUND(404,"找不到该公众号信息"),

    /**
     * 500,"文件已经存在"
     */
    FILE_EXIST(500,"文件已经存在"),

    /**
     * 500,"公众号已经存在"
     */
    PUBLIC_EXIST(500,"公众号已经存在"),

    /**
     * 500,"上传头像失败"
     */
    HEAD_ERROR(500,"上传头像失败"),

    /**
     * 500,"更新公众号信息失败"
     */
    UPDATE_PUBLIC_ERROR(500,"更新公众号信息失败"),

    /**
     * 500,"上传二维码失败"
     */
    QRCODE_ERROR(500,"上传二维码失败"),
    /**
     * 500,"保存公众号信息失败"
     */
    SAVE_PUBLIC_ERROR(500,"保存公众号信息失败"),

    /**
     * 500,"保存公众号信息失败"
     */
    ICON_TYPE_ERROR(500,"图标信息有误，imgType ：0 表示头像，1 表示二维码"),

    /**
     * 500,"头像或二维码图片为空"
     */
    IMG_NOT_NULL(500,"头像或二维码图片不能为空"),
    /**
     * 500,"强制删除公众号图片信息出错"
     */
    FORCE_DELETE_IMG(500,"强制删除公众号图片信息出错"),
    /**
     * 500,"更新图片失败"
     */
    UPDATE_IMG_ERROR(500,"更新图片失败"),
    /**
     * 500,"更新关键词失败"
     */
    UPDATE_KEYWORD_ERROR(500,"更新关键词失败"),
    /**
     * 400,"该参数必须大于0"
     */
    PARAME_LIMITE_POSITIVE(400,"该参数必须大于0"),
    /**
     * 500,"批量删除出错"
     */
    BATCH_DELETE_ERROR(500,"批量删除出错"),
    /**
     * 400,"参数有误"
     */
    PARAME_ERROR(400,"参数有误"),

    /**
     * 400,"参数有误"
     */
    KEYWORD_PARAME_ERROR(400,"传入参数有误，关键词不能为空"),

    /**
     * 400,"该公众号暂时没有回复内容"
     */
    REPLY_CONTENT_NOTFOUND(400,"该公众号暂时没有回复内容"),


    /**
     * 500,"更新公众号回复内容出错"
     */
    UPDATE_REPLY_CONTENT_ERROR(500,"更新公众号回复内容出错"),

    /**
     * 500,"客服账号参数缺失"
     */
    KEFU_ACCOUNT_ERROR(500,"客服账号参数缺失"),
    /**
     * 500,"更新客服头像出错"
     */
    KEFU_HEADIMG_ERROR(500,"更新客服头像出错"),
    /**
     * 500,"需要绑定的客服微信号参数缺失"
     */
    KEFU_INVITE_ERROR(500,"需要绑定的客服微信号参数缺失"),
    /**
     * 500,"客服昵称参数缺失"
     */
    KEFU_NICKNAME_ERROR(500,"客服昵称参数缺失"),


    /**
     * 500,"保存公众号回复内容出错"
     */
    SAVE_REPLY_CONTENT_ERROR(500,"保存公众号回复内容出错"),

    /**
     * 500,"保存公众号回复内容出错"
     */
    KEYWORDS_PARAME_ERROR(500,"关键词列表参数为空"),

    /**
     * 500,"关键词已经存在"
     */
    KEYWORD_HAS_EXISTED(500,"关键词已经存在"),

    /**
     * 500,"保存定点消息出错"
     */
    SAVE_LOCATION_PUSH_ERROR(500,"保存定点消息出错"),

    /**
     * 500,"定点消息已经存在"
     */
    LOCATION_PUSH_EXISTED(500,"定点消息已经存在"),

    /**
     * 500,"该消息已经回复，不能再回复"
     */
    MESSAGE_NO_REPEAT(500,"该消息已经回复，不能再回复"),
    /**
     * 404,"找不到该公众号图片信息"
     */
    PUBLIC_IMG_NOTFOUND(404,"找不到该公众号图片信息"),

    /**
     * 404,"找不到该定点消息"
     */
    LOCATION_PUSH_NOTFOUND(404,"找不到该定点消息"),

    /**
     * 404,"该公众号没有相应回复类型信息"
     */
    REPLY_TYPE_NOTFOUND(404,"该公众号没有相应回复类型信息"),

    /**
     * 404,"没有该消息信息"
     */
    MESSAGE_NOTFOUND(404,"没有该消息信息"),

    /**
     * 404,"该公众号没有相应回复类型信息"
     */
    PUBLIC_CONFIG_NOTFOUND(404,"该公众号没有相应配置信息");




    private int code;
    private String message;

    CommonResultEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return message;
    }

}
