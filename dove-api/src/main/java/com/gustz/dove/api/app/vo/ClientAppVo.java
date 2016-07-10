package com.gustz.dove.api.app.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * TODO: 客户端应用VO
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
public class ClientAppVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 应用CODE
    private String cliAppCode;
    private String cliAppCodeLk;

    // 应用名称
    private String cliAppName;

    // 客户端密码
    private String cliAppPwd;

    // 应用IP地址集（逗号分隔）
    private String cliIpAddrs;
    private String[] cliIpAddrsIn;

    // 创建时间
    private Date createTime;

    // 服务编码（请求服务的接口编码）
    private String websCodes;
    private String[] websCodesIn;

    // 状态
    private String status;
    private String[] statusIn;
    private String statusText;

    // 账号（外键一对一）
    private String accountCode;

    // 接收信息的URL地址
    private String receiveUrl;

    // OAuth授权回调URL
    private String oauthCbUrl;

    // 是否回复消息(Y：是 N：否)
    private String isReplyMsg;
    private String isReplyMsgText;

    // --------------- 账户AccountVo --------------- begin
    // 账户名称
    private String accountName;

    // 唯一凭证
    private String wecAppId;

    // 唯一凭证密钥
    private String wecAppSecret;

    // --------------- 账户AccountVo --------------- end

    public ClientAppVo() {
        super();
    }

    /**
     * 
     * @param id
     */
    public ClientAppVo(String id) {
        this.setId(id);
    }

    /**
     * 
     * @param id
     * @param status
     */
    public ClientAppVo(String id, String status) {
        this(id);
        this.status = status;
    }

    /**
     * 
     * @param id
     * @param status
     * @param cliAppCode
     */
    public ClientAppVo(String id, String status, String cliAppCode) {
        this(id, status);
        this.cliAppCode = cliAppCode;
    }

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCliAppCode() {
        return cliAppCode;
    }

    public void setCliAppCode(String cliAppCode) {
        this.cliAppCode = cliAppCode;
    }

    public String getCliAppName() {
        return cliAppName;
    }

    public void setCliAppName(String cliAppName) {
        this.cliAppName = cliAppName;
    }

    public String getWebsCodes() {
        return websCodes;
    }

    public void setWebsCodes(String websCodes) {
        this.websCodes = websCodes;
    }

    public String getStatus() {
        return status;
    }

    public String getCliAppCodeLk() {
        return cliAppCodeLk;
    }

    public void setCliAppCodeLk(String cliAppCodeLk) {
        this.cliAppCodeLk = cliAppCodeLk;
    }

    public String[] getWebsCodesIn() {
        return websCodesIn;
    }

    public void setWebsCodesIn(String[] websCodesIn) {
        this.websCodesIn = websCodesIn;
    }

    public String getCliIpAddrs() {
        return cliIpAddrs;
    }

    public void setCliIpAddrs(String cliIpAddrs) {
        this.cliIpAddrs = cliIpAddrs;
    }

    public String[] getCliIpAddrsIn() {
        return cliIpAddrsIn;
    }

    public void setCliIpAddrsIn(String[] cliIpAddrsIn) {
        this.cliIpAddrsIn = cliIpAddrsIn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOauthCbUrl() {
        return oauthCbUrl;
    }

    public void setOauthCbUrl(String oauthCbUrl) {
        this.oauthCbUrl = oauthCbUrl;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getWecAppId() {
        return wecAppId;
    }

    public void setWecAppId(String wecAppId) {
        this.wecAppId = wecAppId;
    }

    public String getWecAppSecret() {
        return wecAppSecret;
    }

    public void setWecAppSecret(String wecAppSecret) {
        this.wecAppSecret = wecAppSecret;
    }

    public String getReceiveUrl() {
        return receiveUrl;
    }

    public String getCliAppPwd() {
        return cliAppPwd;
    }

    public void setCliAppPwd(String cliAppPwd) {
        this.cliAppPwd = cliAppPwd;
    }

    public void setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
    }

    public String[] getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(String[] statusIn) {
        this.statusIn = statusIn;
    }

    public String getIsReplyMsg() {
        return isReplyMsg;
    }

    public void setIsReplyMsg(String isReplyMsg) {
        this.isReplyMsg = isReplyMsg;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getIsReplyMsgText() {
        return isReplyMsgText;
    }

    public void setIsReplyMsgText(String isReplyMsgText) {
        this.isReplyMsgText = isReplyMsgText;
    }

}