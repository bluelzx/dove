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
 * TODO: 图片请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageMsgReq extends MediaMsgReq<ImageMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 图片链接
     */
    @JsonProperty("PicUrl")
    @XmlElement(name = "PicUrl")
    private String picUrl;

    public ImageMsgReq() {
        super.setMsgType(MsgTypeDict.IMAGE);
    }

    public ImageMsgReq(String picUrl) {
        this();
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public static ImageMsgReq toBean(String xml) throws JAXBException {
        return new ImageMsgReq().toBean(xml, ImageMsgReq.class);
    }

}
