package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;

/**
 * 
 * TODO: 视频消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Video extends Media {

    private static final long serialVersionUID = 1L;

    /**
     * 视频消息的标题
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Title")
    @JsonProperty("Title")
    private String title;

    /**
     * 视频消息的描述
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Description")
    @JsonProperty("Description")
    private String desc;

    public Video() {
        super();
    }

    public Video(String title, String desc, String mediaId) {
        this();
        super.setMediaId(mediaId);
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
