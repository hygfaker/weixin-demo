package com.minstone.mobile.mp.wechat.material.domain;

import lombok.Data;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

/**
 * @author huangyg
 * @description
 * @since 2018/2/26
 */
public class WxMpCustomeMaterialNews extends WxMpMaterialNews {

    private static final long serialVersionUID = 1L;

    private String publicCode;

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;
    }


}
