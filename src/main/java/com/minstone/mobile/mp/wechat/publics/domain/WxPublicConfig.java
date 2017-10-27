package com.minstone.mobile.mp.wechat.publics.domain;

public class WxPublicConfig {
    private String configCode;

    private String publicCode;

    private Integer cusmenuFlag;

    private Integer toKefuFlag;

    private Integer pushFlag;

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public Integer getCusmenuFlag() {
        return cusmenuFlag;
    }

    public void setCusmenuFlag(Integer cusmenuFlag) {
        this.cusmenuFlag = cusmenuFlag;
    }

    public Integer getToKefuFlag() {
        return toKefuFlag;
    }

    public void setToKefuFlag(Integer toKefuFlag) {
        this.toKefuFlag = toKefuFlag;
    }

    public Integer getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(Integer pushFlag) {
        this.pushFlag = pushFlag;
    }
}