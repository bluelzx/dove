package com.gustz.dove.repo.dict.po;

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
 * TODO: 对应表WCSB_DICT_GROUP 字典组别表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_DICT_GROUP", uniqueConstraints = { @UniqueConstraint(columnNames = "GROUP_CODE"),
        @UniqueConstraint(columnNames = "GROUP_NAME") })
public class DictGroupPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 组别CODE
    private String groupCode;

    // 组别名称
    private String groupName;

    // 创建时间
    private Date createTime;

    // 备注
    private String remarks;

    @Override
    public String toString() {
        return super.toString(this);
    }

    public DictGroupPo() {
        //null
    }

    public DictGroupPo(String id) {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "REMARKS", length = 1024)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "GROUP_CODE", length = 60)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "GROUP_NAME", length = 120)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}