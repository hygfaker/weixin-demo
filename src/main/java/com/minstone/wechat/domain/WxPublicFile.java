package com.minstone.wechat.domain;

public class WxPublicFile {



    private byte[] headimg;

    private byte[] qecode;

    private String fileCode;

    private String publicCode;


    public WxPublicFile() {}

    public WxPublicFile(byte[] headimg, byte[] qrcode,String publicCode) {
        this.setHeadimg(headimg);
        this.setQecode(qrcode);
        this.setPublicCode(publicCode);
    }


    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;
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