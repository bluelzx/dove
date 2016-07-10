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
 * TODO: 文本请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextMsgReq extends MsgBaseReq<TextMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息内容
     */
    @JsonProperty("Content")
    @XmlElement(name = "Content")
    private String content;

    public TextMsgReq() {
        super.setMsgType(MsgTypeDict.TEXT);
    }

    public TextMsgReq(String content) {
        this();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static TextMsgReq toBean(String xml) throws JAXBException {
        return new TextMsgReq().toBean(xml, TextMsgReq.class);
    }

}