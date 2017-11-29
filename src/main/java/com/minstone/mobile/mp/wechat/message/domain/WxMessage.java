package com.minstone.mobile.mp.wechat.message.domain;

import com.minstone.mobile.mp.utils.DateUtil;
import com.minstone.mobile.mp.utils.code.IdGen;
import lombok.Data;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述:MP_YY_MSG表的实体类
 * @version
 * @author:  huangyg
 * @创建时间: 2017-11-28
 */
@Data
public class WxMessage {

    public WxMessage(){

    }
    public WxMessage(String publicCode) {
        this.publicCode = publicCode;
    }
    public WxMessage(String msgCode, String publicCode, String userCode, String msgId, String createDate, String msgType, String content, String mediaId, String url, String format, String recognition, String thumbMediaId, Double locationX, Double locationY, Double locationScale, String description, Integer delFlag, Integer msgFlag, String replyContent) {
        this.msgCode = msgCode;
        this.publicCode = publicCode;
        this.userCode = userCode;
        this.msgId = msgId;
        this.createDate = createDate;
        this.msgType = msgType;
        this.content = content;
        this.mediaId = mediaId;
        this.url = url;
        this.format = format;
        this.recognition = recognition;
        this.thumbMediaId = thumbMediaId;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationScale = locationScale;
        this.description = description;
        this.delFlag = delFlag;
        this.msgFlag = msgFlag;
        this.replyContent = replyContent;
    }

    public WxMessage(String publicCode,WxMpXmlMessage wxMessage) {
        this.publicCode = publicCode;
        this.msgCode = IdGen.uuid();
        this.userCode = wxMessage.getFromUser();
        this.createDate = DateUtil.unix2Date(wxMessage.getCreateTime().toString(), DateUtil.DatePattern.DATE_TIME) ;
        this.msgType = wxMessage.getMsgType();
        if (wxMessage.getContent()!=null){
            this.content = content;
        }
        this.msgId = wxMessage.getMsgId().toString();

        // 如果有 content，则是文本消息
        this.content = wxMessage.getContent()!=null ? wxMessage.getContent() : null;
        // 【链接消息】 中的【消息标题】
        if (wxMessage.getTitle()!=null){
            this.content = wxMessage.getTitle();
        }
        // 【地理位置消息】 中的【地理位置信息】
        if (wxMessage.getLabel()!=null){
            this.content = wxMessage.getLabel();
        }
        this.url = wxMessage.getPicUrl();
        this.mediaId = wxMessage.getMediaId();
        this.format = wxMessage.getFormat();
        this.recognition = wxMessage.getRecognition();
        this.thumbMediaId = wxMessage.getThumbMediaId();
        this.locationX = wxMessage.getLocationX();
        this.locationY = wxMessage.getLocationY();
        this.locationScale = wxMessage.getScale();
        this.description = wxMessage.getDescription();
        // 默认为0，不删除
        this.delFlag = 0;
    }

    /**
     * 时间限制，默认为5天内
     */
    private Integer dayLimit;

    /**
     * 主键
     */
    @NotEmpty(message = "【msgCode】参数缺失（且内容不为空）")
    private String msgCode;

    /**
     * 公众号主键（统一）
     */
    @NotEmpty(message = "【publicCode】参数缺失（且内容不为空）")
    private String publicCode;

    /**
     * 消息来源（统一）
     */
    private String userCode;

    /**
     * 消息id，64位整型（统一）
     */
    private String msgId;

    /**
     * 消息创建时间（统一）
     */
    private String createDate;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 文本消息时为【内容】、链接消息是为【消息标题】、地理位置消息时为【地理位置信息】
     */
    @NotEmpty(message = "【content】参数缺失（且内容不为空）")
    private String content;

    /**
     * 图片消息、语音消息、视频消息、可以调用多媒体文件下载接口拉取数据。 
     */
    private String mediaId;

    /**
     * 图片消息时为【图片链接】、链接消息时为【消息链接】
     */
    private String url;

    /**
     * 语音格式，如amr，等
     */
    private String format;

    /**
     * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段，UTF8编码
     */
    private String recognition;

    /**
     * 视频、小视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;

    /**
     * 地理位置消息中的【地理位置维度】
     */
    private Double locationX;

    /**
     * 地理位置消息中的【地理位置经度】
     */
    private Double locationY;

    /**
     * 地理位置消息中的【地图缩放大小】
     */
    private Double locationScale;

    /**
     * 链接消息中的【消息描述】
     */
    private String description;

    /**
     * 是否删除。0为没删除，1为已删除、
     */
    private Integer delFlag;

    /**
     * 消息回复状态，0表示未回复状态，1表示回复状态
     */
    private Integer msgFlag;

    /**
     * 回复内容 
     */
    private String replyContent;



    /**
     * 主键
     * @return MSG_CODE 主键
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * 主键
     * @param msgCode 主键
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    /**
     * 公众号主键（统一）
     * @return PUBLIC_CODE 公众号主键（统一）
     */
    public String getPublicCode() {
        return publicCode;
    }

