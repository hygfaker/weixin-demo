package com.minstone.mobile.mp.wechat.material.domain;

import lombok.Data;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

/**
 * @author huangyg
 * @description
 * @since 2018/2/26
 */
@Data
public class WxMpCustomeMaterialNews extends WxMpMaterialNews {
    private String publicCode;

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;
    }


}
