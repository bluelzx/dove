package com.gustz.dove.cli.api.account.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * TODO: 账号VO
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class AccountVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 账户CODE（微信代理ID/原始ID+wecAppId=MD5串）
    private String accountCode;

    // 账户名称
    private String accountName;

    // 账户类型: T0服务号/T1订阅号/T2企业号
    private String accountType;

    // 创建时间
    private Date createTime;

    // 状态
    private String status;

    // 唯一凭证
    private String wecAppId;

    // 唯一凭证密钥
    private String wecAppSecret;

    // 备注
    private String remarks;

    // ------------- 页面参数 begin
    // 订阅号、服务号的原始ID/企业好应用ID
    private String srcId;

    // ------------- 页面参数 end

    public AccountVo() {
        super();
    }

    public AccountVo(String id) {
        this();
        this.setId(id);
    }

    public AccountVo(String id, String status) {
        this(id);
        this.status = status;
    }

    /**
     * 
     * @param id
     * @param status
     * @param wecAppId
     * @param appSecret
     */
    public AccountVo(String id, String status, String wecAppId, String appSecret) {
        this(id, status);
        this.wecAppId = wecAppId;
        this.wecAppSecret = appSecret;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getStatus() {
        return status;
    }

    public String getWecAppId() {
        return wecAppId;
    }

    public void setWecAppId(String wecAppId) {
        this.wecAppId = wecAppId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getWecAppSecret() {
        return wecAppSecret;
    }

    public void setWecAppSecret(String wecAppSecret) {
        this.wecAppSecret = wecAppSecret;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
