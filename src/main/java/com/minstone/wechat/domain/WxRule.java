package com.minstone.wechat.domain;

public class WxRule {
    private Integer wxruleCode;

    private Integer wxpublicCode;

    private String wxruleName;

    private String wxruleOn;

    private Boolean wxruleKefu;

    private String wxruleContent;

    public Integer getWxruleCode() {
        return wxruleCode;
    }

    public void setWxruleCode(Integer wxruleCode) {
        this.wxruleCode = wxruleCode;
    }

    public Integer getWxpublicCode() {
        return wxpublicCode;
    }

    public void setWxpublicCode(Integer wxpublicCode) {
        this.wxpublicCode = wxpublicCode;
    }

    public String getWxruleName() {
        return wxruleName;
    }

    public void setWxruleName(String wxruleName) {
        this.wxruleName = wxruleName == null ? null : wxruleName.trim();
    }

    public String getWxruleOn() {
        return wxruleOn;
    }

    public void setWxruleOn(String wxruleOn) {
        this.wxruleOn = wxruleOn == null ? null : wxruleOn.trim();
    }

    public Boolean getWxruleKefu() {
        return wxruleKefu;
    }

    public void setWxruleKefu(Boolean wxruleKefu) {
        this.wxruleKefu = wxruleKefu;
    }

    public String getWxruleContent() {
        return wxruleContent;
    }

    public void setWxruleContent(String wxruleContent) {
        this.wxruleContent = wxruleContent == null ? null : wxruleContent.trim();
    }
}