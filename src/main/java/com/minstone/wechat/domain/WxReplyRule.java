package com.minstone.wechat.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class WxReplyRule {
    public WxReplyRule() {
        super();
    }


    private String ruleCode;

    private String publicCode;

    @NotEmpty(message = "【ruleName】参数缺失（且内容不为空）")
    private String ruleName;

    @NotNull(message = "【useFlag】参数缺失（且内容不为空）")
    private Integer useFlag;

    @NotNull(message = "【kefuReplyFlag】参数缺失（且内容不为空）")
    private Integer kefuReplyFlag;

    @NotEmpty(message = "【content】参数缺失（且内容不为空）")
    private String content;

    private String creator;

    private String createDate;

    private String modifyDate;

    private String modifyer;

    private Integer delFlag;

    @Valid
    @NotEmpty(message = "【keywords】参数内容不能为空")
    @NotNull(message = "【keywords】 参数缺失")
    private List<WxReplyKeyword> keywords;


    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Integer getKefuReplyFlag() {
        return kefuReplyFlag;
    }

    public void setKefuReplyFlag(Integer kefuReplyFlag) {
        this.kefuReplyFlag = kefuReplyFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public List<WxReplyKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<WxReplyKeyword> keywords) {
        this.keywords = keywords;
    }
}