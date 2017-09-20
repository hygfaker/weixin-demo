package com.minstone.wechat.domain;

import com.minstone.wechat.utils.code.IdGen;

public class WxPublicFileWithBLOBs extends WxPublicFile {

    public WxPublicFileWithBLOBs() {
    }

    private byte[] headimg;

    private byte[] qecode;

    public WxPublicFileWithBLOBs(byte[] headimg, byte[] qrcode,String publicCode) {
        this.setHeadimg(headimg);
        this.setQecode(qrcode);
        this.setPublicCode(publicCode);
    }

    public byte[] getHeadimg() {
        return headimg;
    }

    public void setHeadimg(byte[] headimg) {
        this.headimg = headimg;
    }

    public byte[] getQecode() {
        return qecode;
    }

    public void setQecode(byte[] qecode) {
        this.qecode = qecode;
    }
}