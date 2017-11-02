package com.minstone.mobile.mp.common;

/**
 * @author huangyg
 * @description 逻辑删除枚举状态
 * @since 2017/11/2
 */
public enum DeleteFlagEnum {


    /**
     * 0 表示已经被删除
     */
    HAS_DELETE(0),
    /**
     * 1 表示没有被删除
     */
    NOT_DELETE(1);

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    private Integer delFlag;


    DeleteFlagEnum(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
