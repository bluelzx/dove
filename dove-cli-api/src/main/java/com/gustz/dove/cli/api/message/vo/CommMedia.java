package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * TODO: 公用媒体类消息：图片/语音消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CommMedia extends CommMsg {

    private static final long serialVersionUID = 1L;

    /**
     * 媒体对象
     */
    @XmlElement(name = "media")
    @JsonProperty("media")
    private Media media;

    public CommMedia() {
        super();
    }

    public CommMedia(Media media) {
        super();
        this.media = media;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

}
