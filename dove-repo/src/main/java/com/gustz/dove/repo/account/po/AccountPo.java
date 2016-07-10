package com.gustz.dove.repo.account.po;

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
 * TODO: 对应表WCSB_ACCOUNT 账户信息表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_ACCOUNT", uniqueConstraints = { @UniqueConstraint(columnNames = "ACCOUNT_CODE"),
        @UniqueConstraint(columnNames = "WEC_APP_ID"), @UniqueConstraint(columnNames = "WEC_APP_SECRET") })
public class AccountPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 账户CODE
    private String accountCode;

    // 账户名称
    private String accountName;

    // 账户类型: T0服务号/T1订阅号/T2企业号
    private String accountType;

    // 是否删除(Y：删除 N：正常)
    private String isDelete;

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

    @Override
    public String toString() {
        return super.toString(this);
    }

    public AccountPo() {
        super();
    }

    public AccountPo(String id) {
        this();
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

    @Column(name = "REMARKS", length = 2048)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "ACCOUNT_CODE", length = 120)
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @Column(name = "ACCOUNT_NAME", length = 60)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "WEC_APP_ID", length = 32)
    public String getWecAppId() {
        return wecAppId;
    }

    public void setWecAppId(String wecAppId) {
        this.wecAppId = wecAppId;
    }

    @Column(name = "WEC_APP_SECRET", length = 1024)
    public String getWecAppSecret() {
        return wecAppSecret;
    }

    public void setWecAppSecret(String wecAppSecret) {
        this.wecAppSecret = wecAppSecret;
    }

    @Column(name = "ACCOUNT_TYPE", length = 6)
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}