package com.gustz.dove.cli.api.message.rsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.message.vo.Voice;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 语音响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoiceMsgRsp extends MediaMsgRsp<VoiceMsgRsp> {

    private static final long serialVersionUID = 1L;

    /**
     * 语音
     */
    @XmlElement(name = "Voice")
    @JsonProperty("Voice")
    private Voice voice;

    public VoiceMsgRsp() {
        super();
        super.setMsgType(MsgTypeDict.VOICE);
    }

    public VoiceMsgRsp(Voice voice) {
        this();
        this.voice = voice;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}