package com.minstone.wechat.domain;

import com.minstone.wechat.utils.code.IdGen;

public class WxReply {

    public WxReply() {super();}

    public WxReply(String publicCode , String content , Integer replyType){
        this.setPublicCode(publicCode);
        this.setReplyCode(IdGen.uuid());
        this.setContent(content);
        this.setReplyType(replyType);
    }

    private String replyCode;

    private String publicCode;

    private Integer replyType;

    private String content;

    private Integer replyFlag;

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Integer replyFlag) {
        this.replyFlag = replyFlag;
    }
}