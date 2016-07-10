package com.gustz.dove.api.dict.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * 
 * TODO: 字典VO
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
public class DictVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 组别CODE
    private String groupCode;

    // 组别名称
    private String groupName;

    // 创建时间
    private Date createTime;

    // 数据键
    private String dataKey;
    private String dataKeyLk;

    // 数据值
    private String dataValue;
    private String dataValueLk;

    // 备注
    private String remarks;

    // 是否加密(Y：是 N：否)
    private String isEncrypt;

    // 是否加密-文本
    private String isEncryptText;

    // 序号
    private Integer sn;

    public DictVo() {
        super();
    }

    public DictVo(String id) {
        this();
        this.setId(id);
    }

    public DictVo(String id, String groupCode) {
        this(id);
        this.groupCode = groupCode;
    }

    public DictVo(String id, String groupCode, String dataKey) {
        this(id, groupCode);
        this.dataKey = dataKey;
    }

    public DictVo(String id, String groupCode, String dataKey, String dataValue) {
        this(id);
        this.groupCode = groupCode;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIsEncrypt() {
        return isEncrypt;
    }

    public String getDataKeyLk() {
        return dataKeyLk;
    }

    public void setDataKeyLk(String dataKeyLk) {
        this.dataKeyLk = dataKeyLk;
    }

    public String getDataValueLk() {
        return dataValueLk;
    }

    public void setDataValueLk(String dataValueLk) {
        this.dataValueLk = dataValueLk;
    }

    public void setIsEncrypt(String isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public String getIsEncryptText() {
        return isEncryptText;
    }

    public void setIsEncryptText(String isEncryptText) {
        this.isEncryptText = isEncryptText;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

}