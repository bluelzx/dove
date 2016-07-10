package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 视频请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoMsgReq extends MediaMsgReq<VideoMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 视频消息缩略图的媒体id，调用多媒体文件下载接口拉取数据。
     */
    @JsonProperty("ThumbMediaId")
    @XmlElement(name = "ThumbMediaId")
    private String thumbMediaId;

    public VideoMsgReq() {
        super.setMsgType(MsgTypeDict.VIDEO);
    }

    public VideoMsgReq(String thumbMediaId) {
        this();
        this.thumbMediaId = thumbMediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public static VideoMsgReq toBean(String xml) throws JAXBException {
        return new VideoMsgReq().toBean(xml, VideoMsgReq.class);
    }

}
