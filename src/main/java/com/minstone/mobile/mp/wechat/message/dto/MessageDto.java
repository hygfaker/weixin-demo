package com.minstone.mobile.mp.wechat.message.dto;

import lombok.Data;

/**
 * @author huangyg
 * @description
 * @since 2017/11/29
 */
@Data
public class MessageDto {
    /**
     * 用户昵称
     */
    private String userName;
    /*
     * 回复内容
     */
    private String replyContent;
    /**
     * 创建日期
     */
    private String createDate;
    /**
     * 头像链接
     */
    private String imgUrl;
    /**
     * 消息内容
     */
    private String content;
    /*
     * 消息状态
     */
    private Integer msgFlag;
}
