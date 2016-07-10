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
 * TODO: 链接请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class LinkMsgReq extends MsgBaseReq<LinkMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @JsonProperty("Title")
    @XmlElement(name = "Title")
    private String title;

    /**
     * 描述
     */
    @JsonProperty("Description")
    @XmlElement(name = "Description")
    private String desc;

    /**
     * 链接
     */
    @JsonProperty("Url")
    @XmlElement(name = "Url")
    private String url;

    public LinkMsgReq() {
        super.setMsgType(MsgTypeDict.LINK);
    }

    public LinkMsgReq(String title, String desc, String url) {
        this();
        this.title = title;
        this.desc = desc;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static LinkMsgReq toBean(String xml) throws JAXBException {
        return new LinkMsgReq().toBean(xml, LinkMsgReq.class);
    }

}
