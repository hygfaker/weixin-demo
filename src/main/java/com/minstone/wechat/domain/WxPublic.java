package com.minstone.wechat.domain;

import java.util.Map;

public class WxPublic {
    private Integer publicCode;

    private String openId;

    private String publicName;

    private String publicNickname;

    private String appId;

    private String appSerct;

    private String token;

    private String aeskey;

    private String url;

    private String creator;

    private String createDate;

    private String modifyDate;

    private String modifyer;

    private Integer delFlag;


    public WxPublic(Map<String,Object> reqMap) {
        this.publicCode = (Integer) reqMap.get("publicCode");
        this.openId = (String) reqMap.get("openId");
        this.publicName = (String) reqMap.get("publicName");
        this.publicNickname = (String) reqMap.get("publicNickname");
        this.appId = (String) reqMap.get("appId");
        this.appSerct = (String) reqMap.get("appSerct");
        this.token = (String) reqMap.get("token");
        this.aeskey = (String) reqMap.get("aeskey");
        this.url = (String) reqMap.get("url");
        this.creator = (String) reqMap.get("creator");
        this.createDate = (String) reqMap.get("createDate");
        this.modifyDate = (String) reqMap.get("modifyDate");
        this.modifyer = (String) reqMap.get("modifyer");
        this.delFlag = (Integer) reqMap.get("delFlag");
    }

    public Integer getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(Integer publicCode) {
        this.publicCode = publicCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName == null ? null : publicName.trim();
    }

    public String getPublicNickname() {
        return publicNickname;
    }

    public void setPublicNickname(String publicNickname) {
        this.publicNickname = publicNickname == null ? null : publicNickname.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSerct() {
        return appSerct;
    }

    public void setAppSerct(String appSerct) {
        this.appSerct = appSerct == null ? null : appSerct.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAeskey() {
        return aeskey;
    }

    public void setAeskey(String aeskey) {
        this.aeskey = aeskey == null ? null : aeskey.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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