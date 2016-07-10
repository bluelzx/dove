package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.EventTypeDict;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;

/**
 * 
 * TODO: 事件消息请求报文：菜单点击、拉取，关注和取消关注等。
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ] 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventMsgReq<T> extends MsgBaseReq<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 事件消息类型
     */
    @JsonProperty("Event")
    @XmlElement(name = "Event")
    private EventTypeDict event;

    /**
     * 事件消息Key值
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @JsonProperty("EventKey")
    @XmlElement(name = "EventKey")
    private String eventKey;

    public EventMsgReq() {
        super();
        super.setMsgType(MsgTypeDict.EVENT);
    }

    public EventMsgReq(EventTypeDict event) {
        this();
        this.event = event;
    }

    public EventMsgReq(EventTypeDict event, String eventKey) {
        this(event);
        this.eventKey = eventKey;
    }

    public EventTypeDict getEvent() {
        return event;
    }

    public void setEvent(EventTypeDict event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}
