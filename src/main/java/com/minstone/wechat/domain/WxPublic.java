package com.minstone.wechat.domain;


import org.hibernate.validator.constraints.NotEmpty;
import java.util.Map;


/**
 * Created by huangyg on 2017/8/28.
 */
public class WxPublic {

    private int wxPublicCode;
    @NotEmpty
    private String wxPublicOpenid;
    @NotEmpty
    private String wxPublicName;
    @NotEmpty
    private String wxPublicNickName;
    @NotEmpty
    private byte[] wxPublicHeadImg;
    @NotEmpty
    private byte[] wxPublicQrcode;
    @NotEmpty
    private String wxPublicAppid;
    @NotEmpty
    private String wxPublicAppSerct;
    @NotEmpty
    private String wxPublicToken;
    @NotEmpty
    private String wxPublicAeskey;
    @NotEmpty
    private String wxPublicUrl;
    @NotEmpty
    private String wxPublicHeadImgName;
    @NotEmpty
    private String wxPublicQrcodeName;


    public WxPublic(Map<String,Object>reqMap) {
        this.setWxPublicOpenid((String) reqMap.get("wxPublicOpenid"));
        this.setWxPublicName((String) reqMap.get("wxPublicName"));
        this.setWxPublicNickName((String) reqMap.get("wxPublicNickName"));
        this.setWxPublicAppid((String) reqMap.get("wxPublicAppid"));
        this.setWxPublicAppSerct((String) reqMap.get("wxPublicAppSerct"));
        this.setWxPublicToken((String) reqMap.get("wxPublicToken"));
        this.setWxPublicAeskey((String) reqMap.get("wxPublicAeskey"));
        this.setWxPublicUrl((String) reqMap.get("wxPublicUrl"));
        this.setWxPublicHeadImgName((String) reqMap.get("wxPublicHeadImgName"));
        this.setWxPublicQrcodeName((String) reqMap.get("wxPublicQrcodeName"));
    }

    public WxPublic() {
        super();
    }


    public String getWxPublicHeadImgName() {
        return wxPublicHeadImgName;
    }

    public void setWxPublicHeadImgName(String wxPublicHeadImgName) {
        this.wxPublicHeadImgName = wxPublicHeadImgName;
    }

    public String getWxPublicQrcodeName() {
        return wxPublicQrcodeName;
    }

    public void setWxPublicQrcodeName(String wxPublicQrcodeName) {
        this.wxPublicQrcodeName = wxPublicQrcodeName;
    }

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

    public byte[] getWxPublicHeadImg() {
        return wxPublicHeadImg;
    }

    public void setWxPublicHeadImg(byte[] wxPublicHeadImg) {
        this.wxPublicHeadImg = wxPublicHeadImg;
    }

    public byte[] getWxPublicQrcode() {
        return wxPublicQrcode;
    }

    public void setWxPublicQrcode(byte[] wxPublicQrcode) {
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
