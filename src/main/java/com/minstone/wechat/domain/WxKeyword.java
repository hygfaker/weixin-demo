package com.minstone.wechat.domain;

public class WxKeyword {
    private String wxruleKeyword;

    private Integer wxruleCode;

    private Boolean wxruleMatch;

    public String getWxruleKeyword() {
        return wxruleKeyword;
    }

    public void setWxruleKeyword(String wxruleKeyword) {
        this.wxruleKeyword = wxruleKeyword == null ? null : wxruleKeyword.trim();
    }

    public Integer getWxruleCode() {
        return wxruleCode;
    }

    public void setWxruleCode(Integer wxruleCode) {
        this.wxruleCode = wxruleCode;
    }

    public Boolean getWxruleMatch() {
        return wxruleMatch;
    }

    public void setWxruleMatch(Boolean wxruleMatch) {
        this.wxruleMatch = wxruleMatch;
    }
}