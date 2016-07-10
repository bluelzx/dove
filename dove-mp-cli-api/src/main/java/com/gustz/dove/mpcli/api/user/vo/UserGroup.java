package com.gustz.dove.mpcli.api.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 用户群组
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class UserGroup extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id，由微信分配
     */
    @JsonProperty("id")
    private int id;

    /**
     * 分组名字，UTF8编码
     */
    @JsonProperty("name")
    private String name;

    /**
     * 分组内用户数量
     */
    @JsonProperty("count")
    private int count;

    public UserGroup() {
        super();
    }

    public UserGroup(int id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public UserGroup(int id, String name, int count) {
        this(id, name);
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
