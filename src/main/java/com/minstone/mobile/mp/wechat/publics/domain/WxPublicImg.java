package com.minstone.mobile.mp.wechat.publics.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class WxPublicImg {
    @NotEmpty
    private String imgCode;
    @NotNull
    private byte[] headimg;
    @NotNull
    private byte[] qrcode;

    public WxPublicImg(String imgCode, byte[] headimg, byte[] qrcode) {
        this.setImgCode(imgCode);
        this.setHeadimg(headimg);
        this.setQrcode(qrcode);
    }

    public WxPublicImg(byte[] headimg, byte[] qrcode) {
        this.setHeadimg(headimg);
        this.setQrcode(qrcode);
    }

    public WxPublicImg() { super(); }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode == null ? null : imgCode.trim();
    }

    public byte[] getHeadimg() {
        return headimg;
    }

    public void setHeadimg(byte[] headimg) {
        this.headimg = headimg;
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }
}