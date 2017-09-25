package com.minstone.wechat.domain;

public class WxReplyRule {
    private String ruleCode;

    private String trplyCode;

    private String ruleName;

    private Integer useFlag;

    private Integer kefuReplyFlag;

    private String content;

    private String creator;

    private String createDate;

    private String modifyDate;

    private String modifyer;

    private Integer delFlag;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getTrplyCode() {
        return trplyCode;
    }

    public void setTrplyCode(String trplyCode) {
        this.trplyCode = trplyCode == null ? null : trplyCode.trim();
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
}