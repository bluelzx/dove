package com.gustz.dove.cli.api.menu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cli.api.service.dict.BtnTypeDict;

/**
 * 
 * TODO: 按钮
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class Button extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 按钮类型
     */
    @JsonProperty("type")
    private BtnTypeDict type;

    /**
     * 按钮名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 按钮key值
     */
    @JsonProperty("key")
    private String key;

    /**
     * 按钮URL
     */
    @JsonProperty("url")
    private String url;

    /**
     * 子按钮列表
     */
    @JsonProperty("sub_button")
    private Button[] subBtns = new Button[] {};

    public Button() {
        super();
    }

    /**
     * 
     * @param name
     * @param subBtns
     */
    public Button(String name, Button[] subBtns) {
        this.name = name;
        this.subBtns = subBtns;
    }

    /**
     * 
     * @param type
     * @param name
     * @param url
     */
    public Button(BtnTypeDict type, String name, String url) {
        this();
        this.type = type;
        this.name = name;
        this.url = url;
    }

    /**
     * 
     * @param type
     * @param name
     * @param key
     * @param subBtns
     */
    public Button(BtnTypeDict type, String name, String key, Button[] subBtns) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.subBtns = subBtns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BtnTypeDict getType() {
        return type;
    }

    public void setType(BtnTypeDict type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Button[] getSubBtns() {
        return subBtns;
    }

    public void setSubBtns(Button[] subBtns) {
        this.subBtns = subBtns;
    }

}
