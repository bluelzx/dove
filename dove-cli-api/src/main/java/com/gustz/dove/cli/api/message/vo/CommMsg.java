package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 公用消息类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class CommMsg extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户openId
     */
    @JsonProperty("touser")
    private String toUser;

    /**
     * 消息类型
     */
    @JsonProperty("msgtype")
    private MsgTypeDict msgType;

    public CommMsg() {
        super();
    }

    public CommMsg(String toUser, MsgTypeDict msgType) {
        this();
        this.toUser = toUser;
        this.msgType = msgType;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public MsgTypeDict getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeDict msgType) {
        this.msgType = msgType;
    }

}
