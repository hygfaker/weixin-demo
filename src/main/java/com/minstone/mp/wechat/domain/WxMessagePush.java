package com.minstone.mp.wechat.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WxMessagePush {

    private String pushCode;

    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicCode;
    @NotEmpty(message = "【pushName】参数缺失（且内容不为空）")
    private String pushName;
    @NotNull(message = "【latitude】参数缺失")
    private Float latitude;
    @NotNull(message = "【longitude】参数缺失")
    private Float longitude;
    @NotNull(message = "【radius】参数缺失")
    private Float radius;
    @NotEmpty(message = "【content】参数缺失（且内容不为空）")
    private String content;
    @NotNull(message = "【pushFlag】参数缺失（且内容不为空）")
    @Min(value = 0, message = "pushFlag 的值只能为0或者为1")
    @Max(value = 1, message = "pushFlag 的值只能为0或者为1")
    private Integer pushFlag;

    private String creator;

    private String createDate;

    private String modifyDate;

    private String modifyer;

    private Integer delFlag;

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode == null ? null : pushCode.trim();
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName == null ? null : pushName.trim();
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(Integer pushFlag) {
        this.pushFlag = pushFlag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate == null ? null : modifyDate.trim();
    }

    public String getModifyer() {
        return modifyer;
    }

    public void setModifyer(String modifyer) {
        this.modifyer = modifyer == null ? null : modifyer.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}