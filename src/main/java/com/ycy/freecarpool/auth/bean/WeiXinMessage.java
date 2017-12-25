package com.ycy.freecarpool.auth.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * Created by ycy on 2017/12/20.
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinMessage implements Serializable {
    @XmlElement
    private String ToUserName;//开发者微信号
    @XmlElement
    private String FromUserName;//发送方微信号（openID）
    @XmlElement
    private String CreateTime;//创建时间
    @XmlElement
    private String MsgType;//消息类型 文本-text，图片-image 语音-voice 视频-video
    @XmlElement
    private String MediaID;//
    @XmlElement
    private String Event;
    @XmlElement
    private String EventKey;
    @XmlElement
    private String Format;//
    @XmlElement
    private String Content;// 消息内容
    @XmlElement
    private String MsgId;//消息id 64位整型

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMediaID() {
        return MediaID;
    }

    public void setMediaID(String mediaID) {
        MediaID = mediaID;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}


