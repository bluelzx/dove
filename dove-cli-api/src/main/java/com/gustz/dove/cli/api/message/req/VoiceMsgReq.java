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
 * TODO: 语音请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoiceMsgReq extends MediaMsgReq<VoiceMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 语音格式，如amr，speex等
     */
    @JsonProperty("Format")
    @XmlElement(name = "Format")
    private String format;

    public VoiceMsgReq() {
        super.setMsgType(MsgTypeDict.VOICE);
    }

    public VoiceMsgReq(String format) {
        this();
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public static VoiceMsgReq toBean(String xml) throws JAXBException {
        return new VoiceMsgReq().toBean(xml, VoiceMsgReq.class);
    }

}
