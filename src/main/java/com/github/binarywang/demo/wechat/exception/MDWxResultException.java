package com.github.binarywang.demo.wechat.exception;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * Created by huangyg on 2017/8/7.
 */
public class MDWxResultException extends WxErrorException {
    public MDWxResultException(WxError error) {
        super(error);
    }

    public MDWxResultException(WxError error, Throwable cause) {
        super(error, cause);
    }


}