    /**
     * 公众号主键（统一）
     * @param publicCode 公众号主键（统一）
     */
    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    /**
     * 消息来源（统一）
     * @return USER_CODE 消息来源（统一）
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 消息来源（统一）
     * @param userCode 消息来源（统一）
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 消息id，64位整型（统一）
     * @return MSG_ID 消息id，64位整型（统一）
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 消息id，64位整型（统一）
     * @param msgId 消息id，64位整型（统一）
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    /**
     * 消息创建时间（统一）
     * @return CREATE_DATE 消息创建时间（统一）
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 消息创建时间（统一）
     * @param createDate 消息创建时间（统一）
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * 消息类型
     * @return MSG_TYPE 消息类型
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 消息类型
     * @param msgType 消息类型
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 文本消息时为【内容】、链接消息是为【消息标题】、地理位置消息时为【地理位置信息】
     * @return CONTENT 文本消息时为【内容】、链接消息是为【消息标题】、地理位置消息时为【地理位置信息】
     */
    public String getContent() {
        return content;
    }

    /**
     * 文本消息时为【内容】、链接消息是为【消息标题】、地理位置消息时为【地理位置信息】
     * @param content 文本消息时为【内容】、链接消息是为【消息标题】、地理位置消息时为【地理位置信息】
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 图片消息、语音消息、视频消息、可以调用多媒体文件下载接口拉取数据。 
     * @return MEDIA_ID 图片消息、语音消息、视频消息、可以调用多媒体文件下载接口拉取数据。 
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 图片消息、语音消息、视频消息、可以调用多媒体文件下载接口拉取数据。 
     * @param mediaId 图片消息、语音消息、视频消息、可以调用多媒体文件下载接口拉取数据。 
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     * 图片消息时为【图片链接】、链接消息时为【消息链接】
     * @return URL 图片消息时为【图片链接】、链接消息时为【消息链接】
     */
    public String getUrl() {
        return url;
    }

    /**
     * 图片消息时为【图片链接】、链接消息时为【消息链接】
     * @param url 图片消息时为【图片链接】、链接消息时为【消息链接】
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 语音格式，如amr，等
     * @return FORMAT 语音格式，如amr，等
     */
    public String getFormat() {
        return format;
    }

    /**
     * 语音格式，如amr，等
     * @param format 语音格式，如amr，等
     */
    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    /**
     * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段，UTF8编码
     * @return RECOGNITION 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段，UTF8编码
     */
    public String getRecognition() {
        return recognition;
    }

    /**
     * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段，UTF8编码
     * @param recognition 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段，UTF8编码
     */
    public void setRecognition(String recognition) {
        this.recognition = recognition == null ? null : recognition.trim();
    }

    /**
     * 视频、小视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     * @return THUMB_MEDIA_ID 视频、小视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 视频、小视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     * @param thumbMediaId 视频、小视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
    }

    /**
     * 地理位置消息中的【地理位置维度】
     * @return LOCATION_X 地理位置消息中的【地理位置维度】
     */
    public Double getLocationX() {
        return locationX;
    }

    /**
     * 地理位置消息中的【地理位置维度】
     * @param locationX 地理位置消息中的【地理位置维度】
     */
    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }

    /**
     * 地理位置消息中的【地理位置经度】
     * @return LOCATION_Y 地理位置消息中的【地理位置经度】
     */
    public Double getLocationY() {
        return locationY;
    }

    /**
     * 地理位置消息中的【地理位置经度】
     * @param locationY 地理位置消息中的【地理位置经度】
     */
    public void setLocationY(Double locationY) {
        this.locationY = locationY;
    }

    /**
     * 地理位置消息中的【地图缩放大小】
     * @return LOCATION_SCALE 地理位置消息中的【地图缩放大小】
     */
    public Double getLocationScale() {
        return locationScale;
    }

    /**
     * 地理位置消息中的【地图缩放大小】
     * @param locationScale 地理位置消息中的【地图缩放大小】
     */
    public void setLocationScale(Double locationScale) {
        this.locationScale = locationScale;
    }

    /**
     * 链接消息中的【消息描述】
     * @return DESCRIPTION 链接消息中的【消息描述】
     */
    public String getDescription() {
        return description;
    }

    /**
     * 链接消息中的【消息描述】
     * @param description 链接消息中的【消息描述】
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 是否删除。0为没删除，1为已删除、
     * @return DEL_FLAG 是否删除。0为没删除，1为已删除、
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 是否删除。0为没删除，1为已删除、
     * @param delFlag 是否删除。0为没删除，1为已删除、
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 消息回复状态，0表示未回复状态，1表示回复状态
     * @return MSG_FLAG 消息回复状态，0表示未回复状态，1表示回复状态
     */
    public Integer getMsgFlag() {
        return msgFlag;
    }

    /**
     * 消息回复状态，0表示未回复状态，1表示回复状态
     * @param msgFlag 消息回复状态，0表示未回复状态，1表示回复状态
     */
    public void setMsgFlag(Integer msgFlag) {
        this.msgFlag = msgFlag;
    }

    /**
     * 回复内容 
     * @return REPLY_CONTENT 回复内容 
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 回复内容 
     * @param replyContent 回复内容 
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
}