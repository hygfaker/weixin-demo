package com.demo.wechat.domain;

import org.springframework.stereotype.Component;

/**
 * Created by huangyg on 2017/8/28.
 */
@Component
public class WXPublic {
    private int wxPublicCode;
    private String wxPublicOpenid;
    private String wxPublicName;
    private String wxPublicNickName;
    private String wxPublicHeadImg;
    private String wxPublicQrcode;
    private String wxPublicAppid;
    private String wxPublicAppSerct;
    private String wxPublicToken;
    private String wxPublicAeskey;
    private String wxPublicUrl;

    public int getWxPublicCode() {
        return wxPublicCode;
    }

    public void setWxPublicCode(int wxPublicCode) {
        this.wxPublicCode = wxPublicCode;
    }

    public String getWxPublicOpenid() {
        return wxPublicOpenid;
    }

    public void setWxPublicOpenid(String wxPublicOpenid) {
        this.wxPublicOpenid = wxPublicOpenid;
    }

    public String getWxPublicName() {
        return wxPublicName;
    }

    public void setWxPublicName(String wxPublicName) {
        this.wxPublicName = wxPublicName;
    }

    public String getWxPublicNickName() {
        return wxPublicNickName;
    }

    public void setWxPublicNickName(String wxPublicNickName) {
        this.wxPublicNickName = wxPublicNickName;
    }

    public String getWxPublicHeadImg() {
        return wxPublicHeadImg;
    }

    public void setWxPublicHeadImg(String wxPublicHeadImg) {
        this.wxPublicHeadImg = wxPublicHeadImg;
    }

    public String getWxPublicQrcode() {
        return wxPublicQrcode;
    }

    public void setWxPublicQrcode(String wxPublicQrcode) {
        this.wxPublicQrcode = wxPublicQrcode;
    }

    public String getWxPublicAppid() {
        return wxPublicAppid;
    }

    public void setWxPublicAppid(String wxPublicAppid) {
        this.wxPublicAppid = wxPublicAppid;
    }

    public String getWxPublicAppSerct() {
        return wxPublicAppSerct;
    }

    public void setWxPublicAppSerct(String wxPublicAppSerct) {
        this.wxPublicAppSerct = wxPublicAppSerct;
    }

    public String getWxPublicToken() {
        return wxPublicToken;
    }

    public void setWxPublicToken(String wxPublicToken) {
        this.wxPublicToken = wxPublicToken;
    }

    public String getWxPublicAeskey() {
        return wxPublicAeskey;
    }

    public void setWxPublicAeskey(String wxPublicAeskey) {
        this.wxPublicAeskey = wxPublicAeskey;
    }

    public String getWxPublicUrl() {
        return wxPublicUrl;
    }

    public void setWxPublicUrl(String wxPublicUrl) {
        this.wxPublicUrl = wxPublicUrl;
    }

}
