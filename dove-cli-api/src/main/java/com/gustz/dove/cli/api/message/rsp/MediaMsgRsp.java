package com.gustz.dove.cli.api.message.rsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;

/**
 * 
 * TODO: 多媒体响应报文：图片消息、语音消息。
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaMsgRsp<T> extends MsgBaseRsp<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 通过素材管理接口上传多媒体文件，得到的id
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "MediaId")
    @JsonProperty("MediaId")
    private String mediaId;

    public MediaMsgRsp() {
        super();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
