package com.gustz.dove.mpcli.api.hwdevice.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 硬件设备信息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class Hwdevice extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 设备的deviceid
     */
    @JsonProperty("id")
    private String id;

    /**
     * 设备的mac地址 格式采用16进制串的方式（长度为12字节），<br/>
     * 不需要0X前缀，如： 1234567890AB 
     */
    @JsonProperty("mac")
    private String mac;

    /**
     * 支持以下四种连接协议： <br/>
     * android classic bluetooth – 1 <br/>
     * ios classic bluetooth – 2 <br/>
     * ble – 3 <br/>
     * wifi -- 4  <br/>
     */
    @JsonProperty("connect_protocol")
    private String connProtocol;

    /**
     * auth及通信的加密key，第三方需要将key烧制在设备上（128bit），<br/>
     * 格式采用16进制串的方式（长度为32字节），不需要0X前缀。
     */
    @JsonProperty("auth_key")
    private String authKey;

    /**
     * 断开策略，目前支持： <br/>
     * 1：退出公众号页面时即断开连接  <br/>
     * 2：退出公众号之后保持连接不断开  <br/>
     * 3：退出公众号之后一直保持连接（设备主动断开连接后，微信尝试重连） <br/>
     */
    @JsonProperty("close_strategy")
    private String closeStrategy;

    /**
     * 连接策略，32位整型，按bit位置位，<br/>
     * 目前仅第1bit和第3bit位有效（bit置0为无效，1为有效；第2bit已被废弃），<br/>
     * 且bit位可以按或置位（如1|4=5），各bit置位含义说明如下： <br/>
     * 1：（第1bit置位）在公众号对话页面，不停的尝试连接设备 <br/>
     * 4：（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。<br/>
     * 当用户切换微信到前台时，可能尝试去连接设备，连上后一定时间会断开 <br/>
     * 8：（第4bit置位），进入微信后即刻开始连接。只要微信进程在运行就不会主动断开  <br/>
     */
    @JsonProperty("conn_strategy")
    private String connStrategy;

    /**
     * auth加密方法，目前支持两种取值： 0：不加密  <br/>
     * 1：AES加密（CBC模式，PKCS7填充方式）  <br/>
     */
    @JsonProperty("crypt_method")
    private String cryptMethod;

    /**
     * 该字段目前支持取值： 0：不加密的version 1：version 1 
     */
    @JsonProperty("auth_ver")
    private String authVer;

    /**
     * 表示mac地址在厂商广播manufature data里含有mac地址的偏移 <br/>
     * -1：在尾部、 -2：表示不包含mac地址 其他：非法偏移 <br/>
     */
    @JsonProperty("manu_mac_pos")
    private String manuMacPos;

    /**
     * 表示mac地址在厂商serial number里含有mac地址的偏移 <br/>
     * -1：表示在尾部 -2：表示不包含mac地址 其他：非法偏移 <br/>
     */
    @JsonProperty("ser_mac_pos")
    private String serMacPos;

    public Hwdevice() {
        super();
    }

    /**
     * 
     * @param id
     * @param mac
     * @param connProtocol
     * @param authKey
     * @param closeStrategy
     * @param connStrategy
     * @param cryptMethod
     * @param authVer
     * @param manuMacPos
     * @param serMacPos
     */
    public Hwdevice(String id, String mac, String connProtocol, String authKey, String closeStrategy, String connStrategy,
            String cryptMethod, String authVer, String manuMacPos, String serMacPos) {
        super();
        this.id = id;
        this.mac = mac;
        this.connProtocol = connProtocol;
        this.authKey = authKey;
        this.closeStrategy = closeStrategy;
        this.connStrategy = connStrategy;
        this.cryptMethod = cryptMethod;
        this.authVer = authVer;
        this.manuMacPos = manuMacPos;
        this.serMacPos = serMacPos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getConnProtocol() {
        return connProtocol;
    }

    public void setConnProtocol(String connProtocol) {
        this.connProtocol = connProtocol;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getCloseStrategy() {
        return closeStrategy;
    }

    public void setCloseStrategy(String closeStrategy) {
        this.closeStrategy = closeStrategy;
    }

    public String getConnStrategy() {
        return connStrategy;
    }

    public void setConnStrategy(String connStrategy) {
        this.connStrategy = connStrategy;
    }

    public String getCryptMethod() {
        return cryptMethod;
    }

    public void setCryptMethod(String cryptMethod) {
        this.cryptMethod = cryptMethod;
    }

    public String getAuthVer() {
        return authVer;
    }

    public void setAuthVer(String authVer) {
        this.authVer = authVer;
    }

    public String getManuMacPos() {
        return manuMacPos;
    }

    public void setManuMacPos(String manuMacPos) {
        this.manuMacPos = manuMacPos;
    }

    public String getSerMacPos() {
        return serMacPos;
    }

    public void setSerMacPos(String serMacPos) {
        this.serMacPos = serMacPos;
    }

}
