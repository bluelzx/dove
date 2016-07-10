package com.gustz.dove.cli.api.message.rsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 文本响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextMsgRsp extends MsgBaseRsp<TextMsgRsp> {

    private static final long serialVersionUID = 1L;

    /**
     * 回复的消息内容
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Content")
    @JsonProperty("Content")
    private String content;

    public TextMsgRsp() {
        super();
        super.setMsgType(MsgTypeDict.TEXT);
    }

    public TextMsgRsp(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}