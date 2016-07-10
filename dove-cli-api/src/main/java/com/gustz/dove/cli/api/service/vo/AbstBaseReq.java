package com.gustz.dove.cli.api.service.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * TODO: 请求基类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@JsonRootName("REQUEST")
@XmlRootElement(name = "REQUEST")
public abstract class AbstBaseReq<T> extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * body主体内容
     */
    public static final String BODY_FIELD = "body";

    /**
     * cliIpAddrs客户端IP地址
     */
    public static final String CLI_IP_ADDRS_FIELD = "cliIpAddrs";

    /**
     * cliAppPwd客户端密码
     */
    public static final String CLI_APP_PWD_FIELD = "cliAppPwd";

    /**
     * devAcCode开发者账号
     */
    public static final String DEV_AC_CODE_FIELD = "devAcCode";

    /**
     * 客户端IP地址
     */
    @JsonProperty("CLI_IP_ADDRS")
    private String[] cliIpAddrs;

    /**
     * 客户端密码
     */
    @JsonProperty("CLI_APP_PWD")
    private String cliAppPwd;

    /**
     * 开发者账号（微信原始ID）
     */
    @JsonProperty("DEV_AC_CODE")
    private String devAcCode;

    /**
     * 主体内容
     */
    @JsonProperty("BODY")
    private T body;

    protected AbstBaseReq(String devAcCode, T body) {
        this.devAcCode = devAcCode;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getDevAcCode() {
        return devAcCode;
    }

    public void setDevAcCode(String devAcCode) {
        this.devAcCode = devAcCode;
    }

    public String[] getCliIpAddrs() {
        return cliIpAddrs;
    }

    public void setCliIpAddrs(String[] cliIpAddrs) {
        this.cliIpAddrs = cliIpAddrs;
    }

    public String getCliAppPwd() {
        return cliAppPwd;
    }

    public void setCliAppPwd(String cliAppPwd) {
        this.cliAppPwd = cliAppPwd;
    }

}
