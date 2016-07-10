package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 多媒体类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Media extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 媒体ID
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "MediaId")
    @JsonProperty("MediaId")
    private String mediaId;

    public Media() {
        super();
    }

    public Media(String mediaId) {
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
