package com.gustz.dove.api.menu.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * 
 * TODO: 应用菜单VO
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
public class AppMenuVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 应用菜单CODE
    private String appMenuCode;
    private String appMenuCodeLk;

    // 应用菜单名称
    private String appMenuName;

    // 应用CODE
    private String cliAppCode;

    // 创建时间
    private Date createTime;

    // 状态
    private String status;
    private String statusText;

    // 菜单内容
    private String content;

    // ---------------- ClientAppVo ---------------- begin
    // 应用名称
    private String cliAppName;

    // 服务编码（请求服务的接口编码）
    private String websCodes;

    // 账号（外键一对一）
    private String accountCode;

    // ---------------- ClientAppVo ---------------- end

    // ---------------- AccountVo ---------------- begin
    // 账户类型: T0服务号/T1订阅号/T2企业号
    private String accountType;

    // ---------------- AccountVo ---------------- end

    public AppMenuVo() {
        //null
    }

    public AppMenuVo(String id) {
        this.setId(id);
    }

    /**
     * 
     * @param id
     * @param appMenuCode
     */
    public AppMenuVo(String id, String appMenuCode) {
        this(id);
        this.appMenuCode = appMenuCode;
    }

    @Override
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppMenuCode() {
        return appMenuCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAppMenuCode(String appMenuCode) {
        this.appMenuCode = appMenuCode;
    }

    public String getAppMenuName() {
        return appMenuName;
    }

    public void setAppMenuName(String appMenuName) {
        this.appMenuName = appMenuName;
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

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setWebsCodes(String websCodes) {
        this.websCodes = websCodes;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAppMenuCodeLk() {
        return appMenuCodeLk;
    }

    public void setAppMenuCodeLk(String appMenuCodeLk) {
        this.appMenuCodeLk = appMenuCodeLk;
    }

}