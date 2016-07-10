package com.gustz.dove.cli.api.message.rsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.message.vo.Video;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 视频响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoMsgRsp extends MsgBaseRsp<VideoMsgRsp> {

    private static final long serialVersionUID = 1L;

    /**
     * 视频消息的标题
     */
    @XmlElement(name = "Video")
    @JsonProperty("Video")
    private Video video;

    public VideoMsgRsp() {
        super();
        super.setMsgType(MsgTypeDict.VIDEO);
    }

    public VideoMsgRsp(Video video) {
        this();
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
