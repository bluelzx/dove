package com.gustz.dove.repo.dict.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.sinovatech.fw.po.AbstractBasePo;

/**
 * 
 * TODO: 对应表WCSB_DICT 字典表
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
@Entity
@Table(name = "WCSB_DICT")
public class DictPo extends AbstractBasePo<String> {

    private static final long serialVersionUID = 1L;

    // 主键ID
    // private String id;

    // 组别CODE
    private String groupCode;

    // 创建时间
    private Date createTime;

    // 数据键
    private String dataKey;

    // 数据值
    private String dataValue;

    // 备注
    private String remarks;

    // 是否加密(Y：是 N：否)
    private String isEncrypt;

    // 序号
    private Integer sn;

    @Override
    public String toString() {
        return super.toString(this);
    }

    public DictPo() {
        //null
    }

    public DictPo(String id) {
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

    @Column(name = "GROUP_CODE", length = 60)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "DATA_KEY", length = 1024)
    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    @Column(name = "DATA_VALUE", length = 1024)
    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    @Column(name = "REMARKS", length = 1024)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "IS_ENCRYPT", length = 6)
    public String getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(String isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    @Column(name = "SN")
    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

}