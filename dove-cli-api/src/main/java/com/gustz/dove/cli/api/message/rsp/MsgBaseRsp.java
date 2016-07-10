package com.gustz.dove.cli.api.message.rsp;

import java.io.StringWriter;

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
 * TODO: 响应报文基类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgBaseRsp<T> extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 接收方帐号（收到的OpenID）
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "ToUserName")
    @JsonProperty("ToUserName")
    private String toUserName;

    /**
     * 开发者微信号
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "FromUserName")
    @JsonProperty("FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    @JsonProperty("CreateTime")
    private long createTime;

    /**
     * 消息类型
     */
    @XmlElement(name = "MsgType")
    @JsonProperty("MsgType")
    private MsgTypeDict msgType;

    /**
     * 位0x0001被标志时，星标刚收到的消息
     */
    @XmlElement(name = "FuncFlag")
    @JsonProperty("FuncFlag")
    private int funcFlag;

    public MsgBaseRsp() {
        super();
    }

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

    public int getFuncFlag() {
        return funcFlag;
    }

    public void setFuncFlag(int funcFlag) {
        this.funcFlag = funcFlag;
    }

    public static String toXml(Object bean) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext cxt = JAXBContext.newInstance(bean.getClass());
        cxt.createMarshaller().marshal(bean, writer);
        // 
        return writer.toString();
    }

}