package com.minstone.mobile.mp.wechat.publics.domain;

import com.minstone.mobile.mp.utils.code.IdGen;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class WxPublic {

    public WxPublic() {
        super();
    }

    /**
     * 根据公众号的原始 ID 生成实体
 * @param openId
     * @return
     * @author huangyg
     */
    public WxPublic(String openId){
        this.setOpenId(openId);
    }

    public WxPublic(Map<String,Object> reqMap) {
        this.setPublicCode(IdGen.uuid());
        this.setOpenId((String) reqMap.get("openId"));
        this.setPublicName((String) reqMap.get("publicName"));
        this.setPublicNickname((String) reqMap.get("publicNickname"));
        this.setAppId((String) reqMap.get("appId"));
        this.setAppSerct((String) reqMap.get("appSerct"));
        this.setToken((String) reqMap.get("token"));
        this.setAeskey((String) reqMap.get("aeskey"));
        this.setUrl((String) reqMap.get("url"));
        this.setCreator((String) reqMap.get("creator"));
        this.setCreateDate((String) reqMap.get("createDate"));
        this.setModifyDate((String) reqMap.get("modifyDate"));
        this.setModifyer((String) reqMap.get("modifyer"));
        this.setDelFlag((Integer) reqMap.get("delFlag"));
        this.setSystemCode((String) reqMap.get("systemCode"));
    }

    @NotEmpty(message = "【publicCodes】参数缺失（且内容不为空）")
    private String[] publicCodes;

    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicCode;

    @NotNull
    private String openId;

    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicName;

    @NotEmpty(message = "【publicNickname】参数缺失（且内容不为空）")
    private String publicNickname;

    @NotEmpty(message = "【appId】参数缺失（且内容不为空）")
    private String appId;

    @NotEmpty(message = "【appSerct】参数缺失（且内容不为空）")
    private String appSerct;

    @NotEmpty(message = "【token】参数缺失（且内容不为空）")
    private String token;

    @NotEmpty(message = "【aeskey】参数缺失（且内容不为空）")
    private String aeskey;

    private String url;

    private String creator;

    private String createDate;

    private String modifyDate;

    private String modifyer;

    private Integer delFlag;

    private String systemCode;

    @NotEmpty(message = "【publicNickname】参数缺失（且内容不为空）")
    private String imgCode;

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
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

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode == null ? null : imgCode.trim();
    }

    public String[] getPublicCodes() {
        return publicCodes;
    }

    public void setPublicCodes(String[] publicCodes) {
        this.publicCodes = publicCodes;
    }

    @Override
    public String toString() {
        return "WxPublic{" +
                "publicCodes=" + publicCodes +
                ", publicCode='" + publicCode + '\'' +
                ", openId='" + openId + '\'' +
                ", publicName='" + publicName + '\'' +
                ", publicNickname='" + publicNickname + '\'' +
                ", appId='" + appId + '\'' +
                ", appSerct='" + appSerct + '\'' +
                ", token='" + token + '\'' +
                ", aeskey='" + aeskey + '\'' +
                ", url='" + url + '\'' +
                ", creator='" + creator + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifyer='" + modifyer + '\'' +
                ", delFlag=" + delFlag +
                ", systemCode='" + systemCode + '\'' +
                ", imgCode='" + imgCode + '\'' +
                '}';
    }
}