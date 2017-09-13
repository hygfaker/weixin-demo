package com.minstone.wechat.domain;

public class WxPublicFileWithBLOBs extends WxPublicFile {
    private byte[] headimg;

    private byte[] qecode;

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