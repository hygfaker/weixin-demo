package com.minstone.mobile.mp.wechat.reply.domain;

import com.minstone.mobile.mp.utils.IdGen;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 消息回复
 */
@Data
public class WxReply {

    private enum REPLY_TYPE{
        SUBSCRIPT_REPLY(0),
        NO_KEYWORD_REPLY(1),
        KEYWORD_REPLY(2);

        private int replytype;

        REPLY_TYPE(int replytype) {
            this.replytype = replytype;
        }

        public int getReplytype() {
            return replytype;
        }

        public void setReplytype(int replytype) {
            this.replytype = replytype;
        }
    }

    public WxReply() {super();}

    public WxReply(String publicCode){
        this.setPublicCode(publicCode);
    }

    /**
     *
 * @param publicCode
 * @param content
 * @param replyType
     * @return
     * @author huangyg
     */

    public WxReply(String publicCode , String content , Integer replyType){
        this.setPublicCode(publicCode);
        this.setReplyCode(IdGen.uuid());
        this.setContent(content);
        this.setReplyType(replyType);
    }

    private String replyCode;

    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicCode;

    @NotNull(message = "【replyType】参数缺失（且内容不为空）")
    @Min(value = 0, message = "replyType 的值为0表示关注时回复，1表示非关键词回复，2表示关键词回复")
    @Max(value = 2, message = "replyType 的值为0表示关注时回复，1表示非关键词回复，2表示关键词回复")
    private Integer replyType;

    @NotEmpty(message = "【content】参数缺失（且内容不为空）")
    private String content;

    @NotNull(message = "【replyFlag】参数缺失（且内容不为空）")
    @Min(value = 0, message = "replyFlag 的值只能为0或者为1")
    @Max(value = 1, message = "replyFlag 的值只能为0或者为1")
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