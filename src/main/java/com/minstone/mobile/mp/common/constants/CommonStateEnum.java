package com.minstone.mobile.mp.common.constants;

/**
 * @author huangyg
 * @description 逻辑删除枚举状态
 * @since 2017/11/2
 */
public enum CommonStateEnum {

    HAS_DELETE(0,"删除状态"),
    NOT_DELETE(1,"正常状态");

    private Integer state;
    private String msg;

    public Integer getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    CommonStateEnum(Integer state, String msg) {
        this.msg = msg;
        this.state = state;
    }


}
