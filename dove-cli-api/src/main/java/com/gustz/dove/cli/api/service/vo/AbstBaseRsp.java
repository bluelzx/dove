package com.gustz.dove.cli.api.service.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * TODO: 响应基类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@JsonRootName("RESPONSE")
@XmlRootElement(name = "RESPONSE")
public abstract class AbstBaseRsp<T> extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 响应编码rspCode
     */
    public static final String RSP_CODE_FIELD = "rspCode";

    /**
     * 响应消息rspMsg
     */
    public static final String RSP_MSG_FIELD = "rspMsg";

    /**
     * 序号sn
     */
    public static final String SN_FIELD = "sn";

    /**
     * WEB服务编码websCode（响应的服务编码-单个）
     */
    public static final String WEBS_CODE_FIELD = "websCode";

    /** 
     * 序号
     */
    @JsonProperty("SN")
    private long sn;

    /**
     * WEB服务编码（响应的服务编码-单个）
     */
    @JsonProperty("WEBS_CODE")
    private String websCode;

    /**
     * 响应编码
     */
    @JsonProperty("RSP_CODE")
    private String rspCode;

    /**
     * 响应消息
     */
    @JsonProperty("RSP_MSG")
    private String rspMsg;

    /**
     * 主体内容
     */
    @JsonProperty("BODY")
    private T body;

    protected AbstBaseRsp() {
        super();
    }

    protected AbstBaseRsp(T body) {
        this();
        this.body = body;
    }

    protected AbstBaseRsp(long sn, String websCode, String rspCode, T body) {
        this();
        this.sn = sn;
        this.websCode = websCode;
        this.rspCode = rspCode;
        this.body = body;
    }

    public String getWebsCode() {
        return websCode;
    }

    public void setWebsCode(String websCode) {
        this.websCode = websCode;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

}