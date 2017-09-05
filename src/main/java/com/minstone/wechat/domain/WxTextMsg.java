package com.minstone.wechat.domain;

import java.sql.Date;
import java.util.Map;

/**
 * Created by huangyg on 2017/9/5.
 */
public class WxTextMsg {
    private int textMsgCode;
    private String toUserName;
    private String fromUserName;
    private Date createTime;
    private String msgType;
    private String msgId;
    private String mediaId;
    private String formate;
    private String recognition;



//    public WxTextMsg() {
//
//    }

    public WxTextMsg() {
        super();
    }

    public int getTextMsgCode() {
        return textMsgCode;
    }

    public void setTextMsgCode(int textMsgCode) {
        this.textMsgCode = textMsgCode;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormate() {
        return formate;
    }

    public void setFormate(String formate) {
        this.formate = formate;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
