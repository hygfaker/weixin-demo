package com.minstone.mobile.mp.wechat.publics.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

public class WxPublicFileInfo {
    /**
     * 主键
     */
    private String pubFileCode;
    /**
     * 公众号主键
     */
    private String publicCode;
    /**
     * 文件显示的 url
     */
    private String url;
    /**
     * 文件的类型
     */
    private String suffix;
    /**
     * 二维码还是头像，0表示二维码，1表示头像
     */
    private Integer type;
    /**
     * 文件在服务器硬盘上的相对路径
     */
    private String path;
    /**
     * 文件的名字
     */
    private String name;
    /**
     * md5加密后的签名，判断文件是否重复
     */
    private String signature;
    /**
     * 图片大小
     */
    private Double size;
    /**
     * 删除标志，0表示未删除，1表示删除。默认为0
     */
    private Integer delFlag;
    /**
     * 首次上传图片的同志
     */
    private String creator;
    /**
     * 首次上传图片的时间
     */
    private String createDate;
    /**
     * 后面修改图片的时间
     */
    private String modifyDate;
    /**
     * 后面修改图片的同志
     */
    private String modifyer;

    public String getPubFileCode() {
        return pubFileCode;
    }

    public void setPubFileCode(String pubFileCode) {
        this.pubFileCode = pubFileCode == null ? null : pubFileCode.trim();
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
}