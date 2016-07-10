package com.gustz.dove.repo.app.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.sinovatech.fw.po.AbstractBasePo;

/**
 * 
 * TODO: 对应表WCSB_CLIENT_APP 客户端应用表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_CLIENT_APP", uniqueConstraints = { @UniqueConstraint(columnNames = "CLI_APP_CODE"),
        @UniqueConstraint(columnNames = "ACCOUNT_CODE") })
public class ClientAppPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 应用CODE
    private String cliAppCode;

    // 应用名称
    private String cliAppName;

    // 应用密码
    private String cliAppPwd;

    // 应用IP地址集（逗号分隔）
    private String cliIpAddrs;

    // 是否删除(Y：删除 N：正常)
    private String isDelete;

    // 是否回复消息(Y：是 N：否)
    private String isReplyMsg;

    // 创建时间
    private Date createTime;

    // 服务编码（请求服务的接口编码）
    private String websCodes;

    // 状态
    private String status;

    // 账号（外键一对一）
    private String accountCode;

    // 接收信息的URL地址
    private String receiveUrl;

    // OAuth授权回调URL
    private String oauthCbUrl;

    @Override
    public String toString() {
        return super.toString(this);
    }

    public ClientAppPo() {
        //null
    }

    public ClientAppPo(String id) {
        this.setId(id);
    }

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", nullable = false, length = 32)
    @Override
    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    @Column(name = "IS_DELETE", length = 6)
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CLI_APP_CODE", length = 32)
    public String getCliAppCode() {
        return cliAppCode;
    }

    public void setCliAppCode(String cliAppCode) {
        this.cliAppCode = cliAppCode;
    }

    @Column(name = "CLI_APP_NAME", length = 60)
    public String getCliAppName() {
        return cliAppName;
    }

    public void setCliAppName(String cliAppName) {
        this.cliAppName = cliAppName;
    }

    @Column(name = "WEBS_CODES", length = 1024)
    public String getWebsCodes() {
        return websCodes;
    }

    public void setWebsCodes(String websCodes) {
        this.websCodes = websCodes;
    }

    @Column(name = "CLI_IP_ADDRS", length = 1024)
    public String getCliIpAddrs() {
        return cliIpAddrs;
    }

    public void setCliIpAddrs(String cliIpAddrs) {
        this.cliIpAddrs = cliIpAddrs;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "ACCOUNT_CODE", length = 120)
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @Column(name = "RECEIVE_URL", length = 1024)
    public String getReceiveUrl() {
        return receiveUrl;
    }

    public void setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
    }

    @Column(name = "OAUTH_CB_URL", length = 1024)
    public String getOauthCbUrl() {
        return oauthCbUrl;
    }

    public void setOauthCbUrl(String oauthCbUrl) {
        this.oauthCbUrl = oauthCbUrl;
    }

    @Column(name = "CLI_APP_PWD", length = 120)
    public String getCliAppPwd() {
        return cliAppPwd;
    }

    public void setCliAppPwd(String cliAppPwd) {
        this.cliAppPwd = cliAppPwd;
    }

    @Column(name = "IS_REPLY_MSG", length = 6)
    public String getIsReplyMsg() {
        return isReplyMsg;
    }

    public void setIsReplyMsg(String isReplyMsg) {
        this.isReplyMsg = isReplyMsg;
    }

}