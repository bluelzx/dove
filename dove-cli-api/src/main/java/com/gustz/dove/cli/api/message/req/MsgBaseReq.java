package com.gustz.dove.cli.api.message.req;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 请求报文基类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgBaseReq<T> extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 消息类型MsgType XPath
     */
    public static final String MSG_TYPE_XPATH = "/xml/MsgType";

    /**
     * 事件类型Event XPath
     */
    public static final String EVENT_TYPE_XPATH = "/xml/Event";

    /**
     * 开发者微信号ToUserName XPath 
     */
    public static final String TO_USER_NAME_XPATH = "/xml/ToUserName";

    /**
     * 来源微信号FromUserName XPath
     */
    public static final String FROM_USER_NAME_XPATH = "/xml/FromUserName";

    /**
     * 消息id 64位整型MsgId XPath
     */
    public static final String MSG_ID_XPATH = "/xml/MsgId";

    /**
     * 加密串Encrypt XPath
     */
    public static final String ENCRYPT_XPATH = "/xml/Encrypt";

    /**
     * 代理应用ID AgentID XPath
     */
    public static final String AGENT_ID_XPATH = "/xml/AgentID";

    /**
     * 开发者微信号
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @JsonProperty("ToUserName")
    @XmlElement(name = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @JsonProperty("FromUserName")
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @JsonProperty("CreateTime")
    @XmlElement(name = "CreateTime")
    private long createTime;

    /**
     * 消息类型 text、image、location、link
     */
    @JsonProperty("MsgType")
    @XmlElement(name = "MsgType")
    private MsgTypeDict msgType;

    /**
     * 消息id，64位整型
     */
    @JsonProperty("MsgId")
    @XmlElement(name = "MsgId")
    private long msgId;

    /**
     * 企业应用的id，整型。可在应用的设置页面查看 
     */
    @JsonProperty("AgentID")
    @XmlElement(name = "AgentID")
    private int agentId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public MsgTypeDict getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeDict msgType) {
        this.msgType = msgType;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @SuppressWarnings("unchecked")
    public T toBean(String xml, Class<T> beanType) throws JAXBException {
        JAXBContext cxt = JAXBContext.newInstance(beanType);
        //
        return (T) cxt.createUnmarshaller().unmarshal(new StringReader(xml));
    }

}
