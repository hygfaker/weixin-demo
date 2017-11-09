package com.minstone.mobile.mp.wechat.message.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WxMessagePushRecord {

    public WxMessagePushRecord(String userCode){
        this.setUserCode(userCode);
    }
    public WxMessagePushRecord(String userCode,String pushCode){
        this.setUserCode(userCode);
        this.setPushCode(pushCode);
    }
    public WxMessagePushRecord(){}

    /**
     * 公众号原始 id，作查询参数使用
     */
    private String openId;

    @NotEmpty(message = "【recordCode】参数缺失（且内容不为空）")
    private String recordCode;

    @NotEmpty(message = "【pushCode】参数缺失（且内容不为空）")
    private String pushCode;

    @NotEmpty(message = "【userCode】参数缺失（且内容不为空）")
    private String userCode;

    @NotEmpty(message = "【pushDate】参数缺失（且内容不为空）")
    private String pushDate;

    private Integer pushState;

    @NotNull(message = "【delFlag】参数缺失（且内容不为空）")
    @Min(value = 0, message = "delFlag 的值只能为0或者为1")
    @Max(value = 1, message = "delFlag 的值只能为0或者为1")
    private Integer delFlag;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode == null ? null : recordCode.trim();
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode == null ? null : pushCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPushDate() {
        return pushDate;
    }

    public void setPushDate(String pushDate) {
        this.pushDate = pushDate == null ? null : pushDate.trim();
    }

    public Integer getPushState() {
        return pushState;
    }

    public void setPushState(Integer pushState) {
        this.pushState = pushState;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}