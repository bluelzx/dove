package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * TODO: 多媒体请求报文：图片消息、语音消息。
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaMsgReq<T> extends MsgBaseReq<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 多媒体文件ID：图片消息、语音消息
     */
    @JsonProperty("MediaId")
    @XmlElement(name = "MediaId")
    private String mediaId;

    public MediaMsgReq() {
        super();
    }

    public MediaMsgReq(String mediaId) {
        this();
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
