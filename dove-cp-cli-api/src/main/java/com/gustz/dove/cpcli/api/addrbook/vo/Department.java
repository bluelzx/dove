package com.gustz.dove.cpcli.api.addrbook.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 用户部门
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class Department extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @JsonProperty("id")
    private int id;

    /**
     * 部门名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 父亲部门id。根部门为1
     */
    @JsonProperty("parentid")
    private int parentId;

    /**
     *  在父部门中的次序值。order值小的排序靠前。 
     */
    @JsonProperty("order")
    private int order;

    public Department() {
        super();
    }

    public Department(int id, String name, int parentId, int order) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
