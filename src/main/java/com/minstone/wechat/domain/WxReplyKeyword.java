package com.minstone.wechat.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class WxReplyKeyword {

    public WxReplyKeyword() {
        super();
    }

    private String keywordCode;

    private String ruleCode;

    @NotEmpty(message = "【keyword】参数缺失")
    private String keyword;

    @NotNull(message = "【matchFlag】参数缺失")
    private Integer matchFlag;

    private Integer delFlag;

    public String getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(String keywordCode) {
        this.keywordCode = keywordCode == null ? null : keywordCode.trim();
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getMatchFlag() {
        return matchFlag;
    }

    public void setMatchFlag(Integer matchFlag) {
        this.matchFlag = matchFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}