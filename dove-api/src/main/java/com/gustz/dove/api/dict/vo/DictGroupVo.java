package com.gustz.dove.api.dict.vo;

import java.util.Date;

import com.sinovatech.fw.api.vo.AbstractBaseVo;

/**
 * 
 * TODO: 字典组别VO
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
public class DictGroupVo extends AbstractBaseVo<String> {

    private static final long serialVersionUID = 1L;

    // 组别CODE
    private String groupCode;
    private String groupCodeLk;

    // 组别名称
    private String groupName;

    // 创建时间
    private Date createTime;

    // 备注
    private String remarks;

    public DictGroupVo() {
        super();
    }

    public DictGroupVo(String id) {
        this();
        this.setId(id);
    }

    public DictGroupVo(String id, String groupCode) {
        this(id);
        this.groupCode = groupCode;
    }

    public DictGroupVo(String id, String groupCode, String groupName) {
        this(id, groupCode);
        this.groupName = groupName;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupCodeLk() {
        return groupCodeLk;
    }

    public void setGroupCodeLk(String groupCodeLk) {
        this.groupCodeLk = groupCodeLk;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}