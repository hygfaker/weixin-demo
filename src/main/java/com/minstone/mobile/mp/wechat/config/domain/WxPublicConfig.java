package com.minstone.mobile.mp.wechat.config.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;

/**
 * 描述:MP_YY_PUB_FLAG表的实体类
 * @version
 * @author:  huangyg
 * @创建时间: 2017-11-24
 */
public class WxPublicConfig {

    public WxPublicConfig(String configCode, String publicCode, Integer menuUseFlag, Integer kefuUseFlag, Integer pushUseFlag, String kefuOfflineMessage, String kefuOnlineMessage) {
        this.configCode = configCode;
        this.publicCode = publicCode;
        this.menuUseFlag = menuUseFlag;
        this.kefuUseFlag = kefuUseFlag;
        this.pushUseFlag = pushUseFlag;
        this.kefuOfflineMessage = kefuOfflineMessage;
        this.kefuOnlineMessage = kefuOnlineMessage;
    }

    /**
     * 主键
     */

    private String configCode;

    /**
     * 公众号主键
     */
    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicCode;

    /**
     * 是否开启自定义菜单。0为关闭，1为开启。
     */
    private Integer menuUseFlag;

    /**
     * 消息管理中是否开启客服服务。0为关闭，1为开启。
     */
    private Integer kefuUseFlag;

    /**
     * 是否开启定点推送。0为关闭，1为打开。
     */
    private Integer pushUseFlag;

    /**
     * 客服不在线的时候的提示内容
     */
    private String kefuOfflineMessage;

    /**
     * 客服在线等时候的提示内容
     */
    private String kefuOnlineMessage;

    /**
     * 主键
     * @return CONFIG_CODE 主键
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 主键
     * @param configCode 主键
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * 公众号主键
     * @return PUBLIC_CODE 公众号主键
     */
    public String getPublicCode() {
        return publicCode;
    }

    /**
     * 公众号主键
     * @param publicCode 公众号主键
     */
    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    /**
     * 是否开启自定义菜单。0为关闭，1为开启。
     * @return MENU_USE_FLAG 是否开启自定义菜单。0为关闭，1为开启。
     */
    public Integer getMenuUseFlag() {
        return menuUseFlag;
    }

    /**
     * 是否开启自定义菜单。0为关闭，1为开启。
     * @param menuUseFlag 是否开启自定义菜单。0为关闭，1为开启。
     */
    public void setMenuUseFlag(Integer menuUseFlag) {
        this.menuUseFlag = menuUseFlag;
    }

    /**
     * 消息管理中是否开启客服服务。0为关闭，1为开启。
     * @return KEFU_USE_FLAG 消息管理中是否开启客服服务。0为关闭，1为开启。
     */
    public Integer getKefuUseFlag() {
        return kefuUseFlag;
    }

    /**
     * 消息管理中是否开启客服服务。0为关闭，1为开启。
     * @param kefuUseFlag 消息管理中是否开启客服服务。0为关闭，1为开启。
     */
    public void setKefuUseFlag(Integer kefuUseFlag) {
        this.kefuUseFlag = kefuUseFlag;
    }

    /**
     * 是否开启定点推送。0为关闭，1为打开。
     * @return PUSH_USE_FLAG 是否开启定点推送。0为关闭，1为打开。
     */
    public Integer getPushUseFlag() {
        return pushUseFlag;
    }

    /**
     * 是否开启定点推送。0为关闭，1为打开。
     * @param pushUseFlag 是否开启定点推送。0为关闭，1为打开。
     */
    public void setPushUseFlag(Integer pushUseFlag) {
        this.pushUseFlag = pushUseFlag;
    }

    /**
     * 客服不在线的时候的提示内容
     * @return KEFU_OFFLINE_MESSAGE 客服不在线的时候的提示内容
     */
    public String getKefuOfflineMessage() {
        return kefuOfflineMessage;
    }

    /**
     * 客服不在线的时候的提示内容
     * @param kefuOfflineMessage 客服不在线的时候的提示内容
     */
    public void setKefuOfflineMessage(String kefuOfflineMessage) {
        this.kefuOfflineMessage = kefuOfflineMessage == null ? null : kefuOfflineMessage.trim();
    }

    /**
     * 客服在线等时候的提示内容
     * @return KEFU_ONLINE_MESSAGE 客服在线等时候的提示内容
     */
    public String getKefuOnlineMessage() {
        return kefuOnlineMessage;
    }

    /**
     * 客服在线等时候的提示内容
     * @param kefuOnlineMessage 客服在线等时候的提示内容
     */
    public void setKefuOnlineMessage(String kefuOnlineMessage) {
        this.kefuOnlineMessage = kefuOnlineMessage == null ? null : kefuOnlineMessage.trim();
    }
}