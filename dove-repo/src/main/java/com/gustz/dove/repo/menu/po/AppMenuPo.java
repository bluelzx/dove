package com.gustz.dove.repo.menu.po;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.sinovatech.fw.po.AbstractBasePo;

/**
 * 
 * TODO: 对应表WCSB_APP_MENU 应用菜单表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_APP_MENU", uniqueConstraints = @UniqueConstraint(columnNames = "APP_MENU_CODE"))
public class AppMenuPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 应用菜单CODE
    private String appMenuCode;

    // 应用菜单名称
    private String appMenuName;

    // 应用CODE
    private String cliAppCode;

    // 是否删除(Y：删除 N：正常)
    private String isDelete;

    // 创建时间
    private Date createTime;

    // 状态
    private String status;

    // 菜单内容
    private String content;

    @Override
    public String toString() {
        return super.toString(this);
    }

    public AppMenuPo() {
        //null
    }

    public AppMenuPo(String id) {
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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "APP_MENU_CODE", length = 32)
    public String getAppMenuCode() {
        return appMenuCode;
    }

    public void setAppMenuCode(String appMenuCode) {
        this.appMenuCode = appMenuCode;
    }

    @Column(name = "APP_MENU_NAME", length = 60)
    public String getAppMenuName() {
        return appMenuName;
    }

    public void setAppMenuName(String appMenuName) {
        this.appMenuName = appMenuName;
    }

}